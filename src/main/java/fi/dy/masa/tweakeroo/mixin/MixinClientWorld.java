package fi.dy.masa.tweakeroo.mixin;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import org.spongepowered.asm.mixin.Mixin;
import fi.dy.masa.tweakeroo.config.FeatureToggle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkManager;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.LevelProperties;

@Mixin(ClientWorld.class)
public abstract class MixinClientWorld extends World
{
    protected MixinClientWorld(LevelProperties settings, DimensionType dimType, BiFunction<World, Dimension, ChunkManager> func, Profiler profiler)
    {
        super(settings, dimType, func, profiler, true);
    }

    @Override
    public void tickEntity(Consumer<Entity> consumer, Entity entity)
    {
        if (FeatureToggle.TWEAK_NO_CLIENT_ENTITY_UPDATES.getBooleanValue() == false || entity instanceof PlayerEntity)
        {
            super.tickEntity(consumer, entity);
        }
    }
}
