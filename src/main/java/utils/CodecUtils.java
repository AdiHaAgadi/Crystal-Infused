package utils;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;
import java.util.Optional;

public class CodecUtils {
    public static final Codec<Ingredient> INGREDIENT_FROM_STACK_CODEC = ItemStack.CODEC.flatComapMap(
            Ingredient::ofStacks,
            (Ingredient ingredient) -> {
                ItemStack[] items = ingredient.getMatchingStacks();

                if (items.length > 0) {
                    return DataResult.success(items[0]);
                }
                return DataResult.error(() -> "Cannot encode empty ingredient");
            }
    );

    public static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
        return delegate.listOf().flatXmap(
                list -> {
                    if (list.isEmpty()) {
                        return DataResult.error(() -> "Recipe has no ingredients!");
                    }
                    if (list.size() > max) {
                        return DataResult.error(() -> "Recipe has too many ingredients!");
                    }
                    return DataResult.success(list);
                },
                list -> {
                    if (list.isEmpty()) {
                        return DataResult.error(() -> "Recipe has no ingredients!");
                    }
                    if (list.size() > max) {
                        return DataResult.error(() -> "Recipe has too many ingredients!");
                    }
                    return DataResult.success(list);
                }
        );
    }
}

