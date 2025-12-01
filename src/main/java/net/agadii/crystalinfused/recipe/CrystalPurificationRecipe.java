package net.agadii.crystalinfused.recipe;

import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.agadii.crystalinfused.recipe.recipeInput.CrystalPurificationRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.List;

import static utils.CodecUtils.validateAmount;

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

        public static final MapCodec<CrystalPurificationRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                ItemStack.CODEC.fieldOf("output").forGetter(CrystalPurificationRecipe::output),
                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 1).fieldOf("ingredients").forGetter(CrystalPurificationRecipe::getIngredients)
        ).apply(instance, (output, ingredients) ->
                new CrystalPurificationRecipe(output, DefaultedList.copyOf(Ingredient.EMPTY, ingredients.toArray(new Ingredient[0])))
        ));

        public static final PacketCodec<RegistryByteBuf, CrystalPurificationRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC.collect(PacketCodecs.toList()), CrystalPurificationRecipe::recipeItems,
                        ItemStack.PACKET_CODEC, CrystalPurificationRecipe::output,
                        (ingredients, output) ->                                   // constructor logic
                                new CrystalPurificationRecipe(
                                        output,
                                        DefaultedList.copyOf(Ingredient.EMPTY, ingredients.toArray(new Ingredient[0]))
                                )
                );



        @Override
        public MapCodec<CrystalPurificationRecipe> codec() {
            return MAP_CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, CrystalPurificationRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
