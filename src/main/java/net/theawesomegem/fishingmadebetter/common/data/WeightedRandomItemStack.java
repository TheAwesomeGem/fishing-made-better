package net.theawesomegem.fishingmadebetter.common.data;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;

/**
 * Created by TheAwesomeGem on 12/24/2017.
 */
public class WeightedRandomItemStack extends WeightedRandom.Item {
    private ItemStack itemStack;

    public ItemStack getItemStack() {
        return itemStack;
    }

    public WeightedRandomItemStack(ItemStack itemStack, int itemWeightIn) {
        super(itemWeightIn);

        this.itemStack = itemStack;
    }
}
