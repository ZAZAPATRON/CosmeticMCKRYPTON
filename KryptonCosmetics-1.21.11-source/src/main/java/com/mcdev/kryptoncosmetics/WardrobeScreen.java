package com.mcdev.kryptoncosmetics;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public final class WardrobeScreen extends Screen {
    private int px, py;
    public WardrobeScreen() { super(Text.literal("Krypton Cosmetics")); }
    @Override protected void init() { px = (width - 430) / 2; py = (height - 270) / 2; }
    @Override public void render(DrawContext c, int mx, int my, float delta) {
        c.fill(0, 0, width, height, 0x99070A12);
        c.fill(px, py, px + 430, py + 270, 0xF0141825);
        c.fill(px, py, px + 430, py + 3, 0xFF8C70FF);
        c.drawText(textRenderer, "KRYPTON COSMETICS", px + 16, py + 14, 0xFFFFFFFF, false);
        c.drawText(textRenderer, "WARDROBE     /     ORIGINAL PIXEL DESIGNS", px + 16, py + 30, 0xFF9AA5C3, false);
        c.fill(px + 16, py + 52, px + 157, py + 244, 0xFF1B2030);
        c.fill(px + 273, py + 52, px + 414, py + 244, 0xFF1B2030);
        c.drawText(textRenderer, "CAPES", px + 26, py + 63, 0xFF9FEBD5, false);
        c.drawText(textRenderer, "BACKPACKS", px + 283, py + 63, 0xFFFFD28A, false);
        // Medium-sized character preview, with the currently selected accessories drawn on the avatar.
        drawAvatar(c, px + 172, py + 70);
        c.drawCenteredTextWithShadow(textRenderer, "YOUR LOOK", px + 215, py + 224, 0xFFD9DDF0);
        row(c, px + 25, py + 91, "<  " + CosmeticState.CAPES[CosmeticState.capeIndex] + "  >", CosmeticState.capeEnabled);
        row(c, px + 282, py + 91, "<  " + CosmeticState.BAGS[CosmeticState.bagIndex] + "  >", CosmeticState.bagEnabled);
        c.drawCenteredTextWithShadow(textRenderer, "Click to change", px + 86, py + 129, 0xFF8E98B3);
        c.drawCenteredTextWithShadow(textRenderer, "Click to change", px + 343, py + 129, 0xFF8E98B3);
        c.fill(px + 28, py + 158, px + 145, py + 184, 0xFF2B3247);
        c.drawCenteredTextWithShadow(textRenderer, "Cape  " + (CosmeticState.capeEnabled ? "ON" : "OFF"), px + 86, py + 167, 0xFFFFFFFF);
        c.fill(px + 285, py + 158, px + 402, py + 184, 0xFF2B3247);
        c.drawCenteredTextWithShadow(textRenderer, "Pack  " + (CosmeticState.bagEnabled ? "ON" : "OFF"), px + 343, py + 167, 0xFFFFFFFF);
        c.fill(px + 16, py + 250, px + 414, py + 251, 0xFF343B50);
        c.drawText(textRenderer, "Right Shift: open  •  Saved locally  •  No server packets", px + 16, py + 257, 0xFF929DB8, false);
        super.render(c, mx, my, delta);
    }
    private void row(DrawContext c, int x, int y, String text, boolean enabled) {
        c.drawCenteredTextWithShadow(textRenderer, text, x + 60, y, enabled ? 0xFFFFFFFF : 0xFF70788D);
    }
    private void drawAvatar(DrawContext c, int x, int y) {
        int skin = 0xFFF0BE98, shirt = 0xFF4A77D4;
        c.fill(x + 23, y + 1, x + 56, y + 34, 0xFF171A25);
        c.fill(x + 28, y + 4, x + 51, y + 28, skin);
        c.fill(x + 27, y + 3, x + 52, y + 12, 0xFF543729);
        c.fill(x + 25, y + 35, x + 54, y + 89, shirt);
        c.fill(x + 9, y + 37, x + 24, y + 83, skin);
        c.fill(x + 55, y + 37, x + 70, y + 83, skin);
        c.fill(x + 27, y + 89, x + 39, y + 145, 0xFF343A50);
        c.fill(x + 41, y + 89, x + 53, y + 145, 0xFF343A50);
        c.fill(x + 21, y + 143, x + 40, y + 151, 0xFF202433);
        c.fill(x + 41, y + 143, x + 60, y + 151, 0xFF202433);
        if (CosmeticState.capeEnabled) {
            int[] colors = {0xFF57E4C0, 0xFFB57AFF, 0xFFFF795E, 0xFF43B9FF};
            int color = colors[CosmeticState.capeIndex];
            c.fill(x + 22, y + 37, x + 12, y + 91, color);
            c.fill(x + 12, y + 88, x + 18, y + 94, color);
            c.fill(x + 15, y + 49, x + 19, y + 53, 0xFFFFFFFF);
            c.fill(x + 15, y + 65, x + 19, y + 69, 0xFFFFFFFF);
        }
        if (CosmeticState.bagEnabled) {
            int[] colors = {0xFF32D6A0, 0xFFFFC857, 0xFF85B9FF, 0xFFE78BFF};
            int color = colors[CosmeticState.bagIndex];
            c.fill(x + 48, y + 43, x + 61, y + 70, color);
            c.fill(x + 50, y + 50, x + 60, y + 62, 0xFF303648);
            c.fill(x + 52, y + 46, x + 57, y + 49, 0xFFFFE9A2);
        }
        c.fill(x - 3, y + 153, x + 80, y + 156, 0xFF8C70FF);
    }
    @Override public boolean mouseClicked(double mx, double my, int button) {
        if (button != 0) return super.mouseClicked(mx, my, button);
        if (hit(mx, my, px + 16, py + 80, 141, 62)) CosmeticState.capeIndex = (CosmeticState.capeIndex + 1) % CosmeticState.CAPES.length;
        else if (hit(mx, my, px + 273, py + 80, 141, 62)) CosmeticState.bagIndex = (CosmeticState.bagIndex + 1) % CosmeticState.BAGS.length;
        else if (hit(mx, my, px + 28, py + 158, 117, 26)) CosmeticState.capeEnabled = !CosmeticState.capeEnabled;
        else if (hit(mx, my, px + 285, py + 158, 117, 26)) CosmeticState.bagEnabled = !CosmeticState.bagEnabled;
        else return super.mouseClicked(mx, my, button);
        CosmeticState.save(); return true;
    }
    private static boolean hit(double mx, double my, int x, int y, int w, int h) { return mx >= x && mx <= x + w && my >= y && my <= y + h; }
    public static void renderHud(DrawContext c) {
        if (!CosmeticState.hudPreview) return;
        var tr = MinecraftClient.getInstance().textRenderer;
        c.fill(10, 10, 151, 43, 0xA8141825); c.fill(10, 10, 12, 43, 0xFF8C70FF);
        c.drawText(tr, "WARDROBE  /  LIVE", 19, 15, 0xFFDCD5FF, false);
        c.drawText(tr, (CosmeticState.capeEnabled ? "Cape: " + CosmeticState.CAPES[CosmeticState.capeIndex] : "Cape: off"), 19, 28, 0xFF9FEBD5, false);
    }
}
