package hi.korperka.dsmod.dimensions;

import hi.korperka.dsmod.DSMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class DSDimensions {
    public static ResourceKey<Level> DSDIM_KEY =
            ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(DSMod.MODID, "dsdim"));

    public static ResourceKey<DimensionType> DSDIM_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(DSMod.MODID, DSDIM_KEY.registry().getPath()));
}
