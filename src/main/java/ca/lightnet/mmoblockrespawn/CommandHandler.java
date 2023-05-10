package ca.lightnet.mmoblockrespawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(command.getName().equalsIgnoreCase("regen") && sender instanceof Player){
            Player player = (Player) sender;
            String block = args[0];
            //Demo command for test
            Plugin.LOGGER.info("Regenerating " + block + " for " + player.getName());
            return true;
        }else{
            Plugin.LOGGER.info("Command Not Found");
            return false;
        }
    }
}
