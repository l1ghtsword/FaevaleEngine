package ca.lightnet.FaevaleEngine.listeners;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.events.FaevaleDestroyEvent;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Listener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class GiveBlockDropsListener extends Listener {

    private final FileConfiguration config = FaevaleEngine.getConfigRegistry().getConfig(getComponentName());

    public GiveBlockDropsListener(String componentName) { super(componentName); }

    @EventHandler(priority = EventPriority.HIGH)
    public void onFaevaleBlockDestroy(FaevaleDestroyEvent e) {

        if(!e.isCancelled()) {
            // TODO: Implement drop table send to player inventory or ground if full
        }
    }
}