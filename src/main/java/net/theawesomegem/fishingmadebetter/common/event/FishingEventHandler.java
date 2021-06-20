package net.theawesomegem.fishingmadebetter.common.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
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
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.theawesomegem.fishingmadebetter.BetterFishUtil;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.FishPopulation;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.FishingCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.IFishingData;
import net.theawesomegem.fishingmadebetter.common.capability.world.ChunkCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.world.IChunkFishingData;
import net.theawesomegem.fishingmadebetter.common.capability.world.PopulationData;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomFishConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishCaughtData;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.data.WeightedRandomFishPopulation;
import net.theawesomegem.fishingmadebetter.common.item.ItemFishTracker;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemBetterFishingRod;
import net.theawesomegem.fishingmadebetter.common.networking.PrimaryPacketHandler;
import net.theawesomegem.fishingmadebetter.common.networking.packet.PacketReelingC;
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
public class FishingEventHandler {
    enum HookState {
        FLYING,
        HOOKED_IN_ENTITY,
        BOBBING
    }

    final static Field currentStateField = ReflectionHelper.findField(EntityFishHook.class, "currentState", "field_190627_av", "av");
    final static Field biteIntervalField = ReflectionHelper.findField(EntityFishHook.class, "g", "ticksCatchable");
    final static Field delayBeforeBiteField = ReflectionHelper.findField(EntityFishHook.class, "ticksCatchableDelay", "field_146038_az", "at");
    final static Field delayBeforeSwimmingToHookField = ReflectionHelper.findField(EntityFishHook.class, "ticksCaughtDelay", "field_146040_ay", "h");

    @SubscribeEvent
    public void onPlayerFish(ItemFishedEvent e) {
        EntityPlayer player = e.getEntityPlayer();

        if (player == null || !player.isEntityAlive())
            return;

        World world = player.world;

        if (world.isRemote)
            return;

        if (usingVanillaFishingRod(player)) {
            return;
        }

        IFishingData fishingData = player.getCapability(FishingCapabilityProvider.FISHING_DATA_CAP, null);

        if (fishingData == null) {
            e.setCanceled(true);

            return;
        }

        IChunkFishingData chunkFishingData = world.getChunkFromBlockCoords(e.getHookEntity().getPosition()).getCapability(ChunkCapabilityProvider.CHUNK_FISHING_DATA_CAP, null);

        if (chunkFishingData == null) {
            e.setCanceled(true);

            return;
        }

        if (!fishingData.isFishing()) {
            e.setCanceled(true);
            return;
        }

        FishCaughtData fishCaughtData = fishingData.getFishCaughtData();

        if (fishCaughtData == null) {
            e.setCanceled(true);
            return;
        }

        e.setCanceled(true);

        boolean canFish = true;

        if(ConfigurationManager.requireCorrectLine){
            int reelDiff = fishingData.getReelTarget() - fishingData.getReelAmount();
            reelDiff = Math.abs(reelDiff);
            canFish = (reelDiff <= fishingData.getErrorVariance());
        }

        if (canFish && fishingData.getFishDistance() >= fishingData.getFishDeepLevel()) {
            ItemStack itemStack = getFishItemStack(fishCaughtData, world.getTotalWorldTime());

            EntityFishHook hook = e.getHookEntity();

            EntityItem entityitem = new EntityItem(player.world, hook.posX, hook.posY, hook.posZ, itemStack);

            if (ConfigurationManager.magneticFishing) {
                entityitem.setPosition(player.posX, player.posY + 1, player.posZ);
            } else {
                double d0 = player.posX - hook.posX;
                double d1 = player.posY - hook.posY;
                double d2 = player.posZ - hook.posZ;
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                entityitem.motionX = d0 * 0.1D;
                entityitem.motionY = d1 * 0.1D + (double) MathHelper.sqrt(d3) * 0.08D;
                entityitem.motionZ = d2 * 0.1D;
            }

            chunkFishingData.reducePopulation(fishCaughtData.fishId, 1, world.getTotalWorldTime(), true);

            player.world.spawnEntity(entityitem);
            player.world.spawnEntity(new EntityXPOrb(player.world, player.posX, player.posY + 0.5D, player.posZ + 0.5D, player.getRNG().nextInt(6) + 1));
        }

        fishingData.reset();
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent e) {
        EntityPlayer player = e.player;
        EntityFishHook hook = player.fishEntity;

        if (player.world.isRemote)
            return;

        checkForFishInventory(player);

        IFishingData fishingData = player.getCapability(FishingCapabilityProvider.FISHING_DATA_CAP, null);

        if (fishingData == null) {
            return;
        }

        checkTracking(player, fishingData);

        updateFishingData(player, fishingData);

        if (hook == null || hook.isDead) {
            fishingData.reset();

            return;
        }

        if (usingVanillaFishingRod(player)) {
            fishingData.reset();

            return;
        }

        if (fishingData.getTimeBeforeFishSwimToHook() == 0) {
            if (getDelayBeforeSwimmingToHook(hook) > 20)
                setLureSpeed(hook, 20);
        } else {
            setLureSpeed(hook, 200);
        }

        Enum hookState = getHookState(hook);

        if (hookState == null) {
            fishingData.reset();

            return;
        }

        if (!hookState.equals(HookState.BOBBING)) {
            fishingData.reset();

            return;
        }

        if (fishingData.isFishing()) {
            if (fishingData.getFishDistanceTime() <= 0) {
                int distanceTime = getDistanceTime(fishingData.getFishWeight());

                ItemStack betterFishingRodStack = getBetterFishingRod(player);

                if (!betterFishingRodStack.isEmpty()) {
                    distanceTime -= ((ItemBetterFishingRod) betterFishingRodStack.getItem()).getDragSpeed();
                }

                fishingData.setFishDistanceTime(distanceTime);
                int reelDiff = fishingData.getReelTarget() - fishingData.getReelAmount();
                reelDiff = Math.abs(reelDiff);

                if (reelDiff <= fishingData.getErrorVariance()) {
                    fishingData.setFishDistance(fishingData.getFishDistance() + 1);
                }
            } else {
                fishingData.setFishDistanceTime(fishingData.getFishDistanceTime() - 1);
            }
        }

        World world = hook.world;
        Chunk chunk = world.getChunkFromBlockCoords(hook.getPosition());
        IChunkFishingData chunkFishingData = getChunkFishingData(chunk);

        if (fishingData.getFishPopulation() == null) {
            fishingData.setFishPopulation(getSelectedFishPop(player, chunkFishingData));

            if (fishingData.getFishPopulation() == null) {
                fishingData.reset();

                return;
            }
        }

        if (fishingData.getTimeBeforeFishSwimToHook() == -1) {
            if (!fishingData.isFishing())
                fishingData.setTimeBeforeFishSwimToHook(getLureSpeed(player, fishingData.getFishPopulation()));
        }

        int delayBeforeBite = getDelayBeforeBite(hook);

        if (delayBeforeBite > 0 && delayBeforeBite <= 10) {
            //Fish just bit the hook
            if (fishingData.getLastFailedFishing() <= 0) {
                if (!fishingData.isFishing()) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, RegistryHandler.FISH_SPLASHING_EVENT, SoundCategory.PLAYERS, 1.0f, 1.0f);
                }

                if(ConfigurationManager.looseBait) {
                    ItemStack fishingRodItem = getBetterFishingRod(player);
                    ItemBetterFishingRod.removeBait(fishingRodItem);
                }

                fishingData.startFishing(player.getRNG());
            }

            if (!canFishEscape(player, hook, fishingData)) {
                spawnParticleBasedOnFishSpeed(player.world, hook, fishingData);

                setBiteInterval(hook, 20);
            } else {
                setBiteInterval(hook, 0);

                fishingData.setLastFailedFishing(20);
                fishingData.reset();
            }
        } else {
            fishingData.reset(false);
        }
    }

    @SubscribeEvent
    public void onChunkLoad(ChunkEvent.Load e) {
        if (e.getWorld().isRemote) {
            return;
        }

        Chunk chunk = e.getChunk();
        IChunkFishingData chunkFishingData = getChunkFishingData(chunk);
        chunkFishingData.chunkLoad(chunk);
    }

    private void checkForFishInventory(EntityPlayer player) {
        InventoryPlayer playerInventory = player.inventory;
        long currentTime = player.world.getTotalWorldTime();

        for (int i = 0; i < playerInventory.getSizeInventory(); i++) {
            ItemStack itemStack = playerInventory.getStackInSlot(i);

            if (!BetterFishUtil.isBetterFish(itemStack)) {
                continue;
            }

            if (!BetterFishUtil.isDead(itemStack, currentTime)) {
                continue;
            }

            List<String> loreList = ItemStackUtil.getToolTip(itemStack);

            if (loreList.size() > 0) {
                loreList.remove(loreList.size() - 1);
            }

            ItemStackUtil.appendToolTip(itemStack, loreList);

            BetterFishUtil.setFishCaughtTime(itemStack, 0);
        }
    }

    private void checkTracking(EntityPlayer player, IFishingData fishingData) {
        if (fishingData.getTimeSinceTracking() <= 0) {
            return;
        }

        ItemStack mainHandItem = player.getHeldItem(EnumHand.MAIN_HAND);
        ItemStack offHandItem = player.getHeldItem(EnumHand.OFF_HAND);

        if ((!(mainHandItem.getItem() instanceof ItemFishTracker)) && (!(offHandItem.getItem() instanceof ItemFishTracker))) {
            fishingData.setTimeSinceTracking(0);
            player.sendMessage(new TextComponentString("You must be holding a fish tracker either on your main hand or your off hand."));
        } else if (mainHandItem.getItem() instanceof ItemFishTracker && offHandItem.getItem() instanceof ItemFishTracker) {
            fishingData.setTimeSinceTracking(0);
            player.sendMessage(new TextComponentString("You cannot hold fish tracker on both hands."));
        } else {
            ItemStack fishTrackerItem = (mainHandItem.getItem() instanceof ItemFishTracker) ? mainHandItem : offHandItem;
            fishingData.setTimeSinceTracking(fishingData.getTimeSinceTracking() + 1);
            long maxTrackingTime = player.world.getTotalWorldTime() + TimeUtil.secondsToMinecraftTicks(ConfigurationManager.trackingTime);

            if (fishingData.getTimeSinceTracking() >= maxTrackingTime) {
                fishingData.setTimeSinceTracking(0);
                getTrackingFish(player, (ItemFishTracker) fishTrackerItem.getItem());
                player.sendMessage(new TextComponentString("You have finished tracking."));
            }
        }
    }

    private void getTrackingFish(EntityPlayer player, ItemFishTracker item) {
        World world = player.world;
        Chunk chunk = world.getChunkFromBlockCoords(getBlockPos(player));
        IChunkFishingData chunkFishingData = getChunkFishingData(chunk);

        if (chunkFishingData == null) {
            return;
        }

        for (PopulationData populationData : chunkFishingData.getFishes(world.getTotalWorldTime()).values()) {
            FishData fishData = CustomFishConfigurationHandler.fishDataMap.get(populationData.getFishType());

            if (fishData == null) {
                continue;
            }

            if (!fishData.trackable) {
                continue;
            }

            if (item.getMaxDepth() < fishData.maxDeepLevel) {
                continue;
            }

            boolean unknownFish = false;

            if (!item.getTrackingVision().equals(ItemFishTracker.TrackingVision.Best)) {
                if (ItemFishTracker.TrackingVision.Bad.equals(item.getTrackingVision()) && fishData.rarity < 50) {
                    unknownFish = true;
                } else if (ItemFishTracker.TrackingVision.Normal.equals(item.getTrackingVision()) && fishData.rarity < 30) {
                    unknownFish = true;
                }
            }

            String quantity = "medium";

            if(populationData.getQuantity() > 340){
                quantity = "huge";
            } else if(populationData.getQuantity() > 260){
                quantity = "large";
            } else if(populationData.getQuantity() > 140){
                quantity = "big";
            } else if(populationData.getQuantity() > 70){
                quantity = "decent";
            } else if(populationData.getQuantity() > 30){
                quantity = "medium";
            } else if(populationData.getQuantity() > 10){
                quantity = "small";
            } else if(populationData.getQuantity() > 1){
                quantity = "tiny";
            } else if(populationData.getQuantity() == 1){
                quantity = "rare";
            }

            player.sendMessage(new TextComponentString("-----"));
            player.sendMessage(new TextComponentString(String.format("Found %s with weight ranging from %d to %d in %s quantity.", unknownFish ? " an unknown fish" : fishData.description, fishData.minWeight, fishData.maxWeight, quantity)));
            player.sendMessage(new TextComponentString("-----"));
        }
    }

    public BlockPos getBlockPos(Entity entity) {
        int blockX = MathHelper.floor(entity.posX);
        int blockY = MathHelper.floor(entity.getEntityBoundingBox().minY) - 1;
        int blockZ = MathHelper.floor(entity.posZ);

        return new BlockPos(blockX, blockY, blockZ);

    }

    private int getDistanceTime(int weight) {
        if (weight > 300) {
            return 260;
        } else if (weight > 250) {
            return 250;
        } else if (weight > 200) {
            return 240;
        } else if (weight > 170) {
            return 220;
        } else if (weight > 140) {
            return 210;
        } else if (weight > 110) {
            return 200;
        } else if (weight > 100) {
            return 190;
        } else if (weight > 80) {
            return 180;
        } else if (weight > 60) {
            return 170;
        } else if (weight > 50) {
            return 160;
        } else if (weight > 30) {
            return 150;
        } else if (weight > 20) {
            return 130;
        } else if (weight > 10) {
            return 120;
        } else {
            return 110;
        }
    }

    private ItemStack getFishItemStack(FishCaughtData fishCaughtData, long currentTime) {
        Item item = Item.getByNameOrId(fishCaughtData.itemId);

        if (item == null) {
            return ItemStack.EMPTY;
        }

        ItemStack itemStack = new ItemStack(item, 1, fishCaughtData.itemMetaData);
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound tagCompound = itemStack.getTagCompound();
        tagCompound.setString("FishId", fishCaughtData.fishId);
        tagCompound.setInteger("FishWeight", fishCaughtData.weight);
        tagCompound.setLong("FishCaughtTime", currentTime);
        tagCompound.setBoolean("FishScale", true);
        itemStack.setTagCompound(tagCompound);

        List<String> tooltipList = new ArrayList<>();
        tooltipList.add(String.format("Weight: %d", fishCaughtData.weight));
        tooltipList.add("Scale: Attached");
        tooltipList.add(TextFormatting.BLUE + "" + TextFormatting.BOLD + "Alive" + TextFormatting.RESET);

        itemStack = ItemStackUtil.appendToolTip(itemStack, tooltipList);

        return itemStack;
    }

    private void spawnParticleBasedOnFishSpeed(World world, EntityFishHook hook, IFishingData fishingData) {
        if (world.isRemote)
            return;

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

        worldServer.spawnParticle(EnumParticleTypes.WATER_BUBBLE, hook.posX, yPos, hook.posZ, (int) (1.0F + hook.width * numberOfParticles), (double) hook.width, 0.0D, (double) hook.width, particleSpeed);
        worldServer.spawnParticle(EnumParticleTypes.WATER_WAKE, hook.posX, yPos, hook.posZ, (int) (1.0F + hook.width * numberOfParticles), (double) hook.width, 0.0D, (double) hook.width, particleSpeed);
    }

    private boolean canFishEscape(EntityPlayer player, EntityFishHook hook, IFishingData fishingData) {
        return fishingData.getFishTime() <= 0;
    }

    private void updateFishingData(EntityPlayer player, IFishingData fishingData) {
        if (fishingData.getLastFailedFishing() > 0)
            fishingData.setLastFailedFishing(fishingData.getLastFailedFishing() - 1);

        if (fishingData.getTimeBeforeFishSwimToHook() > 0)
            fishingData.setTimeBeforeFishSwimToHook(fishingData.getTimeBeforeFishSwimToHook() - 1);

        if (fishingData.isFishing()) {
            int tugging = 3;
            ItemStack betterFishingRodStack = getBetterFishingRod(player);

            if (!betterFishingRodStack.isEmpty()) {
                tugging = ((ItemBetterFishingRod) betterFishingRodStack.getItem()).getTuggingAmount();
            }

            updateReelAmount(player.getRNG(), fishingData, tugging);

            fishingData.setFishTime(fishingData.getFishTime() - 1);

            if (fishingData.getFishTime() < 0)
                fishingData.setFishTime(0);
        }

        PrimaryPacketHandler.INSTANCE.sendTo(new PacketReelingC(fishingData.getReelAmount(), fishingData.getReelTarget(), fishingData.getFishDistance(), fishingData.getFishDeepLevel(), fishingData.isFishing()), (EntityPlayerMP) player);
    }

    private void updateReelAmount(Random random, IFishingData fishingData, int tugging) {
        if (!shouldChangeReelAmount(random, fishingData.getLastFishTime()))
            return;

        boolean shouldAdd = random.nextBoolean();

        if (shouldAdd)
            fishingData.setReelAmount(fishingData.getReelAmount() + tugging);
        else
            fishingData.setReelAmount(fishingData.getReelAmount() - tugging);
    }

    private IChunkFishingData getChunkFishingData(Chunk chunk) {
        return chunk.getCapability(ChunkCapabilityProvider.CHUNK_FISHING_DATA_CAP, null);
    }


    //TODO: Maybe add fish speed to the equation?
    private int getLureSpeed(EntityPlayer player, FishPopulation population) {
        ItemStack itemFishingRod = getBetterFishingRod(player);

        final int maxTicks = 1800;
        final int pop = population.population;

        int rate = (int) ((float) maxTicks - ((float) pop * 24f));
        rate += RandomUtil.getRandomInRange(player.getRNG(), -80, 80);

        Map<String, Integer> baitMap = new HashMap<>();
        FishData fishData = CustomFishConfigurationHandler.fishDataMap.get(population.fishId);

        if (fishData != null) {
            baitMap.putAll(fishData.baitItemMap);
        }

        String baitItem = ItemBetterFishingRod.getBaitItem(itemFishingRod);

        if (ItemBetterFishingRod.hasBait(itemFishingRod) && baitMap.containsKey(baitItem) && baitMap.get(baitItem) == ItemBetterFishingRod.getBaitMetadata(itemFishingRod)) {
            rate -= RandomUtil.getRandomInRange(player.getRNG(), 50, 200);
        }

        if (rate < 10)
            rate = 10;

        if (rate > maxTicks)
            rate = maxTicks;

        return rate;
    }

    private FishPopulation getSelectedFishPop(EntityPlayer player, IChunkFishingData chunkFishingData) {
        if (!chunkFishingData.hasFishes())
            return null;

        ItemStack itemFishingRod = getBetterFishingRod(player);

        boolean hasFishBait = ItemBetterFishingRod.hasBait(itemFishingRod);
        World world = player.world;
        Map<String, PopulationData> fishPopulationMap = chunkFishingData.getFishes(world.getTotalWorldTime());
        List<WeightedRandomFishPopulation> weightedRandomFishPopulationList = new ArrayList<>();

        for (PopulationData populationData : fishPopulationMap.values()) {
            FishData fishData = CustomFishConfigurationHandler.fishDataMap.get(populationData.getFishType());

            if (fishData == null)
                continue;

            if (fishData.time.equals(FishData.TimeToFish.DAY) && (!isDay(world)))
                continue;

            if (fishData.time.equals(FishData.TimeToFish.NIGHT) && (isDay(world)))
                continue;

            if (fishData.rainRequired && (!isRaining(world)))
                continue;

            int rarity = fishData.rarity;

            if (hasFishBait && ItemBetterFishingRod.isBaitForFish(itemFishingRod, populationData.getFishType())) {
                rarity += RandomUtil.getRandomInRange(player.getRNG(), 8, 60);
            }

            weightedRandomFishPopulationList.add(new WeightedRandomFishPopulation(rarity, populationData.getFishType(), populationData.getQuantity()));
        }

        if (weightedRandomFishPopulationList.size() <= 0) {
            return null;
        }

        WeightedRandomFishPopulation randomFishPopulation = WeightedRandom.getRandomItem(world.rand, weightedRandomFishPopulationList);

        return new FishPopulation(randomFishPopulation.fishId, randomFishPopulation.population);
    }

    private boolean shouldChangeReelAmount(Random random, int lastFishTime) {
        int chance;
        final int minFishTime = 560;

        if (MathUtil.inRange(lastFishTime, minFishTime, minFishTime + 120))
            chance = 60;
        else if (MathUtil.inRange(lastFishTime, minFishTime + 121, minFishTime + 300))
            chance = 40;
        else if (MathUtil.inRange(lastFishTime, minFishTime + 301, minFishTime + 465))
            chance = 28;
        else if (MathUtil.inRange(lastFishTime, minFishTime + 466, minFishTime + 734))
            chance = 12;
        else if (MathUtil.inRange(lastFishTime, minFishTime + 735, minFishTime + 910))
            chance = 6;
        else {
            chance = 2;
        }

        int randPercChance = RandomUtil.getRandomInRange(random, 0, 100);

        return chance >= randPercChance;
    }

    private boolean isInSaltWater(Entity entity) {
        World world = entity.world;
        Biome biome = world.getBiome(entity.getPosition());

        return (biome instanceof BiomeOcean);
    }

    private boolean isDay(World world) {
        return world.isDaytime();
    }

    private boolean isRaining(World world) {
        return world.isRaining();
    }

    private ItemStack getBetterFishingRod(EntityPlayer player) {
        if (usingVanillaFishingRod(player))
            return ItemStack.EMPTY;

        ItemStack mainHandItem = player.getHeldItemMainhand();
        ItemStack offHandItem = player.getHeldItemOffhand();

        if (mainHandItem.isEmpty() && offHandItem.isEmpty())
            return ItemStack.EMPTY;

        if (mainHandItem.getItem() instanceof ItemBetterFishingRod)
            return mainHandItem;

        if (offHandItem.getItem() instanceof ItemBetterFishingRod)
            return offHandItem;

        return ItemStack.EMPTY;
    }

    private boolean usingVanillaFishingRod(EntityPlayer player) {
        ItemStack mainHandItem = player.getHeldItemMainhand();
        ItemStack offHandItem = player.getHeldItemOffhand();

        if (mainHandItem.isEmpty() && offHandItem.isEmpty())
            return false;

        if (mainHandItem.getItem() instanceof ItemBetterFishingRod)
            return false;

        if (mainHandItem.getItem() instanceof ItemFishingRod)
            return true;

        if (offHandItem.getItem() instanceof ItemBetterFishingRod)
            return false;

        if (offHandItem.getItem() instanceof ItemFishingRod)
            return true;

        return false;
    }

    private void setLureSpeed(EntityFishHook hook, int ticks) {
        if (delayBeforeSwimmingToHookField == null)
            return;

        delayBeforeSwimmingToHookField.setAccessible(true);

        try {
            delayBeforeSwimmingToHookField.setInt(hook, ticks);

            delayBeforeSwimmingToHookField.setAccessible(false);

            return;
        } catch (IllegalArgumentException | IllegalAccessException e) {
            delayBeforeSwimmingToHookField.setAccessible(false);

            e.printStackTrace();
        }

        delayBeforeSwimmingToHookField.setAccessible(false);
    }

    private int getDelayBeforeSwimmingToHook(EntityFishHook hook) {
        if (delayBeforeSwimmingToHookField == null)
            return -1;

        delayBeforeSwimmingToHookField.setAccessible(true);

        try {
            int delay = delayBeforeSwimmingToHookField.getInt(hook);

            delayBeforeSwimmingToHookField.setAccessible(false);

            return delay;
        } catch (IllegalArgumentException | IllegalAccessException e) {
            delayBeforeSwimmingToHookField.setAccessible(false);

            e.printStackTrace();
        }

        delayBeforeSwimmingToHookField.setAccessible(false);

        return -1;
    }

    private int getDelayBeforeBite(EntityFishHook hook) {
        if (delayBeforeBiteField == null)
            return -1;

        delayBeforeBiteField.setAccessible(true);

        try {
            int delay = delayBeforeBiteField.getInt(hook);

            delayBeforeBiteField.setAccessible(false);

            return delay;
        } catch (IllegalArgumentException | IllegalAccessException e) {
            delayBeforeBiteField.setAccessible(false);

            e.printStackTrace();
        }

        delayBeforeBiteField.setAccessible(false);

        return -1;
    }

    private void setBiteInterval(EntityFishHook hook, int time) {
        if (biteIntervalField == null)
            return;

        biteIntervalField.setAccessible(true);

        try {
            biteIntervalField.setInt(hook, time);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            biteIntervalField.setAccessible(false);

            e.printStackTrace();
        }

        biteIntervalField.setAccessible(false);
    }

    private HookState getHookState(EntityFishHook hook) {
        currentStateField.setAccessible(true);

        try {
            Enum hookEnum = (Enum) currentStateField.get(hook);
            int hookStateOrdinal = hookEnum.ordinal();

            currentStateField.setAccessible(false);

            return HookState.values()[hookStateOrdinal];
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        currentStateField.setAccessible(false);

        return null;
    }
}
