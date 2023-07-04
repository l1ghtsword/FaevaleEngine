package ca.lightnet.FaevaleEngine.listeners;

import ca.lightnet.FaevaleEngine.events.FaevaleDestroyEvent;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Listener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GiveBlockDropsListener extends Listener {

    public GiveBlockDropsListener(String componentName) { super(componentName); }

    @EventHandler
    public void onFaevaleBlockDestroy(FaevaleDestroyEvent e) {

        if(e.isCancelled()) { return;}

        if(canAddItems(e.getDrops(),e.getPlayer())){
            addItems(e.getDrops(),e.getPlayer());
        }else{
            e.setCancelled(true);
            e.setCancelMessage("You have no more room to store that!");
        }
    }

    private void addItems(Collection<ItemStack> drops, Player player){
        for(ItemStack is : drops) { player.getInventory().addItem(is); }
    }

    private boolean canAddItems(Collection<ItemStack> drops, Player player) {
        Map<Integer,ItemStack> results = new HashMap<>();
        Inventory testInv = Bukkit.createInventory(null, InventoryType.PLAYER);

        try {
            testInv.setContents(player.getInventory().getContents());
            testInv.setStorageContents(player.getInventory().getStorageContents());

            for(ItemStack is : drops) {
                results = testInv.addItem(is);
                if(!results.isEmpty()) { return false; }
            }
        } catch(IllegalArgumentException err) {
            err.getStackTrace();
            return false;
        }
        return  true;
    }
}