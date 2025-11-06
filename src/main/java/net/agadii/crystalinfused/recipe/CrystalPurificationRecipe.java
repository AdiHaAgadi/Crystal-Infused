package net.agadii.crystalinfused.recipe;

import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.agadii.crystalinfused.recipe.recipeInput.CrystalPurificationRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.List;

public record CrystalPurificationRecipe(ItemStack output, DefaultedList<Ingredient> recipeItems) implements Recipe<CrystalPurificationRecipeInput> {
    @Override
    public boolean matches(CrystalPurificationRecipeInput inventory, World world) {
        if (world.isClient()) {
            return false;
        }

        return recipeItems.get(0).test(inventory.getStackInSlot(1));
    }

    @Override
    public ItemStack craft(CrystalPurificationRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
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

        public static final MapCodec<CrystalPurificationRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ItemStack.OPTIONAL_CODEC.fieldOf("output").forGetter(CrystalPurificationRecipe::output),
                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 1).fieldOf("basg").forGetter(CrystalPurificationRecipe::getIngredients)
        ).apply(instance, (output, ingredients) ->
                new CrystalPurificationRecipe(output, DefaultedList.copyOf(Ingredient.EMPTY, ingredients.toArray(new Ingredient[0])))
        ));

        public static final PacketCodec<RegistryByteBuf, CrystalPurificationRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        PacketCodec.list(Ingredient.PACKET_CODEC), // list of ingredients
                        ItemStack.PACKET_CODEC,
                        (ingredients, output) -> new CrystalPurificationRecipe(
                                DefaultedList.copyOf(Ingredient.EMPTY, ingredients.toArray(new Ingredient[0])),
                                output
                        ),
                        CrystalPurificationRecipe::getIngredients, // accessor for ingredients
                        CrystalPurificationRecipe::output         // accessor for output
                );



        @Override
        public MapCodec<CrystalPurificationRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, CrystalPurificationRecipe> packetCodec() {
            return STREAM_CODEC;
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

//        @Override
//        public CrystalPurificationRecipe read(PacketByteBuf buf) {
//            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
//
//            for (int i = 0; i < inputs.size(); i++) {
//                inputs.set(i, Ingredient.fromPacket(buf));
//            }
//
//            ItemStack output = buf.readItemStack();
//            return new CrystalPurificationRecipe(output, inputs);
//        }
//
//        @Override
//        public void write(PacketByteBuf buf, CrystalPurificationRecipe recipe) {
//            buf.writeInt(recipe.getIngredients().size());
//
//            for (Ingredient ing : recipe.getIngredients()) {
//                ing.write(buf);
//            }
//
//            buf.writeItemStack(recipe.getResult(null));
//        }
    }
}
