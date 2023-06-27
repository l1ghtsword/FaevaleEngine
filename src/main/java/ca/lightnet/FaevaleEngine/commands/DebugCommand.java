package ca.lightnet.FaevaleEngine.commands;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Command;
import ca.lightnet.FaevaleEngine.libraries.utilities.BoolUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class DebugCommand extends Command {

    FileConfiguration config = FaevaleEngine.getConfigFile();

    public DebugCommand(String componentName) {
        super(componentName);
    }

    @Override
    public String getName() {
        return "debug";
    }

    @Override
    public String getDescription() {
        return "Toggles global debugging to console";
    }

    @Override
    public String getUsage() {
        return "debug";
    }

    @Override
    public String getPermission() {
        return "debug";
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        Boolean status = BoolUtils.toggleBool(config.getBoolean("debug",false));
        config.set("debug",status);
        FaevaleEngine.logInfo("Debugging set to " + status,getComponentName());
        sender.sendMessage("Debugging set to " + status);
        return true;
    }
}
