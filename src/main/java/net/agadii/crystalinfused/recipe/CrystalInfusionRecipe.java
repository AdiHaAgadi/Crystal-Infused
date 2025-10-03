package net.agadii.crystalinfused.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.types.templates.List;
import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.world.World;

import java.util.ArrayList;

public class CrystalInfusionRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;
    private final ArrayList<ItemStack> displayInputs;

    public CrystalInfusionRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems, ArrayList<ItemStack> displayInputs) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.displayInputs = displayInputs;
    }


    public ArrayList<ItemStack> getDisplayInputs() {
        return displayInputs;
    }

    private boolean isSameEnchantmentsNbt(ItemStack expected, ItemStack input) {
        if (!expected.isOf(input.getItem())) return false;

        var expectedEnchants = EnchantmentHelper.get(expected);
        var inputEnchants = EnchantmentHelper.get(input);

        return expectedEnchants.equals(inputEnchants);
    }

    private boolean isMatchNbt(Ingredient expected, ItemStack input) {
        ItemStack[] expectedStacks = expected.getMatchingStacks();

        if (expectedStacks.length > 0) {
            ItemStack expectedStack = expectedStacks[0];
            if (expectedStack.hasNbt()) {
                if (!input.hasNbt() || !isSameEnchantmentsNbt(expectedStack, input)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }

        return recipeItems.get(0).test(inventory.getStack(0))
                && this.isMatchNbt(recipeItems.get(0), inventory.getStack(0))
                && recipeItems.get(1).test(inventory.getStack(1))
                && recipeItems.get(2).test(inventory.getStack(2));
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
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll(recipeItems);

        return list;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CrystalInfusionRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "infusing";
    }

    public static class Serializer implements RecipeSerializer<CrystalInfusionRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "infusing";

        @Override
        public CrystalInfusionRecipe read(Identifier id, JsonObject json) {
            JsonObject resultObject = JsonHelper.getObject(json, "result");
            ItemStack output = ShapedRecipe.outputFromJson(resultObject);

            if (resultObject.has("nbt")) {
                try {
                    NbtCompound nbt = StringNbtReader.parse(JsonHelper.getString(resultObject, "nbt"));
                    output.setNbt(nbt);
                } catch (CommandSyntaxException e) {
                    e.printStackTrace();
                }
            }

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(ingredients.size(), Ingredient.EMPTY);
            ArrayList<ItemStack> displayInputs = new ArrayList<>();

            for (int i = 0; i < ingredients.size(); i++) {
                JsonObject ingredientObject = ingredients.get(i).getAsJsonObject();
                ItemStack stack = new ItemStack(Registries.ITEM.get(new Identifier(JsonHelper.getString(ingredientObject, "item"))));
                if (ingredientObject.has("nbt")) {
                    try {
                        NbtCompound nbt = StringNbtReader.parse(JsonHelper.getString(ingredientObject, "nbt"));
                        stack.setNbt(nbt);
                    } catch (CommandSyntaxException e) {
                        e.printStackTrace();
                    }
                }
                inputs.set(i, Ingredient.ofStacks(stack));
                displayInputs.add(stack);
            }

            return new CrystalInfusionRecipe(id, output, inputs, displayInputs);
        }

        @Override
        public CrystalInfusionRecipe read(Identifier id, PacketByteBuf buf) {
            int size = buf.readInt();
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(size, Ingredient.EMPTY);

            for (int i = 0; i < size; i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            String nbtString = buf.readString(32767);
            if (!nbtString.isEmpty()) {
                try {
                    NbtCompound nbt = StringNbtReader.parse(nbtString);
                    output.setNbt(nbt);
                } catch (CommandSyntaxException e) {
                    e.printStackTrace();
                }
            }

            int displaySize = buf.readInt();
            ArrayList<ItemStack> displayInputs = new ArrayList<>();
            for (int i = 0; i < displaySize; i++) {
                displayInputs.add(buf.readItemStack());
            }

            return new CrystalInfusionRecipe(id, output, inputs, displayInputs);
        }

        @Override
        public void write(PacketByteBuf buf, CrystalInfusionRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }

            ItemStack output = recipe.getOutput(null);
            buf.writeItemStack(output);

            if (output.hasNbt()) {
                buf.writeString(output.getNbt().toString());
            } else {
                buf.writeString("");
            }
        }
    }
}
