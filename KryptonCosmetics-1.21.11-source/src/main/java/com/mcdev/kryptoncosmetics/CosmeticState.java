package com.mcdev.kryptoncosmetics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class CosmeticState {
    public static final String[] CAPES = {"Aurora", "Nebula", "Ember", "Ocean"};
    public static final String[] BAGS = {"Trail Pack", "Star Satchel", "Cloud Roll", "Explorer"};
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("kryptoncosmetics.json");
    public static int capeIndex, bagIndex;
    public static boolean capeEnabled = true, bagEnabled = true, hudPreview = true;
    private CosmeticState() {}
    public static void load() {
        if (!Files.exists(FILE)) { save(); return; }
        try (Reader in = Files.newBufferedReader(FILE)) {
            Data d = GSON.fromJson(in, Data.class);
            if (d != null) { capeIndex = Math.max(0, Math.min(CAPES.length - 1, d.cape)); bagIndex = Math.max(0, Math.min(BAGS.length - 1, d.bag)); capeEnabled = d.capeEnabled; bagEnabled = d.bagEnabled; hudPreview = d.hud; }
        } catch (Exception e) { KryptonCosmeticsClient.LOGGER.error("Could not read wardrobe config", e); }
    }
    public static void save() {
        try { Files.createDirectories(FILE.getParent()); try (Writer out = Files.newBufferedWriter(FILE)) { GSON.toJson(new Data(), out); } }
        catch (Exception e) { KryptonCosmeticsClient.LOGGER.error("Could not save wardrobe config", e); }
    }
    private static final class Data { int cape = capeIndex, bag = bagIndex; boolean capeEnabled = CosmeticState.capeEnabled, bagEnabled = CosmeticState.bagEnabled, hud = hudPreview; }
}
