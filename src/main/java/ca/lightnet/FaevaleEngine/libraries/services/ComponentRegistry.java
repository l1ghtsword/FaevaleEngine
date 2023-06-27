package ca.lightnet.FaevaleEngine.libraries.services;

import ca.lightnet.FaevaleEngine.libraries.models.objects.Component;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class ComponentRegistry {

    private final Map<String,Component> componentMap;

    public ComponentRegistry() { componentMap = new HashMap<>(); }

    public void registerComponent(Component component) { componentMap.put(component.getComponentName(),component); }

    public @Nullable Component getComponent(String componentName) { return componentMap.get(componentMap.get(componentName)); }

    public void load() { componentMap.forEach((k, v) -> v.onLoad()); }

    public void saveAll() {
        componentMap.forEach((k, v) -> v.onSave());
    }

    public void reloadAll() { componentMap.forEach((k, v) -> v.onReload()); }

    public void unload() {
        componentMap.forEach((k, v) -> v.onUnload());
    }
}