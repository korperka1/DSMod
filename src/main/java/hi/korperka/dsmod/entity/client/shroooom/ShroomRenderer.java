package hi.korperka.dsmod.entity.client.froggy;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import hi.korperka.dsmod.entity.client.shroooom.ShroomModel;
import hi.korperka.dsmod.entity.custom.froggy.FroggyEntity;
import hi.korperka.dsmod.entity.custom.shroooom.ShroomEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ShroomRenderer extends GeoEntityRenderer<ShroomEntity> {
    public ShroomRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ShroomModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public RenderType getRenderType(ShroomEntity animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1.4f, 1.4f, 1.4f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}