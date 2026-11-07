package com.mcdev.kryptoncosmetics;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudElementRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KryptonCosmeticsClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("kryptoncosmetics");
    private static KeyBinding openWardrobe;
    @Override public void onInitializeClient() {
        CosmeticState.load();
        KeyBinding.Category category = KeyBinding.Category.create(Identifier.of("kryptoncosmetics", "controls"));
        openWardrobe = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.kryptoncosmetics.open", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, category));
        ClientTickEvents.END_CLIENT_TICK.register(client -> { while (openWardrobe.wasPressed()) if (client.currentScreen == null) client.setScreen(new WardrobeScreen()); });
        HudElementRegistry.addLast(Identifier.of("kryptoncosmetics", "wardrobe_hud"), (drawContext, tickCounter) -> WardrobeScreen.renderHud(drawContext));
    }
}
