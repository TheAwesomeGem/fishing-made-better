package net.theawesomegem.fishingmadebetter.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.server.command.CommandTreeBase;
import net.theawesomegem.fishingmadebetter.ModInfo;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;

public class FishingCommand extends CommandTreeBase {
    public FishingCommand() {
        this.addSubcommand(new FishingReloadCommand(getName()));
    }

    @Override
    public String getName() {
        return "fishing";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return String.format("commands.%s.%s.usage", ModInfo.MOD_ID, getName());
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        World world = sender.getEntityWorld();

        if (world.isRemote) {
            return;
        }

        if (args.length > 0) {
            ICommand cmd = getSubCommand(args[0]);

            if (cmd == null) {
                String subCommandsString = getAvailableSubCommandsString(server, sender);

                throw new CommandException("commands.tree_base.invalid_cmd.list_subcommands", args[0], subCommandsString);
            } else if (!cmd.checkPermission(server, sender)) {
                throw new CommandException("commands.generic.permission");
            } else {
                cmd.execute(server, sender, shiftArgs(args));
            }

            return;
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    //Y u make this private and not protected CommandTreeBase.java class?
    //============================================================================
    private static String[] shiftArgs(@Nullable String[] s) {
        if (s == null || s.length == 0) {
            return new String[0];
        }

        String[] s1 = new String[s.length - 1];
        System.arraycopy(s, 1, s1, 0, s1.length);

        return s1;
    }

    private String getAvailableSubCommandsString(MinecraftServer server, ICommandSender sender) {
        Collection<String> availableCommands = new ArrayList<>();

        for (ICommand command : getSubCommands()) {
            if (command.checkPermission(server, sender)) {
                availableCommands.add(command.getName());
            }
        }

        return CommandBase.joinNiceStringFromCollection(availableCommands);
    }
    //============================================================================
}
