package ca.lightnet.FaevaleEngine.libs.Models.Objects;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.libs.Utils.BoolUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class OLD_CommandManager implements CommandExecutor{
    FileConfiguration config = FaevaleEngine.getConfigFile();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(args == null || args.length == 0) { return false; }
        switch (args[0].toLowerCase()) {
            case ("debug"):
                toggleDebug(sender);
                break;
            case ("test"):
                FaevaleEngine.logInfo("testing...");
                break;
            default:
                return false;
        }
        return true;
    }

    //Class Methods
    private void toggleDebug(CommandSender sender) {
        Boolean status;
        String message;

        status = BoolUtils.toggleBool(config.getBoolean("debug",false));
        config.set("debug",status);
        message = "Debugging set to " + status;
        FaevaleEngine.logInfo(message);
        sender.sendMessage(message);
    }
}
