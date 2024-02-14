package hi.korperka.dsmod.entity.client.shroooom;

import hi.korperka.dsmod.DSMod;
import hi.korperka.dsmod.entity.custom.shroooom.ShroomEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShroomModel extends AnimatedGeoModel<ShroomEntity> {

    @Override
    public ResourceLocation getModelResource(ShroomEntity object) {
        return new ResourceLocation(DSMod.MODID, "geo/shroom.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShroomEntity object) {
        return new ResourceLocation(DSMod.MODID, "textures/entity/shroom_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShroomEntity animatable) {
        return new ResourceLocation(DSMod.MODID, "animations/shroom.animations.json");
    }
}
