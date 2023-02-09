package net.theawesomegem.fishingmadebetter.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;

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
        boolean loaded = CustomConfigurationHandler.loadFishes();

        String message;

        if (loaded) {
            message = "notif.fishingmadebetter.command.reload_success";
        } else {
            message = "notif.fishingmadebetter.command.reload_fail";
        }

        sender.sendMessage(new TextComponentTranslation(message));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }
}
