package ca.lightnet.FaevaleEngine.libraries.models.objects;

public abstract class Listener implements org.bukkit.event.Listener {

    private final String componentName;

    public Listener(String componentName) { this.componentName = componentName; }
    public String getComponentName() { return this.componentName; }
}