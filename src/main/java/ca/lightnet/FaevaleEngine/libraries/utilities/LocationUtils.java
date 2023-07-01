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

    private final static List<Location> locations = new ArrayList<>();
    private static Material material;

    public static List<Location> getAllLinearBlocks(Location currentLoc, Object block) {
        material = getMaterial(block);
        if(material == null){ locations.add(currentLoc); return locations; }

        //Find the bottom
        do { currentLoc.add(Direction.DOWN.getVector());
        } while (currentLoc.getBlock().getType() != material);

        //Correct current position
        currentLoc.add(Direction.UP.getVector());

        //Add each location from bottom to top to the list
        do { locations.add(currentLoc); currentLoc.add(Direction.UP.getVector());
        } while (currentLoc.getBlock().getType() == material);

        return locations;
    }

    public static List<Location> getAllAdjacentBlocks(Location currentLoc, Object block){
        material = getMaterial(block);
        if(material == null){ locations.add(currentLoc); return locations; }

        locations.add(new Location(currentLoc.getWorld(),currentLoc.getX(),currentLoc.getY(),currentLoc.getZ()).add(Direction.NORTH.getVector()));
        locations.add(new Location(currentLoc.getWorld(),currentLoc.getX(),currentLoc.getY(),currentLoc.getZ()).add(Direction.SOUTH.getVector()));
        locations.add(new Location(currentLoc.getWorld(),currentLoc.getX(),currentLoc.getY(),currentLoc.getZ()).add(Direction.EAST.getVector()));
        locations.add(new Location(currentLoc.getWorld(),currentLoc.getX(),currentLoc.getY(),currentLoc.getZ()).add(Direction.WEST.getVector()));

        return locations;
    }

    private static @Nullable Material getMaterial(Object obj) {
        if(obj instanceof Material) {
            return (Material) obj;
        } else if (obj instanceof BlockData) {
            return ((BlockData) obj).getMaterial();
        } else if(obj instanceof Block) {
            return ((Block) obj).getType();
        } else {
            return null;
        }
    }
}
