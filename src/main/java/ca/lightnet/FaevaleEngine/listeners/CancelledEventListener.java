package ca.lightnet.FaevaleEngine.listeners;

import ca.lightnet.FaevaleEngine.libraries.models.objects.Event;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class CancelledEventListener extends Listener {

    public CancelledEventListener(String componentName) { super(componentName); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCancelledEvent(Event e) {
        if(!e.isCancelled() || e.getPlayer() == null) { return; }
        e.getPlayer().sendMessage("&d[&fFaeVale&d] &4"+e.getCancelledMessage());
    }
}