package ca.lightnet.FaevaleEngine.events;

import ca.lightnet.FaevaleEngine.libraries.models.objects.Event;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class FaevaleDestroyEvent extends Event {
    private final Player player;
    private final Material material;
    private final Location location;
    private final BlockData blockData;
    private final Collection<ItemStack> drops;

    public FaevaleDestroyEvent(Player player, Material material, Location location, BlockData blockData, Collection<ItemStack> drops) {
        this.player = player;
        this.material = material;
        this.location = location;
        this.blockData = blockData;
        this.drops = drops;
    }

    public Material getMaterial() {
        return material;
    }

    public Location getLocation() {
        return location;
    }

    public BlockData getBlockData() {
        return blockData;
    }

    public Collection<ItemStack> getDrops() {
        return drops;
    }

    @Override
    public Player getPlayer() { return player; }
}
