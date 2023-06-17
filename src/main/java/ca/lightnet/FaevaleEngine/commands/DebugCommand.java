package ca.lightnet.FaevaleEngine.commands;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.libs.Models.Objects.Command;
import ca.lightnet.FaevaleEngine.libs.Utils.BoolUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class DebugCommand extends Command {

    FileConfiguration config = FaevaleEngine.getConfigFile();

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
    public void perform(CommandSender sender, String[] args) {
        Boolean status = BoolUtils.toggleBool(config.getBoolean("debug",false));
        config.set("debug",status);
        FaevaleEngine.logInfo("Debugging set to " + status);
        sender.sendMessage("Debugging set to " + status);
    }
}
