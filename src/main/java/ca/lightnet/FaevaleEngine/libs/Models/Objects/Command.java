package ca.lightnet.FaevaleEngine.libs.Models.Objects;

import org.bukkit.command.CommandSender;

public abstract class Command {

    public abstract String getName();

    public abstract String getDescription();

    public abstract  String getUsage();

    public abstract void perform(CommandSender sender, String args[]);

}
