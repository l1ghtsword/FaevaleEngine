package ca.lightnet.FaevaleEngine.libraries.models.objects;

import org.bukkit.command.CommandSender;

public abstract class Command {

    private final String componentName;

    public Command(String componentName) { this.componentName = componentName; }
    public String getComponentName() { return this.componentName; }
    public abstract String getName();
    public abstract String getDescription();
    public abstract  String getUsage();
    public abstract  String getPermission();
    public abstract boolean onCommand(CommandSender sender, String args[]);


}
