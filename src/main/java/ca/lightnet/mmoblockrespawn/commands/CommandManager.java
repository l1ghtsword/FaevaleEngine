package ca.lightnet.mmoblockrespawn.commands;

import ca.lightnet.mmoblockrespawn.MmoBlockRespawn;
import ca.lightnet.mmoblockrespawn.utils.utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class CommandManager implements CommandExecutor{
    FileConfiguration config = MmoBlockRespawn.getConfigFile();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(args.length == 0) {
            sender.sendMessage(getHelp());
            return true;
        }

        switch (args[0].toLowerCase()) {
            case ("debug"):
                toggleDebug(sender);
            case ("test"):
                MmoBlockRespawn.LOGGER.info("Regenerating block for " + sender.getName());
            default: // "help" command implied
                sender.sendMessage(getHelp());
        }
        return true;
    }

    private String getHelp(){
        return  "/regen help  - Get list of usable commands\n" +
                "/regen debug - Toggle debugging\n" +
                "/regen test  - For developer testing only";
    }

    private void toggleDebug(CommandSender sender) {

        Boolean status;
        String message;

        if (!config.isSet("debug")) {
            status = true;
            message = "debug unset in config, starting debugging";
        } else {
            status = utility.toggleBool(config.getBoolean("debug"));
            message = "Debugging set to " + status;
        }
        config.set("debug",status);
        MmoBlockRespawn.LOGGER.warning(message);
        sender.sendMessage(message);
    }
}
