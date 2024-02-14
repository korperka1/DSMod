package hi.korperka.dsmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.zanckor.example.client.screen.button.TextButton;
import dev.zanckor.example.common.enumregistry.enumdialog.EnumDialogOption;
import dev.zanckor.mod.QuestApiMain;
import dev.zanckor.mod.client.screen.abstractscreen.AbstractDialog;
import dev.zanckor.mod.common.network.SendQuestPacket;
import dev.zanckor.mod.common.network.message.dialogoption.AddQuest;
import dev.zanckor.mod.common.network.message.dialogoption.DialogRequestPacket;
import dev.zanckor.mod.common.network.message.dialogoption.DisplayDialog;
import dev.zanckor.mod.common.network.message.screen.OpenVanillaEntityScreen;
import dev.zanckor.mod.common.util.MCUtilClient;
import hi.korperka.dsmod.entity.custom.froggy.FroggyEntity;
import hi.korperka.dsmod.entity.custom.shroooom.ShroomEntity;
import hi.korperka.dsmod.sounds.DSSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CustomScreen extends AbstractDialog {
    SoundEvent voice;
    int dialogID;
    String text;
    int textDisplayDelay;
    int textDisplaySize;
    int optionSize;
    HashMap<Integer, List<Integer>> optionIntegers;
    HashMap<Integer, List<String>> optionStrings;

    double xScreenPos, yScreenPos, scale;
    int imageWidth, imageHeight;
    int xButtonPosition, yButtonPosition;
    Entity entity;
    UUID npcUUID;
    String resourceLocation;
    Item item;
    DisplayDialog.NpcType npcType;

    private final static ResourceLocation DIALOG = new ResourceLocation(QuestApiMain.MOD_ID, "textures/gui/dialog_background.png");
    private final static ResourceLocation BUTTON = new ResourceLocation(QuestApiMain.MOD_ID, "textures/gui/dialog_button.png");

    public CustomScreen(Component component) {
        super(component);
    }

    @Override
    public Screen modifyScreen(int dialogID, String text, int optionSize, HashMap<Integer, List<Integer>> optionIntegers, HashMap<Integer, List<String>> optionStrings, UUID npcUUID, String resourceLocation, Item item, DisplayDialog.NpcType npcType) {
        this.dialogID = dialogID;
        this.text = I18n.get(text);
        this.optionSize = optionSize;
        this.optionIntegers = optionIntegers;
        this.optionStrings = optionStrings;
        this.npcType = npcType;

        switch (npcType) {
            case RESOURCE_LOCATION -> {
                EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(resourceLocation));
                entity = entityType.create(Minecraft.getInstance().level);
            }

            case UUID -> entity = MCUtilClient.getEntityByUUID(npcUUID);
            case ITEM -> this.item = item;
        }

        this.npcUUID = npcUUID;

        if (entity != null) {
            if(entity instanceof FroggyEntity)
                voice = DSSounds.FROGGY_VOICE.get();

            else if (entity instanceof ShroomEntity)
                voice = SoundEvents.AMETHYST_BLOCK_CHIME;

            else
                voice = SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON;
        }

        return this;
    }

    @Override
    protected void init() {
        super.init();

        textDisplaySize = 0;
        imageWidth = width / 2;
        imageHeight = (int) (width / 2.7);
        xScreenPos = width - (imageWidth);
        yScreenPos = (double) width / 11;
        scale = ((float) width) / 700;

        xButtonPosition = (int) (width / 3.55);
        yButtonPosition = (int) (yScreenPos * 3.6);

        for (int i = 0; i < optionSize; i++) {
            int stringLength = (int) ((optionStrings.get(i).get(0).length() + 1) * 5.5);
            int index = i;


            if (xButtonPosition + stringLength > (width / 1.4)) {
                xButtonPosition = (int) (width / 3.55);
                yButtonPosition += (int) (22 * scale);
            }

            addRenderableWidget(new TextButton(xButtonPosition, yButtonPosition, stringLength, 20, ((float) width) / 675,
                    Component.literal(I18n.get(optionStrings.get(i).get(0))), 26, button -> button(index)));

            xButtonPosition += (int) (optionStrings.get(i).get(0).length() * 5.7 + 20);
        }

        addRenderableWidget(new TextButton((int) (imageWidth * 1.4), (int) (imageHeight * 1.1), (int) (20 * scale), (int) (20 * scale), ((float) width) / 675,
                Component.literal("↩").withStyle(ChatFormatting.BOLD), 26, button -> {
            if (npcUUID != null) SendQuestPacket.TO_SERVER(new OpenVanillaEntityScreen(npcUUID));
        }));
    }

    @Override
    public void tick() {
        super.tick();

        if (textDisplaySize < text.length()) {
            if (textDisplayDelay == 0) {
                MCUtilClient.playSound(voice, 0.75f, 1.5f);
                textDisplaySize++;

                if (textDisplaySize < text.length()) {
                    switch (Character.toString(text.charAt(textDisplaySize))) {
                        case ".", "?", "!" -> textDisplayDelay = 7;
                        case "," -> textDisplayDelay = 5;
                    }
                }
            } else {
                textDisplayDelay--;
            }
        }
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        int xPosition = (int) (width / 2.41);
        int yPosition = (int) (yScreenPos + yScreenPos / 1.45);

        RenderSystem._setShaderTexture(0, DIALOG);
        blit(poseStack, (int)(this.xScreenPos - (this.imageWidth / 2)), (int)this.yScreenPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        MCUtilClient.renderText(poseStack, xPosition, yPosition, 26, (float) width / 675, 42, text.substring(0, textDisplaySize), font);

        switch (npcType) {
            case UUID, RESOURCE_LOCATION -> {
                if (entity != null) MCUtilClient.renderEntity(
                        xScreenPos / 1.4575, yScreenPos * 3.41, width / 12,
                        (xScreenPos / 1.4575 - mouseX) / 4, (yScreenPos * 2.5 - mouseY) / 4,
                        (LivingEntity) entity);
            }

            case ITEM -> MCUtilClient.renderItem(item.getDefaultInstance(),
                    (int) (xScreenPos / 1.4575), (int) (yScreenPos * 2.5), width / 150,
                    0, poseStack);
        }

        super.render(poseStack, mouseX, mouseY, partialTicks);
    }

    private void button(int optionID) {
        EnumDialogOption optionType = EnumDialogOption.valueOf(optionStrings.get(optionID).get(1));

        switch (optionType) {
            case OPEN_DIALOG, CLOSE_DIALOG:
                SendQuestPacket.TO_SERVER(new DialogRequestPacket(optionType, optionID, entity, resourceLocation, item, npcType));
                break;
            case ADD_QUEST:
                SendQuestPacket.TO_SERVER(new AddQuest(optionType, optionID));
                break;
        }
    }

    @Override
    public boolean mouseClicked(double xPosition, double yPosition, int button) {
        textDisplaySize = text.length();

        return super.mouseClicked(xPosition, yPosition, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}