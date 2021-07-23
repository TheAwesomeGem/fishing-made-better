package net.theawesomegem.fishingmadebetter.common.capability.fishing;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

/**
 * Created by TheAwesomeGem on 6/22/2017.
 */
public class FishingCapabilityProvider implements ICapabilityProvider {
    @CapabilityInject(IFishingData.class)
    public static final Capability<IFishingData> FISHING_DATA_CAP = null;

    private IFishingData instance = FISHING_DATA_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == FISHING_DATA_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == FISHING_DATA_CAP ? FISHING_DATA_CAP.cast(this.instance) : null;
    }
}
