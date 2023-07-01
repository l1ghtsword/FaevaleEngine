package ca.lightnet.FaevaleEngine.libraries.models.objects;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class Listener implements org.bukkit.event.Listener {

    private final String componentName;

    public Listener(String componentName) { this.componentName = componentName; }
    public String getComponentName() { return this.componentName; }
    public FileConfiguration getConfig() { return FaevaleEngine.getInstance().getConfigRegistry().getConfig(this.componentName); }
}