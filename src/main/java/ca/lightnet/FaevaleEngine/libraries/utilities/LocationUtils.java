package ca.lightnet.FaevaleEngine.libraries.utilities;

import ca.lightnet.FaevaleEngine.libraries.models.enums.Direction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public final class LocationUtils {

    private static final int LOOP_HALT = 20;

    public static Location getAbove(Location location) { return location.clone().add(Direction.UP.getVector()); }

    public static Location getBelow(Location location) { return location.clone().add(Direction.DOWN.getVector()); }

    public static Location getBottomOfStack(Location location, Material material) {
        Location current = location.clone();

        if(!material.isSolid() || material.equals(Material.BAMBOO)) {
            for (int i = 0; i < LOOP_HALT; i++) {
                if (!getBelow(current).getBlock().getType().equals(material)) {
                    return current; //If below is different, current is the bottom
                } else {
                    current = getBelow(current); //move to next block
                }
            }
        }
        return current;
    }

    public static Location getTopOfStack(Location location, Material material) {
        Location current = location.clone();

        if(!material.isSolid() || material.equals(Material.BAMBOO)) {
            for (int i = 0; i < LOOP_HALT; i++) {
                if (!getAbove(current).getBlock().getType().equals(material)) {
                    return current; //If below is different, current is the bottom
                } else {
                    current = getAbove(current); //move to next block
                }
            }
        }
        return current;
    }

    public static List<Location> getAllLinearBlocks(Location location, Object block) {
        List<Location> locations = new ArrayList<>();
        Material material = getMaterial(block);

        //getMaterial() is nullable
        if(material == null){ locations.add(location.clone());  return locations; }

        Location start = getBottomOfStack(location,material);
        Location finish = getTopOfStack(location,material);

        //Reorder start and finish
        if(material.equals(Material.VINE)) {
            Location temp = start.clone();
            start = finish.clone();
            finish = temp.clone();
        }

        //invertible for-loop to add locations to the list from start to finish.
        Location current = start.clone();
        for (int i = start.getBlockY(); i != finish.getBlockY();) {
            locations.add(new Location(location.getWorld(), location.getX(), i, location.getZ()));
            if (start.getBlockY() < finish.getBlockY()) { i++; }
            else { i--; }
        }
        locations.add(finish.clone()); //manually add finish as it always gets skipped (also handles start == finish condition);

        return locations;
    }

    public static List<Location> getAllAdjacentBlocks(Location location, Object block){
        List<Location> locations = new ArrayList<>();
        Location currentLoc = location.clone();
        Material material = getMaterial(block);

        if(material == null){ locations.add(currentLoc.clone()); return locations; }

        locations.add(currentLoc.clone().add(Direction.NORTH.getVector()));
        locations.add(currentLoc.clone().add(Direction.SOUTH.getVector()));
        locations.add(currentLoc.clone().add(Direction.EAST.getVector()));
        locations.add(currentLoc.clone().add(Direction.WEST.getVector()));

        return locations;
    }

    private static @Nullable Material getMaterial(Object obj) {
        if(obj instanceof Material) { return (Material) obj; }
        else if (obj instanceof BlockData) { return ((BlockData) obj).getMaterial(); }
        else if(obj instanceof Block) { return ((Block) obj).getType(); }
        else { return null; }
    }
}
