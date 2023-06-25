package ca.lightnet.FaevaleEngine.libraries.models.objects;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.libraries.services.CommandRegistry;
import ca.lightnet.FaevaleEngine.libraries.services.ConfigRegistry;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public abstract class Component {

    private final String name;
    private final CommandRegistry commandRegistry;
    private final ConfigRegistry configRegistry;

    public Component() {
        this.name = this.getClass().getSimpleName();
        this.commandRegistry = FaevaleEngine.getCommandRegistry();
        this.configRegistry = FaevaleEngine.getConfigRegistry();
    }

    public final void registerListener(Listener eventListener) { Bukkit.getServer().getPluginManager().registerEvents(eventListener, FaevaleEngine.getInstance()); }
    public final void registerCommand(Command command) { commandRegistry.addCommand(command); }
    public final void registerConfig() { configRegistry.addConfig(this.getComponentName()); }

    public final String getComponentName() { return this.name; }
    public final FileConfiguration getConfig() {return this.configRegistry.getConfig(this.getComponentName()); }
    public final void saveConfig() { this.configRegistry.saveConfig(this.getComponentName()); }
    public final void reloadConfig() { this.configRegistry.reloadConfig(this.getComponentName()); }

    public final void onReload() {
        this.onUnload();
        this.onLoad();
    }
    public abstract void onLoad();
    public abstract void onSave();
    public abstract void onUnload();
}