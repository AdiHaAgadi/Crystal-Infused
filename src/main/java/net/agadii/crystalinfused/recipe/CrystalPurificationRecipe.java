package net.agadii.crystalinfused.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CrystalPurificationRecipe implements Recipe<SimpleInventory> {
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    public CrystalPurificationRecipe(ItemStack output, DefaultedList<Ingredient> recipeItems) {
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }

        return recipeItems.get(0).test(inventory.getStack(1));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll(recipeItems);
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CrystalPurificationRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "purifying";
    }

    public static class Serializer implements RecipeSerializer<CrystalPurificationRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "purifying";

        public static final Codec<CrystalPurificationRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ItemStack.RECIPE_RESULT_CODEC.fieldOf("output").forGetter(r -> r.output),
                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 1).fieldOf("ingredients").forGetter(CrystalPurificationRecipe::getIngredients)
        ).apply(instance, (output, ingredients) ->
                new CrystalPurificationRecipe(output, DefaultedList.copyOf(Ingredient.EMPTY, ingredients.toArray(new Ingredient[0])))
        ));

        @Override
        public Codec<CrystalPurificationRecipe> codec() {
            return CODEC;
        }

        public static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
            return Codecs.validate(Codecs.validate(
                    delegate.listOf(), list -> list.size() > max ? DataResult.error(() ->
                            "Recipe has too many ingredients!") : DataResult.success(list)
            ), list -> list.isEmpty() ? DataResult.error(() -> "Recipe has no ingredients!") : DataResult.success(list));
        }

        private ItemStack parseOutputJson(JsonObject outputJson) {
            Identifier itemId = new Identifier(JsonHelper.getString(outputJson, "item"));
            int count = JsonHelper.getInt(outputJson, "count", 1);
            ItemStack output = new ItemStack(Registries.ITEM.get(itemId), count);

            if (outputJson.has("nbt")) {
                try {
                    NbtCompound nbt = StringNbtReader.parse(JsonHelper.getString(outputJson, "nbt"));
                    output.setNbt(nbt);
                } catch (CommandSyntaxException e) {
                    e.printStackTrace();
                }
            }

            return output;
        }

//        @Override
//        public CrystalPurificationRecipe read(JsonObject json) {
//            ItemStack output = parseOutputJson(JsonHelper.getObject(json, "output"));
//            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
//
//            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(1, Ingredient.EMPTY);
//
//            for (int i = 0; i < inputs.size(); i++) {
//                inputs.add(Ingredient.DISALLOW_EMPTY_CODEC.parse(JsonOps.INSTANCE, ingredients.get(i))
//                        .resultOrPartial(msg -> {
//                            throw new IllegalStateException("Failed to parse ingredient: " + msg);
//                        })
//                        .orElseThrow());            }
//
//            return new CrystalPurificationRecipe(output, inputs);
//        }

        @Override
        public CrystalPurificationRecipe read(PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            return new CrystalPurificationRecipe(output, inputs);
        }

        @Override
        public void write(PacketByteBuf buf, CrystalPurificationRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }

            buf.writeItemStack(recipe.getResult(null));
        }
    }
}
