package net.theawesomegem.fishingmadebetter.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;
import org.lwjgl.input.Keyboard;

/**
 * Created by TheAwesomeGem on 12/27/2017.
 */
public class KeybindManager {
    private static KeyBinding reelIn;
    private static KeyBinding reelOut;

    public static void load() {
        reelIn = new KeyBinding("key.reelIn", Keyboard.KEY_X, "key.fishingmadebetter.category");
        reelOut = new KeyBinding("key.reelOut", Keyboard.KEY_Z, "key.fishingmadebetter.category");

        ClientRegistry.registerKeyBinding(reelIn);
        ClientRegistry.registerKeyBinding(reelOut);
    }

    public static boolean isReelInPressed() {
        return ConfigurationManager.reelingHold ? reelIn.isKeyDown() : reelIn.isPressed();
    }

    public static boolean isReelOutPressed() {
        return ConfigurationManager.reelingHold ? reelOut.isKeyDown() : reelOut.isPressed();
    }
}
