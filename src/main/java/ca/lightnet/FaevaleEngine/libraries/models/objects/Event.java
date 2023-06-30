package ca.lightnet.FaevaleEngine.libraries.models.objects;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public abstract class Event extends org.bukkit.event.Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();;
    private boolean cancelled = false;
    private String cancelledMessage = "This event was cancelled!";

    public boolean isCancelled() { return this.cancelled; }
    public void setCancelled(boolean b) { this.cancelled = b; }
    public String getCancelledMessage() { return this.cancelledMessage; }
    public void setCancelledMessage(String msg) { this.cancelledMessage = msg; }

    @Override
    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }

    public abstract Player getPlayer();
}
