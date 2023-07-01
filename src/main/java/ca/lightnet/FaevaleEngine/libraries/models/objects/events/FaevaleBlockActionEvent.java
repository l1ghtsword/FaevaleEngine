package ca.lightnet.FaevaleEngine.libraries.models.objects.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public abstract class FaevaleBlockActionEvent extends Event {

    public abstract Material getMaterial();
    public abstract Location getLocation();
    public abstract BlockData getBlockData();
    public abstract Collection<ItemStack> getDrops();
}
