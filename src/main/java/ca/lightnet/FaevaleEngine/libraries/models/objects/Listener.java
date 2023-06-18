package ca.lightnet.FaevaleEngine.libraries.models.objects;

import ca.lightnet.FaevaleEngine.FaevaleEngine;

public abstract class Listener implements org.bukkit.event.Listener {

    private final Component component;

    public Listener(Component component) {
        this.component = component;
    }

    public String getComponentName() { return this.component.getComponentName(); }
    public FaevaleEngine getPlugin() { return this.component.getPlugin(); }
}