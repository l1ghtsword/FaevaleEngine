package ca.lightnet.FaevaleEngine.libraries.models.objects;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.libraries.services.CommandRegistry;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public abstract class Component {

    private final String name;
    private final FaevaleEngine plugin;
    private final PluginManager pluginManager;
    private final CommandRegistry commandRegistry;

    public Component() {
        this.name = this.getClass().getSimpleName();
        this.plugin = FaevaleEngine.getInstance();
        this.pluginManager = Bukkit.getServer().getPluginManager();
        this.commandRegistry = FaevaleEngine.getCommandRegistry();
    }

    public final void registerListener(Listener eventListener) {
        pluginManager.registerEvents(eventListener, plugin);
    }
    public final void registerCommand(Command command) { commandRegistry.addCommand(command); }
    public final void registerConfig() {} //Unimplemented

    public final String getComponentName() { return this.name; }
    public final FaevaleEngine getPlugin() {return this.plugin; }

    public abstract void onLoad();
    public abstract void onSave();
    public abstract void onUnload();
}