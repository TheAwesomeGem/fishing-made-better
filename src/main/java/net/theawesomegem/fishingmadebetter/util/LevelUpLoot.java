package net.theawesomegem.fishingmadebetter.util;

import levelup2.skills.SkillRegistry;
import levelup2.util.Library;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;

public class LevelUpLoot {
    public static void doLevelUpLoot(EntityFishHook hook, World world, EntityPlayer player) {
        ItemStack loot = getFishingLoot(world, player);
        if(!loot.isEmpty() && !hook.world.isRemote) {
        	EntityItem item = new EntityItem(hook.world, hook.posX, hook.posY, hook.posZ, loot);
        	
        	if(ConfigurationManager.server.magneticFishing) {
            	item.setPosition(player.posX, player.posY + 1, player.posZ);
            } 
            else {
                double d0 = player.posX - hook.posX;
                double d1 = player.posY - hook.posY;
                double d2 = player.posZ - hook.posZ;
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                item.motionX = d0 * 0.1D;
                item.motionY = d1 * 0.1D + (double) MathHelper.sqrt(d3) * 0.08D;
                item.motionZ = d2 * 0.1D;
            }
            player.world.spawnEntity(item);
        }
    }

    private static ItemStack getFishingLoot(World world, EntityPlayer player) {
        if(!world.isRemote) {
            if(player.getRNG().nextDouble() <= SkillRegistry.getSkillLevel(player, "levelup:fishbonus") * 0.05D) {
                LootContext.Builder build = new LootContext.Builder((WorldServer)world);
                build.withLuck((float) EnchantmentHelper.getMaxEnchantmentLevel(Enchantment.getEnchantmentByLocation("luck_of_the_sea"), player) + player.getLuck());
                return Library.getLootManager().getLootTableFromLocation(new ResourceLocation("levelup", "fishing/fishing_loot")).generateLootForPools(player.getRNG(), build.build()).get(0).copy();
            }
        }
        return ItemStack.EMPTY;
    }
}