package ca.lightnet.FaevaleEngine.libs.Models.Objects;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.ArrayList;
import java.util.List;

public class CommandRegistry implements CommandExecutor {

    private final List<Command> commandList;

    public CommandRegistry() {
        commandList = new ArrayList<Command>();
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(args == null || args.length == 0) { return false; }
        for ( Command c : getCommands()) {
            if (args[0].equalsIgnoreCase(c.getName())) {
                c.perform(sender,args);
                return true;
            }
        }
        return false;
    }

    public void addCommand(Command c) {
        commandList.add(c);
    }

    public List<Command> getCommands() {
        return commandList;
    }
}
