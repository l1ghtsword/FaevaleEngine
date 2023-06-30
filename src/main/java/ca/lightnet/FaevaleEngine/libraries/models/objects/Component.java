package ca.lightnet.FaevaleEngine.libraries.models.objects;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public abstract class Component {

    private final String name;

    public Component() {
        this.name = this.getClass().getSimpleName();
    }

    public final void registerListener(Listener eventListener) { Bukkit.getServer().getPluginManager().registerEvents(eventListener, FaevaleEngine.getInstance()); }
    public final void registerCommand(Command command) { FaevaleEngine.getInstance().getCommandRegistry().addCommand(command); }
    public final void registerConfig() { FaevaleEngine.getInstance().getConfigRegistry().addConfig(this.getComponentName()); }

    public final String getComponentName() { return this.name; }
    public final FileConfiguration getConfig() {return FaevaleEngine.getInstance().getConfigRegistry().getConfig(this.getComponentName()); }
    public final void saveConfig() { FaevaleEngine.getInstance().getConfigRegistry().saveConfig(this.getComponentName()); }
    public final void reloadConfig() { FaevaleEngine.getInstance().getConfigRegistry().reloadConfig(this.getComponentName()); }

    public final void onReload() {
        this.onUnload();
        this.onLoad();
    }
    public abstract void onLoad();
    public abstract void onSave();
    public abstract void onUnload();
}