package ca.lightnet.FaevaleEngine.libraries.models.objects;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import org.bukkit.command.CommandSender;

public abstract class Command {

    private final Component component;

    public Command(Component component) {
        this.component = component;
    }
    public abstract String getName();

    public abstract String getDescription();

    public abstract  String getUsage();
    public abstract  String getPermission();

    public abstract boolean onCommand(CommandSender sender, String args[]);

    public String getComponentName() { return this.component.getComponentName(); }
    public FaevaleEngine getPlugin() { return this.component.getPlugin(); }
}
