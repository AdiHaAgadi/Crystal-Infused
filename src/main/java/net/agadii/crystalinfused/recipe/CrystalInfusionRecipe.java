package net.agadii.crystalinfused.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.agadii.crystalinfused.recipe.recipeInput.CrystalInfusionRecipeInput;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import utils.CodecUtils;

import java.util.ArrayList;
import java.util.List;

import static utils.CodecUtils.validateAmount;

public record CrystalInfusionRecipe(ItemStack output, DefaultedList<Ingredient> recipeItems, ArrayList<ItemStack> displayInputs) implements Recipe<CrystalInfusionRecipeInput> {

    public ArrayList<ItemStack> getDisplayInputs() {
        return displayInputs;
    }

    private static ArrayList<ItemStack> createDisplayInputsFromIngredientList(List<Ingredient> ingredients) {
        ArrayList<ItemStack> displayInputs = new ArrayList<>();
        for (Ingredient ing : ingredients) {
            ItemStack[] matchingStacks = ing.getMatchingStacks();
            if (matchingStacks.length > 0) {
                displayInputs.add(matchingStacks[0].copy()); // take first stack for display
            }
        }

        return displayInputs;
    }

    private boolean isSameEnchantmentsNbt(ItemStack expected, ItemStack input) {
        if (!expected.isOf(input.getItem())) return false;

        ItemEnchantmentsComponent expectedEnchantments = EnchantmentHelper.getEnchantments(expected);
        ItemEnchantmentsComponent inputEnchantments = EnchantmentHelper.getEnchantments(input);

        if (expectedEnchantments == null && inputEnchantments == null) {
            return true;
        }
        if (expectedEnchantments == null || inputEnchantments == null) {
            return false;
        }

        return expectedEnchantments.getEnchantments().equals(inputEnchantments.getEnchantments());
    }

    private boolean isMatchNbt(Ingredient expected, ItemStack input) {
        ItemStack[] expectedStacks = expected.getMatchingStacks();

        if (expectedStacks.length > 0) {
            ItemStack expectedStack = expectedStacks[0];

            // Compare enchantments if either stack has them
            boolean expectedHasEnchants = expectedStack.get(DataComponentTypes.ENCHANTMENTS) != null;
            boolean inputHasEnchants = input.get(DataComponentTypes.ENCHANTMENTS) != null;

            if (expectedHasEnchants) {
                return inputHasEnchants && isSameEnchantmentsNbt(expectedStack, input);
            }
        }

        return false;
    }

    @Override
    public boolean matches(CrystalInfusionRecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }

        return recipeItems.get(0).test(input.getStackInSlot(0))
                && this.isMatchNbt(recipeItems.get(0), input.getStackInSlot(0))
                && recipeItems.get(1).test(input.getStackInSlot(1))
                && recipeItems.get(2).test(input.getStackInSlot(2));    }

    @Override
    public ItemStack craft(CrystalInfusionRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
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

    public static class Type implements RecipeType<CrystalInfusionRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "infusing";
    }

    public static class Serializer implements RecipeSerializer<CrystalInfusionRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "infusing";

        public static final MapCodec<CrystalInfusionRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                // Only encode/decode the actual ingredients and output
                validateAmount(CodecUtils.INGREDIENT_FROM_STACK_CODEC, 3).fieldOf("ingredients").forGetter(CrystalInfusionRecipe::getIngredients),
                ItemStack.CODEC.fieldOf("result").forGetter(CrystalInfusionRecipe::output)
        ).apply(instance, (ingredients, output) -> {
            ArrayList<ItemStack> displayInputs =
                    CrystalInfusionRecipe.createDisplayInputsFromIngredientList(ingredients);

            // constructor logic
            return new CrystalInfusionRecipe(
                    output,
                    DefaultedList.copyOf(Ingredient.EMPTY, ingredients.toArray(new Ingredient[0])),
                    displayInputs
            );
        }));

        public static final PacketCodec<RegistryByteBuf, CrystalInfusionRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC.collect(PacketCodecs.toList()), CrystalInfusionRecipe::recipeItems,
                        ItemStack.PACKET_CODEC, CrystalInfusionRecipe::output,
                        (ingredients, output) -> {
                            ArrayList<ItemStack> displayInputs =
                                    CrystalInfusionRecipe.createDisplayInputsFromIngredientList(ingredients);

                            // constructor logic
                            return new CrystalInfusionRecipe(
                                    output,
                                    DefaultedList.copyOf(Ingredient.EMPTY, ingredients.toArray(new Ingredient[0])),
                                    displayInputs
                            );
                        }
                );

        @Override
        public MapCodec<CrystalInfusionRecipe> codec() {
            return MAP_CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, CrystalInfusionRecipe> packetCodec() {
            return STREAM_CODEC;
        }

//        private ItemStack parseOutputJson(JsonObject outputJson) {
//            Identifier itemId = Identifier.of(JsonHelper.getString(outputJson, "item"));
//            int count = JsonHelper.getInt(outputJson, "count", 1);
//            ItemStack output = new ItemStack(Registries.ITEM.get(itemId), count);
//
//            if (outputJson.has("nbt")) {
//                try {
//                    NbtCompound nbt = StringNbtReader.parse(JsonHelper.getString(outputJson, "nbt"));
//                    output.setNbt(nbt);
//                } catch (CommandSyntaxException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            return output;
//        }

//        public CrystalInfusionRecipe read(Identifier id, JsonObject json) {
//            JsonObject resultObject = JsonHelper.getObject(json, "result");
//            ItemStack output = parseOutputJson(resultObject);
//
//            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
//            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(ingredients.size(), Ingredient.EMPTY);
//            ArrayList<ItemStack> displayInputs = new ArrayList<>();
//
//            for (int i = 0; i < ingredients.size(); i++) {
//                JsonObject ingredientObject = ingredients.get(i).getAsJsonObject();
//                ItemStack stack = new ItemStack(Registries.ITEM.get(new Identifier(JsonHelper.getString(ingredientObject, "item"))));
//                if (ingredientObject.has("nbt")) {
//                    try {
//                        NbtCompound nbt = StringNbtReader.parse(JsonHelper.getString(ingredientObject, "nbt"));
//                        stack.setNbt(nbt);
//                    } catch (CommandSyntaxException e) {
//                        e.printStackTrace();
//                    }
//                }
//                inputs.set(i, Ingredient.ofStacks(stack));
//                displayInputs.add(stack);
//            }
//
//            return new CrystalInfusionRecipe(output, inputs, displayInputs);
//        }

//        @Override
//        public CrystalInfusionRecipe read(PacketByteBuf buf) {
//            int size = buf.readInt();
//            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(size, Ingredient.EMPTY);
//
//            for (int i = 0; i < size; i++) {
//                inputs.set(i, Ingredient.fromPacket(buf));
//            }
//
//            ItemStack output = buf.readItemStack();
//            String nbtString = buf.readString(32767);
//            if (!nbtString.isEmpty()) {
//                try {
//                    NbtCompound nbt = StringNbtReader.parse(nbtString);
//                    output.setNbt(nbt);
//                } catch (CommandSyntaxException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            int displaySize = buf.readInt();
//            ArrayList<ItemStack> displayInputs = new ArrayList<>();
//            for (int i = 0; i < displaySize; i++) {
//                displayInputs.add(buf.readItemStack());
//            }
//
//            return new CrystalInfusionRecipe(output, inputs, displayInputs);
//        }
//
//        @Override
//        public void write(PacketByteBuf buf, CrystalInfusionRecipe recipe) {
//            buf.writeInt(recipe.getIngredients().size());
//
//            for (Ingredient ing : recipe.getIngredients()) {
//                ing.write(buf);
//            }
//
//            ItemStack output = recipe.getResult(null);
//            buf.writeItemStack(output);
//
//            if (output.hasNbt()) {
//                buf.writeString(output.getNbt().toString());
//            } else {
//                buf.writeString("");
//            }
//        }
    }
}
