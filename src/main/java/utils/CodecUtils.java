package utils;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;

import java.util.Optional;

public class CodecUtils {
    public static final Codec<Ingredient> INGREDIENT_WITH_NBT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Registries.ITEM.getCodec().fieldOf("item").forGetter(ing -> ing.getMatchingStacks()[0].getItem()),
            Codec.STRING.optionalFieldOf("nbt").forGetter(ing -> {
                ItemStack stack = ing.getMatchingStacks()[0];
                NbtCompound nbt = stack.getNbt();

                return java.util.Optional.ofNullable(nbt != null ? nbt.toString() : null);
            })
    ).apply(instance, (item, nbtString) -> {
        ItemStack stack = new ItemStack(item);
        nbtString.ifPresent(s -> {
            try {
                stack.setNbt(StringNbtReader.parse(s));
            } catch (CommandSyntaxException e) {
                throw new RuntimeException("Invalid NBT: " + s, e);
            }
        });

        return Ingredient.ofStacks(stack);
    }));

    public static final Codec<ItemStack> RECIPE_RESULT_WITH_NBT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Registries.ITEM.getCodec().fieldOf("item").forGetter(ItemStack::getItem),
            Codec.INT.optionalFieldOf("count", 1).forGetter(ItemStack::getCount),
            Codec.STRING.optionalFieldOf("nbt").forGetter(stack -> {
                NbtCompound nbt = stack.getNbt();

                return Optional.ofNullable(nbt != null ? nbt.toString() : null);
            })
    ).apply(instance, (item, count, nbtString) -> {
        ItemStack stack = new ItemStack(item, count);
        nbtString.ifPresent(s -> {
            try {
                stack.setNbt(StringNbtReader.parse(s));
            } catch (CommandSyntaxException e) {
                throw new RuntimeException("Invalid NBT: " + s, e);
            }
        });

        return stack;
    }));
}

