package me.kctops6.animalfeeds.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class AnimalFeedItem extends Item {
    private final Supplier<? extends EntityType<? extends Animal>> entityTypeSupplier;

    public AnimalFeedItem(Properties properties, Supplier<? extends EntityType<? extends Animal>> entityType) {
        super(properties);
        this.entityTypeSupplier = entityType;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();

        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        if (level instanceof ServerLevel serverLevel) {
            BlockPos pos = context.getClickedPos().relative(context.getClickedFace());

            Animal animal = entityTypeSupplier.get().create(serverLevel);
            if (animal != null) {
                animal.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.0F, 0.0F);
                animal.finalizeSpawn(serverLevel, level.getCurrentDifficultyAt(pos), MobSpawnType.SPAWN_EGG, null, null);
                serverLevel.addFreshEntity(animal);

                level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);

                if (context.getPlayer() != null && !context.getPlayer().getAbilities().instabuild) {
                    context.getItemInHand().shrink(1);
                }
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(Component.translatable(this.getDescriptionId() + ".tooltip").withStyle(ChatFormatting.GREEN));
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}