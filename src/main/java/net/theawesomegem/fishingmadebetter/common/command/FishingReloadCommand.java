package net.theawesomegem.fishingmadebetter.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomFishConfigurationHandler;

public class FishingReloadCommand extends CommandBase {
    private final String parentCommandName;

    public FishingReloadCommand(String parentCommandName) {
        this.parentCommandName = parentCommandName;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return String.format("commands.%s.%s.%s.usage", ModInfo.MOD_ID, parentCommandName, getName());
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        boolean loaded = CustomFishConfigurationHandler.loadFishes();

        String message;

        if (loaded) {
            message = "Config files reloaded for Fishing Made Better.";
        } else {
            message = "Config files could not be loaded because the folder for the fishes data does not exist.";
        }

        sender.sendMessage(new TextComponentString(message));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}
