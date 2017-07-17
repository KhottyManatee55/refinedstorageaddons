package com.raoulvdberge.refinedstorageaddons;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class RSAddonsConfig {
    private Configuration config;

    //region Wireless Grid
    public boolean wirelessCraftingGridUsesEnergy;
    public int wirelessCraftingGridOpenUsage;
    public int wirelessCraftingGridExtractUsage;
    public int wirelessCraftingGridInsertUsage;
    public int wirelessCraftingGridCraftUsage;
    //endregion

    //region Categories
    private static final String WIRELESS_CRAFTING_GRID = "wirelessCraftingGrid";
    //endregion

    public RSAddonsConfig(File configFile) {
        config = new Configuration(configFile);

        MinecraftForge.EVENT_BUS.register(this);

        loadConfig();
    }

    public Configuration getConfig() {
        return config;
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(RSAddons.ID)) {
            loadConfig();
        }
    }

    private void loadConfig() {
        //region Wireless Grid
        wirelessCraftingGridUsesEnergy = config.getBoolean("usesEnergy", WIRELESS_CRAFTING_GRID, true, "Whether the Wireless Crafting Grid uses energy");
        wirelessCraftingGridOpenUsage = config.getInt("open", WIRELESS_CRAFTING_GRID, 30, 0, Integer.MAX_VALUE, "The energy used by the Wireless Crafting Grid to open");
        wirelessCraftingGridInsertUsage = config.getInt("insert", WIRELESS_CRAFTING_GRID, 3, 0, Integer.MAX_VALUE, "The energy used by the Wireless Crafting Grid to insert items");
        wirelessCraftingGridExtractUsage = config.getInt("extract", WIRELESS_CRAFTING_GRID, 3, 0, Integer.MAX_VALUE, "The energy used by the Wireless Crafting Grid to extract items");
        wirelessCraftingGridCraftUsage = config.getInt("craft", WIRELESS_CRAFTING_GRID, 1, 0, Integer.MAX_VALUE, "The energy used by the Wireless Crafting Grid to craft a single item");
        //endregion

        if (config.hasChanged()) {
            config.save();
        }
    }

    @SuppressWarnings("unchecked")
    public List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<>();

        list.addAll(new ConfigElement(config.getCategory(WIRELESS_CRAFTING_GRID)).getChildElements());

        return list;
    }
}
