package net.theawesomegem.fishingmadebetter.common.loottable;

import org.apache.logging.log4j.Level;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.proxy.CommonProxy;

public class LootHandler {
	public static final ResourceLocation FMB_JUNK = new ResourceLocation(ModInfo.MOD_ID, "fishing_junk");
	public static final ResourceLocation FMB_TREASURE = new ResourceLocation(ModInfo.MOD_ID, "fishing_treasure");
	
	public static final ResourceLocation FMB_AQC_JUNK = new ResourceLocation(ModInfo.MOD_ID, "fishing_aquaculture_junk");
	public static final ResourceLocation FMB_AQC_TREASURE = new ResourceLocation(ModInfo.MOD_ID, "fishing_aquaculture_treasure");
	
	public static final ResourceLocation FMB_COMBINED = new ResourceLocation(ModInfo.MOD_ID, "fishing_combined");
	
    public static void registerLootTable() {
    	LootTableList.register(FMB_JUNK);
    	LootTableList.register(FMB_TREASURE);
    	LootTableList.register(FMB_COMBINED);
    }
    
    @SubscribeEvent
    public void lootTableLoad(LootTableLoadEvent event) {
    	if(!Loader.isModLoaded("aquaculture")) return;
    	
    	if(event.getName().equals(FMB_JUNK)) {
    		CommonProxy.Logger.log(Level.INFO, "Injecting Aquaculture junk into FishingMadeBetter junk");
    		event.getTable().getPool(FMB_JUNK.toString()).addEntry(new LootEntryTable(FMB_AQC_JUNK, 125, 0, new LootCondition[0], "fmb_aqc_junk_entry_inject"));}
    	if(event.getName().equals(FMB_TREASURE)) {
    		CommonProxy.Logger.log(Level.INFO, "Injecting Aquaculture treasure into FishingMadeBetter treasure");
    		event.getTable().getPool(FMB_TREASURE.toString()).addEntry(new LootEntryTable(FMB_AQC_TREASURE, 52, 0, new LootCondition[0], "fmb_aqc_treasure_entry_inject"));
    	}
    }
}
