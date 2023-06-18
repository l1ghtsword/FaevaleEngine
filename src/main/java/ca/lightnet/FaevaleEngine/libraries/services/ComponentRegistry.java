package ca.lightnet.FaevaleEngine.libraries.services;

import ca.lightnet.FaevaleEngine.libraries.models.objects.Component;

import java.util.ArrayList;
import java.util.List;

public class ComponentRegistry {

    private final List<Component> componentList;

    public ComponentRegistry() {
        componentList = new ArrayList<>();
    }

    public void registerComponent(Component component) {
        componentList.add(component);
    }

    public void load() {
        componentList.forEach(Component::onLoad);
    }

    public void saveAll() {
        componentList.forEach(Component::onSave);
    }

    public void unload() {
        componentList.forEach(Component::onUnload);
    }
}