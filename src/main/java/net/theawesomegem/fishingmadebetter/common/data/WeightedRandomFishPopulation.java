package net.theawesomegem.fishingmadebetter.common.data;

import net.minecraft.util.WeightedRandom;

/**
 * Created by TheAwesomeGem on 12/30/2017.
 */
public class WeightedRandomFishPopulation extends WeightedRandom.Item {
    public final String fishId;
    public final int population;

    public WeightedRandomFishPopulation(int itemWeightIn, String fishId, int population) {
        super(itemWeightIn);

        this.fishId = fishId;
        this.population = population;
    }
}
