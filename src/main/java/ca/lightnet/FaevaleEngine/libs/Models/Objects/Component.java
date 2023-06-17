package ca.lightnet.FaevaleEngine.libs.Models.Objects;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public abstract class Component {

    private final FaevaleEngine plugin;
    private final PluginManager pluginManager;
    private final CommandRegistry commandRegistry;

    public Component() {
        this.plugin = FaevaleEngine.getInstance();
        this.pluginManager = Bukkit.getServer().getPluginManager();
        this.commandRegistry = FaevaleEngine.getCommandRegistry();
    }

    public final void registerListener(Listener eventListener) {
        pluginManager.registerEvents(eventListener, plugin);
    }
    public final void registerCommand(Command command) { commandRegistry.addCommand(command); }
    public final void registerConfig() {} //Unimplemented

    public abstract void onLoad();
    public abstract void onSave();
    public abstract void onUnload();
}