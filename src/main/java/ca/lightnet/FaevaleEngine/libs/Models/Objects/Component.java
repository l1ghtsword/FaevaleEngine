package ca.lightnet.FaevaleEngine.libs.Models.Objects;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public abstract class Component {

    private final FaevaleEngine plugin;
    private final PluginManager pluginManager;

    public Component() {
        this.plugin = FaevaleEngine.getInstance();
        this.pluginManager = Bukkit.getServer().getPluginManager();
    }

    public final void registerListener(Listener eventListener) {
        pluginManager.registerEvents(eventListener, plugin);
    }
    public final void registerCommand() {} //Unimplemented
    public final void registerConfig() {} //Unimplemented

    public abstract void onLoad();
    public abstract void onSave();
    public abstract void onUnload();
}