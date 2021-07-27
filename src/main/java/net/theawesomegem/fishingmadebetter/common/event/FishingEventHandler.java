package net.theawesomegem.fishingmadebetter.common.event;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.theawesomegem.fishingmadebetter.BetterFishUtil;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.FishPopulation;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.FishingCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.IFishingData;
import net.theawesomegem.fishingmadebetter.common.capability.world.ChunkCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.world.IChunkFishingData;
import net.theawesomegem.fishingmadebetter.common.capability.world.PopulationData;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishCaughtData;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.data.FishData.FishingLiquid;
import net.theawesomegem.fishingmadebetter.common.data.WeightedRandomFishPopulation;
import net.theawesomegem.fishingmadebetter.common.entity.EntityFMBCustomFishHook;
import net.theawesomegem.fishingmadebetter.common.entity.EntityFMBLavaFishHook;
import net.theawesomegem.fishingmadebetter.common.entity.EntityFMBVoidFishHook;
import net.theawesomegem.fishingmadebetter.common.item.attachment.hook.ItemHook;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemBetterFishingRod;
import net.theawesomegem.fishingmadebetter.common.item.tracker.ItemFishTracker;
import net.theawesomegem.fishingmadebetter.common.item.tracker.ItemFishTracker.TrackingVision;
import net.theawesomegem.fishingmadebetter.common.loottable.LootHandler;
import net.theawesomegem.fishingmadebetter.common.networking.PrimaryPacketHandler;
import net.theawesomegem.fishingmadebetter.common.networking.packet.PacketReelingC;
import net.theawesomegem.fishingmadebetter.common.networking.packet.PacketUseRodC;
import net.theawesomegem.fishingmadebetter.common.networking.packet.PacketKeybindS.Keybind;
import net.theawesomegem.fishingmadebetter.common.registry.RegistryManager.RegistryHandler;
import net.theawesomegem.fishingmadebetter.util.ItemStackUtil;
import net.theawesomegem.fishingmadebetter.util.MathUtil;
import net.theawesomegem.fishingmadebetter.util.RandomUtil;
import net.theawesomegem.fishingmadebetter.util.TimeUtil;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by TheAwesomeGem on 12/27/2017.
 */
public class FishingEventHandler {//God this handler is a mess 
    enum HookState {
        FLYING,
        HOOKED_IN_ENTITY,
        BOBBING
    }
    
    final static Field currentStateField = ObfuscationReflectionHelper.findField(EntityFishHook.class, "field_190627_av");
    final static Field biteIntervalField = ObfuscationReflectionHelper.findField(EntityFishHook.class, "field_146045_ax");
    final static Field delayBeforeBiteField = ObfuscationReflectionHelper.findField(EntityFishHook.class, "field_146038_az");
    final static Field delayBeforeSwimmingToHookField = ObfuscationReflectionHelper.findField(EntityFishHook.class, "field_146040_ay");
    
    @SubscribeEvent
    public void onPlayerFish(ItemFishedEvent e) {
        EntityPlayer player = e.getEntityPlayer();
        if(player == null || !player.isEntityAlive()) return;

        World world = player.world;
        if(world.isRemote) return;

        if(usingVanillaFishingRod(player)) return;

        IFishingData fishingData = player.getCapability(FishingCapabilityProvider.FISHING_DATA_CAP, null);
        if(fishingData == null) {
            e.setCanceled(true);
            return;
        }

        IChunkFishingData chunkFishingData = world.getChunkFromBlockCoords(e.getHookEntity().getPosition()).getCapability(ChunkCapabilityProvider.CHUNK_FISHING_DATA_CAP, null);
        if(chunkFishingData == null) {
            e.setCanceled(true);
            return;
        }
        
        if(!fishingData.isFishing()) {
            e.setCanceled(true);
            return;
        }
        
        FishCaughtData fishCaughtData = fishingData.getFishCaughtData();
        if(fishCaughtData == null) {
            e.setCanceled(true);
            return;
        }
        
        e.setCanceled(true);
        
        if(fishingData.getFishDistance() >= fishingData.getFishDeepLevel()) {
        	ItemStack fishingRod = getBetterFishingRod(player);
            ItemHook hookAttachment = ItemBetterFishingRod.getHookItem(fishingRod);
            int weightModifier = hookAttachment.getWeightModifier();
            int treasureChance = 15 + hookAttachment.getTreasureModifier();
            
            EntityFishHook hook = e.getHookEntity();
            ItemStack treasure = ItemStack.EMPTY;
            
            if(treasureChance > RandomUtil.getRandomInRange(player.getRNG(), 0, 100)) {
                LootContext.Builder lootBuilder = new LootContext.Builder((WorldServer)world);
                lootBuilder.withLuck(EnchantmentHelper.getFishingLuckBonus(fishingRod) + player.getLuck()).withPlayer(player).withLootedEntity(hook);
                List<ItemStack> result = world.getLootTableManager().getLootTableFromLocation(LootHandler.FMB_COMBINED).generateLootForPools(player.getRNG(), lootBuilder.build());
                
                if(result.size()>0) treasure = result.get(0);
            }
            
            
            ItemStack fishStack = getFishItemStack(fishCaughtData, world.getTotalWorldTime(), weightModifier);
            EntityItem entityFishItem = new EntityItem(player.world, hook.posX, hook.posY, hook.posZ, fishStack);

            if(ConfigurationManager.server.magneticFishing) {
            	entityFishItem.setPosition(player.posX, player.posY + 1, player.posZ);
            } 
            else {
                double d0 = player.posX - hook.posX;
                double d1 = player.posY - hook.posY;
                double d2 = player.posZ - hook.posZ;
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                entityFishItem.motionX = d0 * 0.1D;
                entityFishItem.motionY = d1 * 0.1D + (double) MathHelper.sqrt(d3) * 0.08D;
                entityFishItem.motionZ = d2 * 0.1D;
            }
            player.world.spawnEntity(entityFishItem);
            
            
            if(!treasure.isEmpty()) {
            	EntityItem entityTreasureItem = new EntityItem(player.world, hook.posX, hook.posY, hook.posZ, treasure);

                if(ConfigurationManager.server.magneticFishing) {
                	entityTreasureItem.setPosition(player.posX, player.posY + 1, player.posZ);
                } 
                else {
                    double d0 = player.posX - hook.posX;
                    double d1 = player.posY - hook.posY;
                    double d2 = player.posZ - hook.posZ;
                    double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    entityTreasureItem.motionX = d0 * 0.1D;
                    entityTreasureItem.motionY = d1 * 0.1D + (double) MathHelper.sqrt(d3) * 0.08D;
                    entityTreasureItem.motionZ = d2 * 0.1D;
                }
                player.world.spawnEntity(entityTreasureItem);
                player.sendMessage(new TextComponentString("You snagged something extra on the line!"));
            }

            
            chunkFishingData.reducePopulation(fishCaughtData.fishId, 1, world.getTotalWorldTime(), true);
            player.world.spawnEntity(new EntityXPOrb(player.world, player.posX, player.posY + 0.5D, player.posZ + 0.5D, player.getRNG().nextInt(6) + 1));//TODO: exp based on fish rarity
        }

        fishingData.reset();
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent e) {
        EntityPlayer player = e.player;
        if(player.world.isRemote || e.phase == TickEvent.Phase.END) return;

        if(player.ticksExisted %5 == 0) checkForFishInventory(player);

        IFishingData fishingData = player.getCapability(FishingCapabilityProvider.FISHING_DATA_CAP, null);
        if(fishingData == null) return;

        checkTracking(player, fishingData);
        updateFishingData(player, fishingData);
        
        EntityFishHook hook = player.fishEntity;
        if(hook == null || hook.isDead) {
        	fishingData.setUsingVanillaRod(false);//Prevents fuckery from trying to swap rods in the middle of fishing
            fishingData.reset();
            return;
        }

        if(player.getHeldItem(EnumHand.MAIN_HAND) != null && player.getHeldItem(EnumHand.OFF_HAND) != null && player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemFishingRod && player.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemFishingRod) {
        	fishingData.reset();//Prevent dual-wielding to mix rods
        	hook.setDead();
        	return;
        }
        
        if(usingVanillaFishingRod(player)) {//If you're actively using a vanilla rod, mark it and return
        	fishingData.setUsingVanillaRod(true);
        	fishingData.reset();
        	return;
        }
        
        if(fishingData.getUsingVanillaRod()) {//If you were actively using a vanilla rod last tick, and now you're not, but still fishing, kill hook (Rest of logic handled in ItemBetterFishingRod)
        	fishingData.setUsingVanillaRod(false);
        	fishingData.reset();
        	hook.setDead();
        	return;
        }

        ItemStack betterFishingRodStack = getBetterFishingRod(player);
        if(betterFishingRodStack == null || betterFishingRodStack.isEmpty()) {
            fishingData.reset();
            return;
        }
        
        if(fishingData.getTimeBeforeFishSwimToHook() == 0) {//Overrides vanilla's fish swimming to bobber timing, replaces it with getTimeBeforeFishSwimToHook
            if(getDelayBeforeSwimmingToHook(hook) > 20) setLureSpeed(hook, 20);
        } 
        else setLureSpeed(hook, 200);

        Enum hookState = getHookState(hook);
        if(hookState == null || !hookState.equals(HookState.BOBBING)) {
            fishingData.reset();
            return;
        }
        
        if(fishingData.isFishing()) {
        	int distanceTime = fishingData.getFishDistanceTime();
            if(distanceTime <= 0) {
                distanceTime = getDistanceTime(fishingData.getFishWeight());

                int reelDiff = fishingData.getReelTarget() - fishingData.getReelAmount();
                reelDiff = Math.abs(reelDiff);
                
                int distanceMod = (reelDiff <= fishingData.getErrorVariance()) ? ItemBetterFishingRod.getReelItem(betterFishingRodStack).getReelSpeed() : -1;
                fishingData.setFishDistance(Math.min(fishingData.getFishDeepLevel(), Math.max(0, fishingData.getFishDistance() + distanceMod)));
            } 
            fishingData.setFishDistanceTime(distanceTime - 1);
        }

        World world = hook.world;
        Chunk chunk = world.getChunkFromBlockCoords(hook.getPosition());
        IChunkFishingData chunkFishingData = getChunkFishingData(chunk);

        if(fishingData.getFishPopulation() == null && !fishingData.isFishing()) {
        	boolean justChecked = false;
        	
            if(world.getWorldTime() %60 == 0) {
            	fishingData.setFishPopulation(getSelectedFishPop(player, chunkFishingData));//Only check every 3 seconds so as to not spam lots of logic
            	justChecked = true;
            }

            if(fishingData.getFishPopulation() == null) {
                fishingData.reset();
                if(justChecked && world.getWorldTime() %180 == 0) player.sendMessage(new TextComponentString("There doesn't seem to be anything biting here"));//Output failure message every 9 seconds, only after checking
                return;
            }
        }

        if(fishingData.getTimeBeforeFishSwimToHook() == -1 && !fishingData.isFishing()) {//Only reached once hook is in water, but before fishing starts, sets speed to catch a fish
            fishingData.setTimeBeforeFishSwimToHook(getLureSpeed(player, fishingData.getFishPopulation()));
        }

        int delayBeforeBite = getDelayBeforeBite(hook);
        if(delayBeforeBite > 0 && delayBeforeBite <= 10) {
            //Fish just bit the hook
            if(fishingData.getLastFailedFishing() <= 0) {
                if(!fishingData.isFishing()) {//Brackets need to be around all this, otherwise it spams it for 10 ticks
                	if(hook instanceof EntityFMBLavaFishHook) player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    else if(hook instanceof EntityFMBVoidFishHook) player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDEREYE_DEATH, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    else player.world.playSound(null, player.posX, player.posY, player.posZ, RegistryHandler.FISH_SPLASHING_EVENT, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    
                    if(ConfigurationManager.server.looseBait) ItemBetterFishingRod.removeBait(betterFishingRodStack);
                    
                    //This is gross, will redo better eventually
                    Integer[] minigameBackground = new Integer[5];
                    minigameBackground[0] = world.provider.getDimension();//dimension override
                    minigameBackground[1] = hook.posY<56 ? 1 : 0;//y level cave override
                    minigameBackground[2] = world.isDaytime() ? 0 : 1;//time
                    if(hook instanceof EntityFMBLavaFishHook) minigameBackground[3] = 1;//liquid
                    else if(hook instanceof EntityFMBVoidFishHook) minigameBackground[3] = 2;
                    else minigameBackground[3] = 0;
                    minigameBackground[4] = textureIndexFromBiome(chunk.getBiome(hook.getPosition(), world.getBiomeProvider()));//biome
                    
                    fishingData.startFishing(player.getRNG(), betterFishingRodStack, minigameBackground);
                    player.sendMessage(new TextComponentString("A bite!"));
                }
            }
            
            if(ConfigurationManager.server.autoReel && player.ticksExisted%3==0 && fishingData.getFishDistance()==fishingData.getFishDeepLevel() && fishingData.getFishDistance()!=0) {
            	PrimaryPacketHandler.INSTANCE.sendTo(new PacketUseRodC(true), (EntityPlayerMP)player);//Attempt to automatically right-click
            }
            
            if(fishingData.getFishTime() > 0 && fishingData.getLineBreak() < 60) {
                spawnParticleBasedOnFishSpeed(player.world, hook, fishingData);
                setBiteInterval(hook, 20);
            }
            else {
            	if(fishingData.isFishing() && fishingData.getFishTime() <= 0) player.sendMessage(new TextComponentString("Took too long, the fish got away!"));
            	else if(fishingData.isFishing() && fishingData.getLineBreak() == 60) player.sendMessage(new TextComponentString("Line snapped! The fish got away!"));
            	setBiteInterval(hook, 0);
                fishingData.setLastFailedFishing(20);
                fishingData.reset();
            }
        } 
        else fishingData.reset(false);
    }

    @SubscribeEvent
    public void onChunkLoad(ChunkEvent.Load e) {
        if(e.getWorld().isRemote) return;

        Chunk chunk = e.getChunk();
        IChunkFishingData chunkFishingData = getChunkFishingData(chunk);
        chunkFishingData.chunkLoad(chunk);
    }
    
    @SubscribeEvent
    public void onItemToss(ItemTossEvent event) {//Charm dupe patch
    	ItemStack stack = event.getEntityItem().getItem();
    	if(!(stack.getItem() instanceof ItemBetterFishingRod)) return;
    	if(!(ConfigurationManager.server.charmSalvagePatch)) return;
    	
    	if(stack.getItemDamage() >= stack.getMaxDamage()) {
    		ItemBetterFishingRod.removeReelItem(stack);
    		ItemBetterFishingRod.removeBobberItem(stack);
    		ItemBetterFishingRod.removeHookItem(stack);
    	}
    }
    
    private int textureIndexFromBiome(Biome biome) {//TODO: config override
    	if(BiomeDictionary.hasType(biome, Type.MUSHROOM)) return 8;
    	else if(BiomeDictionary.hasType(biome, Type.DEAD) || BiomeDictionary.hasType(biome, Type.SPOOKY)) return 9;
    	else if(BiomeDictionary.hasType(biome, Type.JUNGLE)) return 1;
    	else if(BiomeDictionary.hasType(biome, Type.SWAMP)) return 7;
    	else if(BiomeDictionary.hasType(biome, Type.HOT) || BiomeDictionary.hasType(biome, Type.SANDY)) return 2;
    	else if(BiomeDictionary.hasType(biome, Type.COLD)) return 3;
    	else if(BiomeDictionary.hasType(biome, Type.MOUNTAIN)) return 4;
    	else if(BiomeDictionary.hasType(biome, Type.FOREST)) return 5;
    	else if(BiomeDictionary.hasType(biome, Type.PLAINS) || BiomeDictionary.hasType(biome, Type.RIVER)) return 6;
    	else return 0;
    }

    private void checkForFishInventory(EntityPlayer player) {
        InventoryPlayer playerInventory = player.inventory;
        long currentTime = player.world.getTotalWorldTime();

        for(int i = 0; i < playerInventory.getSizeInventory(); i++) {
            ItemStack itemStack = playerInventory.getStackInSlot(i);

            if(itemStack.isEmpty() || !BetterFishUtil.isBetterFish(itemStack)) continue;

            List<String> tooltipList = new ArrayList<>();
            tooltipList.add(String.format("Weight: %d", BetterFishUtil.getFishWeight(itemStack)));
            if(CustomConfigurationHandler.fishDataMap.get(BetterFishUtil.getFishId(itemStack)).allowScaling) tooltipList.add(String.format("Scale: %s", BetterFishUtil.doesFishHasScale(itemStack) ? "Attached" : "Detached"));
            tooltipList.add(TextFormatting.BLUE + "" + TextFormatting.BOLD + (BetterFishUtil.isDead(itemStack, currentTime) ? "Dead" : "Alive") + TextFormatting.RESET);
            itemStack = ItemStackUtil.appendToolTip(itemStack, tooltipList);

            if(BetterFishUtil.isDead(itemStack, currentTime)) BetterFishUtil.setFishCaughtTime(itemStack, 0);
        }
    }

    private void checkTracking(EntityPlayer player, IFishingData fishingData) {
        if(fishingData.getTimeSinceTracking() <= 0) return;

        ItemStack mainHandItem = player.getHeldItem(EnumHand.MAIN_HAND);
        ItemStack offHandItem = player.getHeldItem(EnumHand.OFF_HAND);

        if((!(mainHandItem.getItem() instanceof ItemFishTracker)) && (!(offHandItem.getItem() instanceof ItemFishTracker))) {
            fishingData.setTimeSinceTracking(0);
            player.sendMessage(new TextComponentString("You must be holding a fish tracker either on your main hand or your off hand."));
        } 
        else if(mainHandItem.getItem() instanceof ItemFishTracker && offHandItem.getItem() instanceof ItemFishTracker) {
            fishingData.setTimeSinceTracking(0);
            player.sendMessage(new TextComponentString("You cannot hold fish tracker on both hands."));
        } 
        else {
            ItemStack fishTrackerItem = (mainHandItem.getItem() instanceof ItemFishTracker) ? mainHandItem : offHandItem;
            fishingData.setTimeSinceTracking(fishingData.getTimeSinceTracking() + 2);
            long maxTrackingTime = player.world.getTotalWorldTime() + TimeUtil.secondsToMinecraftTicks(ConfigurationManager.server.baitBoxUpdateInterval);

            if(fishingData.getTimeSinceTracking() >= maxTrackingTime) {
                fishingData.setTimeSinceTracking(0);
                getTrackingFish(player, (ItemFishTracker) fishTrackerItem.getItem());
                player.sendMessage(new TextComponentString("You have finished tracking."));
            }
        }
    }

    private void getTrackingFish(EntityPlayer player, ItemFishTracker item) {
        World world = player.world;
        Chunk chunk = world.getChunkFromBlockCoords(player.getPosition());
        IChunkFishingData chunkFishingData = getChunkFishingData(chunk);
        if(chunkFishingData == null) return;

        boolean fishFound = false;
        int underYCount = 0;
        int trackerQualityCount = 0;
        int hibernatingCount = 0;
        
        player.sendMessage(new TextComponentString("-----"));
        
        for(PopulationData populationData : chunkFishingData.getFishes(world.getTotalWorldTime()).values()) {
            FishData fishData = CustomConfigurationHandler.fishDataMap.get(populationData.getFishType());
            if(fishData == null) continue;
            if(!fishData.trackable) continue;
            if(!fishData.liquid.equals(FishingLiquid.ANY) && !item.getLiquidEnum().equals(fishData.liquid)) continue;
            if(fishData.minYLevel > player.posY) continue;

            fishFound = true;
            
            if(fishData.maxYLevel < player.posY) {
            	underYCount++;
            	continue;
            }
            if(fishData.minDeepLevel > item.getMaxDepth()) {
            	trackerQualityCount++;
            	continue;
            }
            if(fishData.rarity < TrackingVision.getMinRarity(item.getTrackingVision())) {
            	trackerQualityCount++;
            	continue;
            }
            if((fishData.time.equals(FishData.TimeToFish.DAY) && (!world.isDaytime())) || (fishData.time.equals(FishData.TimeToFish.NIGHT) && (world.isDaytime()))) {
            	hibernatingCount++;
            	continue;
            }
            if(fishData.maxLightLevel < world.getLight(player.getPosition())) {
            	hibernatingCount++;
            	continue;
            }
            if((fishData.rainRequired && (!world.isRaining())) || (fishData.thunderRequired && (!world.isThundering()))) {
            	hibernatingCount++;
            	continue;
            }

            int pop = populationData.getQuantity();
            String quantity;

            if(pop > 50) quantity = "immense";
            else if(pop > 40) quantity = "abundant";
            else if(pop > 30) quantity = "ample";
            else if(pop > 20) quantity = "substantial";
            else if(pop > 10) quantity = "numerous";
            else if(pop > 3) quantity = "light";
            else if(pop > 1) quantity = "sparse";
            else quantity = "meager";

            player.sendMessage(new TextComponentString(String.format("Detected %s in %s quantity.", fishData.description, quantity)));
        }
        
        if(!fishFound) {
        	player.sendMessage(new TextComponentString("Probe failed to detect any fish in this location"));
        }
        else {
        	if(underYCount > 0) {
        		player.sendMessage(new TextComponentString(String.format("Detected traces of %s fish living at a lower altitiude.", underYCount)));
        	}
        	if(trackerQualityCount > 0) {
        		player.sendMessage(new TextComponentString(String.format("Detected traces of %s fish outside of the probe's ability.", trackerQualityCount)));
        	}
        	if(hibernatingCount > 0) {
        		player.sendMessage(new TextComponentString(String.format("Detected %s fish currently not feeding in these conditions.", hibernatingCount)));
        	}
        }
        
    	player.sendMessage(new TextComponentString("-----"));
    }

    private int getDistanceTime(int weight) {
        return (int)(1D+(Math.cbrt((double)(weight*weight))/10D));
    }

    private ItemStack getFishItemStack(FishCaughtData fishCaughtData, long currentTime, int weightModifier) {
        Item item = Item.getByNameOrId(fishCaughtData.itemId);
        if(item==null) return ItemStack.EMPTY;

        ItemStack itemStack = new ItemStack(item, 1, fishCaughtData.itemMetaData);
        if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());

        NBTTagCompound tagCompound = itemStack.getTagCompound();
        tagCompound.setString("FishId", fishCaughtData.fishId);
        tagCompound.setInteger("FishWeight", (int)((float)fishCaughtData.weight * (1f + ((float)weightModifier/100f))));
        tagCompound.setLong("FishCaughtTime", currentTime);
        if(CustomConfigurationHandler.fishDataMap.get(fishCaughtData.fishId).allowScaling) tagCompound.setBoolean("FishScale", true);
        itemStack.setTagCompound(tagCompound);

        itemStack.setStackDisplayName(TextFormatting.RESET + fishCaughtData.fishId);
        
        List<String> tooltipList = new ArrayList<>();
        tooltipList.add(String.format("Weight: %d", fishCaughtData.weight));
        if(itemStack.getTagCompound().hasKey("FishScale")) tooltipList.add("Scale: Attached");
        tooltipList.add(TextFormatting.BLUE + "" + TextFormatting.BOLD + "Alive" + TextFormatting.RESET);

        itemStack = ItemStackUtil.appendToolTip(itemStack, tooltipList);

        return itemStack;
    }

    private void spawnParticleBasedOnFishSpeed(World world, EntityFishHook hook, IFishingData fishingData) {//TODO: fix weird math, maybe
        if(world.isRemote) return;

        WorldServer worldServer = (WorldServer) world;

        float numberOfParticles;
        double particleSpeed;
        int fishTime = fishingData.getLastFishTime();
        int minFishTime = 80;

        if (MathUtil.inRange(fishTime, minFishTime, minFishTime + 80)) {
            numberOfParticles = 40.0f;
            particleSpeed = 0.40000000298023224D;
        } else if (MathUtil.inRange(fishTime, minFishTime + 81, minFishTime + 139)) {
            numberOfParticles = 30.0f;
            particleSpeed = 0.30000000298023224D;
        } else if (MathUtil.inRange(fishTime, minFishTime + 140, minFishTime + 175)) {
            numberOfParticles = 30.0f;
            particleSpeed = 0.30000000298023224D;
        } else if (MathUtil.inRange(fishTime, minFishTime + 176, minFishTime + 200)) {
            numberOfParticles = 20.0f;
            particleSpeed = 0.20000000298023224D;
        } else if (MathUtil.inRange(fishTime, minFishTime + 201, minFishTime + 230)) {
            numberOfParticles = 5.0f;
            particleSpeed = 0.10000000298023224D;
        } else {
            numberOfParticles = 2.0f;
            particleSpeed = 0.05000000298023224D;
        }

        double yPos = hook.getEntityBoundingBox().minY + 0.5D;

        if(hook instanceof EntityFMBCustomFishHook) {
            worldServer.spawnParticle(((EntityFMBCustomFishHook)hook).getBubbleParticle(), hook.posX, yPos, hook.posZ, (int) (1.0F + hook.width * numberOfParticles), (double) hook.width, 0.0D, (double) hook.width, particleSpeed);
            worldServer.spawnParticle(((EntityFMBCustomFishHook)hook).getWakeParticle(), hook.posX, yPos, hook.posZ, (int) (1.0F + hook.width * numberOfParticles), (double) hook.width, 0.0D, (double) hook.width, particleSpeed);
        }
        else {
            worldServer.spawnParticle(EnumParticleTypes.WATER_BUBBLE, hook.posX, yPos, hook.posZ, (int) (1.0F + hook.width * numberOfParticles), (double) hook.width, 0.0D, (double) hook.width, particleSpeed);
            worldServer.spawnParticle(EnumParticleTypes.WATER_WAKE, hook.posX, yPos, hook.posZ, (int) (1.0F + hook.width * numberOfParticles), (double) hook.width, 0.0D, (double) hook.width, particleSpeed);
        }
    }

    private void updateFishingData(EntityPlayer player, IFishingData fishingData) {
        if(fishingData.getLastFailedFishing() > 0) fishingData.setLastFailedFishing(fishingData.getLastFailedFishing() - 1);
        if(fishingData.getTimeBeforeFishSwimToHook() > 0) fishingData.setTimeBeforeFishSwimToHook(fishingData.getTimeBeforeFishSwimToHook() - 1 - (player.world.isRainingAt(player.getPosition()) ? player.getRNG().nextInt(2) : 0));

        if(fishingData.isFishing()) {
            ItemStack betterFishingRodStack = getBetterFishingRod(player);

            int fishSpeed = 8-Math.min(6, (int)(((float)getDistanceTime(fishingData.getFishWeight())/2f)+0.5f));//TODO: cache distanceTime
            int tensioningSpeed = 8;
            
            fishSpeed -= ItemBetterFishingRod.getHookItem(betterFishingRodStack).getTuggingReduction();
            tensioningSpeed += ItemBetterFishingRod.getBobberItem(betterFishingRodStack).getTensioningModifier();
            
            updateReelTarget(player.getRNG(), fishingData, fishSpeed);
            updateReelPosition(fishingData, tensioningSpeed, player);
            updateLineBreak(fishingData);
            
            fishingData.setFishTime(fishingData.getFishTime() - 1);
            if(fishingData.getFishTime() < 0) fishingData.setFishTime(0);
        }
        PrimaryPacketHandler.INSTANCE.sendTo(new PacketReelingC(fishingData.getReelAmount(), fishingData.getReelTarget(), fishingData.getFishDistance(), fishingData.getFishDeepLevel(), fishingData.getErrorVariance(), fishingData.isFishing(), fishingData.getMinigameBackground()[0], fishingData.getMinigameBackground()[1], fishingData.getMinigameBackground()[2], fishingData.getMinigameBackground()[3], fishingData.getMinigameBackground()[4], fishingData.getLineBreak()), (EntityPlayerMP) player);
    }
    
    private void updateReelTarget(Random random, IFishingData fishingData, int fishSpeed) {
    	int prevTugging = fishingData.getFishTugging();
    	if(prevTugging==0) fishSpeed = fishSpeed * (random.nextInt(3)-1) * Math.max(0, RandomUtil.getRandomInRange(random, -getDistanceTime(fishingData.getFishWeight()), 1));//TODO: cache distanceTime maybe
    	else fishSpeed = fishSpeed * (int)Math.signum(prevTugging) * (int)Math.signum(RandomUtil.getRandomInRange(random, -1, 20));
    	fishingData.setFishTugging(fishSpeed);
    	
    	int fishMomentum = fishingData.getFishMomentum();
    	fishMomentum += (int)Math.signum(fishSpeed-fishMomentum);
    	
    	fishingData.setFishMomentum(fishMomentum);
        fishingData.setReelTarget(fishingData.getReelTarget() + fishMomentum);
    }
    
    private void updateReelPosition(IFishingData fishingData, int tensioningSpeed, EntityPlayer player) {
    	if(fishingData.getKeybind().equals(Keybind.REEL_IN)) tensioningSpeed = tensioningSpeed * -1;
        else if(fishingData.getKeybind().equals(Keybind.REEL_OUT)) tensioningSpeed = tensioningSpeed * 1;
        else tensioningSpeed = tensioningSpeed * 0;
    	
        int tensionMomentum = fishingData.getTensionMomentum();
        tensionMomentum += (int)Math.signum(tensioningSpeed-tensionMomentum);
        
        fishingData.setTensionMomentum(tensionMomentum);
        fishingData.setReelAmount(fishingData.getReelAmount() + tensionMomentum);
        playReelingSound(tensionMomentum, player);
    }
    
    private void updateLineBreak(IFishingData fishingData) {
    	fishingData.setLineBreak(fishingData.getLineBreak() + (fishingData.getFishDistance()==0 ? 1 : -1));
    }
    
    private void playReelingSound(int momentum, EntityPlayer player) {//TODO: make this sound better
    	if(momentum==0 || player.ticksExisted%2!=0) return;
        float pitch = Math.min(2.0f, Math.max(0.0f, (float)Math.abs(momentum)/10.0f));

        player.world.playSound(null, player.posX, player.posY, player.posZ, RegistryHandler.REELING_EVENT, SoundCategory.PLAYERS, pitch, 2.0f);
    }

    private IChunkFishingData getChunkFishingData(Chunk chunk) {
        return chunk.getCapability(ChunkCapabilityProvider.CHUNK_FISHING_DATA_CAP, null);
    }

    private int getLureSpeed(EntityPlayer player, FishPopulation population) {
        ItemStack itemFishingRod = getBetterFishingRod(player);

        int pop = population.population;

        int rate = (int)(900f * ((float)(100f-ItemBetterFishingRod.getHookItem(itemFishingRod).getBiteRateModifier())/100f));//Percentage reduction based on modifier
        rate -= (100*EnchantmentHelper.getFishingSpeedBonus(itemFishingRod));//Max 60 seconds, -5 per lure level
        rate -= 4*pop;//-4 ticks per fish in population
        
        FishData fishData = CustomConfigurationHandler.fishDataMap.get(population.fishId);

        String baitItem = ItemBetterFishingRod.getBaitItem(itemFishingRod);

        if(ItemBetterFishingRod.hasBait(itemFishingRod) && fishData.baitItemMap.containsKey(baitItem) && Arrays.asList(fishData.baitItemMap.get(baitItem)).contains(ItemBetterFishingRod.getBaitMetadata(itemFishingRod))) {
            rate -= RandomUtil.getRandomInRange(player.getRNG(), 100, 200);
        }
        rate = Math.max(60, rate);
        
        return rate;
    }

    private FishPopulation getSelectedFishPop(EntityPlayer player, IChunkFishingData chunkFishingData) {
        if(!chunkFishingData.hasFishes()) return null;
        World world = player.world;
        EntityFishHook hook = player.fishEntity;
        
        FishingLiquid liquid = FishingLiquid.WATER;
        if(hook instanceof EntityFMBLavaFishHook) liquid = FishingLiquid.LAVA;
        else if(hook instanceof EntityFMBVoidFishHook) liquid = FishingLiquid.VOID;
        
        if(liquid == FishingLiquid.WATER) {
        	int waterCount = 0;
        	for(BlockPos pos : BlockPos.getAllInBox((int)hook.posX-2, (int)hook.posY-3, (int)hook.posZ-2, (int)hook.posX+2, (int)hook.posY, (int)hook.posZ+2)) {
        		Material mat = world.getBlockState(pos).getMaterial();
        		if(mat == MaterialLiquid.WATER) waterCount++;
        		if(waterCount >= 25) break;
        	}
        	if(waterCount < 25) return null;//Not enough water
        }
        if(liquid == FishingLiquid.LAVA) {//TODO: different sizes required depending on liquid
        	int lavaCount = 0;
        	for(BlockPos pos : BlockPos.getAllInBox((int)hook.posX-2, (int)hook.posY-3, (int)hook.posZ-2, (int)hook.posX+2, (int)hook.posY, (int)hook.posZ+2)) {
        		Material mat = world.getBlockState(pos).getMaterial();
        		if(mat == MaterialLiquid.LAVA) lavaCount++;
        		if(lavaCount >= 25) break;
        	}
        	if(lavaCount < 25) return null;//Not enough lava
        }
        
        ItemStack itemFishingRod = getBetterFishingRod(player);
        boolean hasFishBait = ItemBetterFishingRod.hasBait(itemFishingRod);
        
        Map<String, PopulationData> fishPopulationMap = chunkFishingData.getFishes(world.getTotalWorldTime());
        List<WeightedRandomFishPopulation> weightedRandomFishPopulationList = new ArrayList<>();

        for(PopulationData populationData : fishPopulationMap.values()) {
            FishData fishData = CustomConfigurationHandler.fishDataMap.get(populationData.getFishType());
            if(fishData == null) continue;

            if(fishData.time.equals(FishData.TimeToFish.DAY) && (!world.isDaytime())) continue;
            if(fishData.time.equals(FishData.TimeToFish.NIGHT) && (world.isDaytime())) continue;
            
            if(!fishData.liquid.equals(liquid) && !fishData.liquid.equals(FishingLiquid.ANY)) continue; //TODO: reenable after testing
            
            if(fishData.minDeepLevel > ItemBetterFishingRod.getReelItem(itemFishingRod).getReelRange()) continue;
            
            if(fishData.minYLevel > hook.posY) continue;
            if(fishData.maxYLevel < hook.posY) continue; 
            
            if(fishData.maxLightLevel < world.getLight(hook.getPosition())) continue;
            
            if(fishData.rainRequired && (!world.isRaining())) continue;
            if(fishData.thunderRequired && (!world.isThundering())) continue;

            int rarity = fishData.rarity;
            if(hasFishBait && ItemBetterFishingRod.isBaitForFish(itemFishingRod, populationData.getFishType())) rarity += RandomUtil.getRandomInRange(player.getRNG(), 25, 75);
            weightedRandomFishPopulationList.add(new WeightedRandomFishPopulation(rarity, populationData.getFishType(), populationData.getQuantity()));
        }
        if(weightedRandomFishPopulationList.size() <= 0) return null;

        WeightedRandomFishPopulation randomFishPopulation = WeightedRandom.getRandomItem(world.rand, weightedRandomFishPopulationList);

        return new FishPopulation(randomFishPopulation.fishId, randomFishPopulation.population);
    }
    
    private ItemStack getBetterFishingRod(EntityPlayer player) {
        if(usingVanillaFishingRod(player)) return ItemStack.EMPTY;

        ItemStack mainHandItem = player.getHeldItemMainhand();
        ItemStack offHandItem = player.getHeldItemOffhand();

        if(!mainHandItem.isEmpty() && mainHandItem.getItem() instanceof ItemBetterFishingRod) return mainHandItem;
        if(!offHandItem.isEmpty() && offHandItem.getItem() instanceof ItemBetterFishingRod) return offHandItem;
        return ItemStack.EMPTY;
    }

    private boolean usingVanillaFishingRod(EntityPlayer player) {
        ItemStack mainHandItem = player.getHeldItemMainhand();
        ItemStack offHandItem = player.getHeldItemOffhand();

        if(!mainHandItem.isEmpty()) {
	        if(mainHandItem.getItem() instanceof ItemBetterFishingRod) return false;
	        if(mainHandItem.getItem() instanceof ItemFishingRod) return true;
        }
        if(!offHandItem.isEmpty()) {
	        if(offHandItem.getItem() instanceof ItemBetterFishingRod) return false;
	        if(offHandItem.getItem() instanceof ItemFishingRod) return true;
        }
        return false;
    }
    
    private void setLureSpeed(EntityFishHook hook, int ticks) {
        if(delayBeforeSwimmingToHookField == null) return;

        try {
            delayBeforeSwimmingToHookField.setAccessible(true);
            delayBeforeSwimmingToHookField.setInt(hook, ticks);
            delayBeforeSwimmingToHookField.setAccessible(false);
        } 
        catch(IllegalArgumentException | IllegalAccessException e) {
            delayBeforeSwimmingToHookField.setAccessible(false);
            e.printStackTrace();
        }
    }

    private int getDelayBeforeSwimmingToHook(EntityFishHook hook) {
        if(delayBeforeSwimmingToHookField == null) return -1;
        
        try {
            delayBeforeSwimmingToHookField.setAccessible(true);
            int delay = delayBeforeSwimmingToHookField.getInt(hook);
            delayBeforeSwimmingToHookField.setAccessible(false);
            return delay;
        } 
        catch(IllegalArgumentException | IllegalAccessException e) {
            delayBeforeSwimmingToHookField.setAccessible(false);
            e.printStackTrace();
            return -1;
        }
    }

    private int getDelayBeforeBite(EntityFishHook hook) {
        if(delayBeforeBiteField == null) return -1;

        try {
            delayBeforeBiteField.setAccessible(true);
            int delay = delayBeforeBiteField.getInt(hook);
            delayBeforeBiteField.setAccessible(false);
            return delay;
        } 
        catch(IllegalArgumentException | IllegalAccessException e) {
            delayBeforeBiteField.setAccessible(false);
            e.printStackTrace();
            return -1;
        }
    }

    private void setBiteInterval(EntityFishHook hook, int time) {
        if(biteIntervalField == null) return;

        try {
            biteIntervalField.setAccessible(true);
            biteIntervalField.setInt(hook, time);
            biteIntervalField.setAccessible(false);
        } 
        catch(IllegalArgumentException | IllegalAccessException e) {
            biteIntervalField.setAccessible(false);
            e.printStackTrace();
        }
    }

    private HookState getHookState(EntityFishHook hook) {
    	if(currentStateField == null) return null;
    	
        try {
        	currentStateField.setAccessible(true);
            Enum hookEnum = (Enum) currentStateField.get(hook);
            int hookStateOrdinal = hookEnum.ordinal();
            currentStateField.setAccessible(false);
            return HookState.values()[hookStateOrdinal];
        } 
        catch(IllegalAccessException e) {
        	currentStateField.setAccessible(false);
            e.printStackTrace();
            return null;
        }
    }
}
