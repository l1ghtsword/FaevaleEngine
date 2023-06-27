package ca.lightnet.FaevaleEngine.libraries.models.objects;


import ca.lightnet.FaevaleEngine.FaevaleEngine;

public abstract class Listener implements org.bukkit.event.Listener {

    private final String componentName;

    public Listener(String componentName) { this.componentName = componentName; }
    public String getComponentName() { return this.componentName; }
    public FaevaleEngine getPlugin() { return FaevaleEngine.getInstance(); }
}