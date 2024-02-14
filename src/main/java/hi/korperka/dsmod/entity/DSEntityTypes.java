package hi.korperka.dsmod.entity;

import hi.korperka.dsmod.DSMod;
import hi.korperka.dsmod.entity.custom.froggy.FroggyEntity;
import hi.korperka.dsmod.entity.custom.shroooom.ShroomEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DSEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DSMod.MODID);

    public static final RegistryObject<EntityType<FroggyEntity>> FROGGY =
            ENTITY_TYPES.register("froggy",
                    () -> EntityType.Builder.of(FroggyEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 0.9f)
                            .build(new ResourceLocation(DSMod.MODID, "froggy").toString()));

    public static final RegistryObject<EntityType<ShroomEntity>> SHROOM =
            ENTITY_TYPES.register("shroom",
                    () -> EntityType.Builder.of(ShroomEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 0.8f)
                            .build(new ResourceLocation(DSMod.MODID, "shroom").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
