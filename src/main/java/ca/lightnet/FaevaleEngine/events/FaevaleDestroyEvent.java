package ca.lightnet.FaevaleEngine.events;

import ca.lightnet.FaevaleEngine.libraries.models.objects.events.FaevaleBlockActionEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.Collection;

public class FaevaleDestroyEvent extends FaevaleBlockActionEvent {
    private final Player player;
    private final Location location;
    private final BlockData blockData;
    private final Material material;
    private final Collection<ItemStack> drops;
    private final String propString;

    public FaevaleDestroyEvent(Player player, Location location, @Nullable BlockData blockData, @Nullable Material material, @Nullable Collection<ItemStack> drops, @Nullable String propString) {
        this.player = player;
        this.location = location;
        this.blockData = blockData;
        this.material = material;
        this.drops = drops;
        this.propString = propString;
    }


    @Override
    public Material getMaterial() {
        return this.material;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public BlockData getBlockData() {
        return this.blockData;
    }

    @Override
    public Collection<ItemStack> getDrops() {
        return this.drops;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    public String getPropString() { return this.propString; }
}
