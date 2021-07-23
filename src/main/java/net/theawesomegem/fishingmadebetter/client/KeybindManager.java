package net.theawesomegem.fishingmadebetter.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/**
 * Created by TheAwesomeGem on 12/27/2017.
 */
public class KeybindManager {
    public static KeyBinding reelIn;
    public static KeyBinding reelOut;

    public static void load() {
        reelIn = new KeyBinding("key.reelIn", Keyboard.KEY_LEFT, "key.fishingmadebetter.category");
        reelOut = new KeyBinding("key.reelOut", Keyboard.KEY_RIGHT, "key.fishingmadebetter.category");

        ClientRegistry.registerKeyBinding(reelIn);
        ClientRegistry.registerKeyBinding(reelOut);
    }
}
