package hi.korperka.dsmod.listeners;

import dev.zanckor.api.filemanager.dialog.register.LoadDialog;
import dev.zanckor.api.filemanager.quest.register.LoadQuest;
import dev.zanckor.api.screen.ScreenRegistry;
import hi.korperka.dsmod.DSMod;
import hi.korperka.dsmod.client.CustomScreen;
import hi.korperka.dsmod.entity.DSEntityTypes;
import hi.korperka.dsmod.entity.custom.froggy.FroggyEntity;
import hi.korperka.dsmod.entity.custom.shroooom.ShroomEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class EventListener {
    @Mod.EventBusSubscriber(modid = DSMod.MODID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onServerLoad(ServerStartedEvent event) {
            MinecraftServer server = event.getServer();

            LoadQuest.registerQuest(server, DSMod.MODID);
            LoadDialog.registerDialog(server, DSMod.MODID);
            ScreenRegistry.registerDialogScreen(DSMod.MODID, new CustomScreen(Component.literal("custom_screen")));
        }
    }

    @Mod.EventBusSubscriber(modid = DSMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class EventBusEvents {

        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(DSEntityTypes.FROGGY.get(), FroggyEntity.setAttributes());
            event.put(DSEntityTypes.SHROOM.get(), ShroomEntity.setAttributes());
        }
    }
}
