package ca.lightnet.FaevaleEngine.libraries.models.objects;

import org.bukkit.command.CommandSender;

public abstract class Command {

    public abstract String getName();

    public abstract String getDescription();

    public abstract  String getUsage();
    public abstract  String getPermission();

    public abstract boolean onCommand(CommandSender sender, String args[]);

}
