package hi.korperka.dsmod.entity.client.froggy;

import hi.korperka.dsmod.DSMod;
import hi.korperka.dsmod.entity.custom.froggy.FroggyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FroggyModel extends AnimatedGeoModel<FroggyEntity> {

    @Override
    public ResourceLocation getModelResource(FroggyEntity object) {
        return new ResourceLocation(DSMod.MODID, "geo/froggy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FroggyEntity object) {
        return new ResourceLocation(DSMod.MODID, "textures/entity/froggy_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FroggyEntity animatable) {
        return new ResourceLocation(DSMod.MODID, "animations/froggy.animations.json");
    }
}
