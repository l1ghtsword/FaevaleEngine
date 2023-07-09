package ca.lightnet.FaevaleEngine.listeners;

import ca.lightnet.FaevaleEngine.events.FaevaleDestroyEvent;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Map;

public class GiveBlockDropsListener extends Listener {

    public GiveBlockDropsListener(String componentName) { super(componentName); }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFaevaleBlockDestroy(FaevaleDestroyEvent e) {

        if(e.isCancelled()) { return;}

        if(canAddItems(e.getDrops(),e.getPlayer())){
            addItems(e.getDrops(),e.getPlayer());
        }else{
            e.setCancelled(true);
            e.setCancelledMessage("You have no more room to store that!");
        }
    }

    private void addItems(Collection<ItemStack> drops, Player player){
        drops.forEach((item) -> player.getInventory().addItem(item));
    }

    private boolean canAddItems(Collection<ItemStack> drops, Player player) {

        if(drops.size() == 1 && player.getInventory().firstEmpty() >= 0) { return true; }

        for(ItemStack item : drops) {
            Map<Integer, ? extends ItemStack> slots = player.getInventory().all(item.getType());
            int amount = item.getAmount();
            boolean hasRoom = false;

            for(Map.Entry<Integer, ? extends ItemStack> slot : slots.entrySet()){
                int remainder = (amount + slot.getValue().getAmount()) - item.getMaxStackSize();
                if(remainder <= 0) {
                    hasRoom = true;
                    break;
                } else {
                    amount = remainder;
                }
            }
            if(!hasRoom) { return false; }
        }
        return true;
    }
}