package ca.lightnet.FaevaleEngine.runnables;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;

public class SerializeRegenTaskToDB extends BukkitRunnable {
    private final String id;
    private final Location loc;
    private final BlockData bd;
    private final Long t;
    private final String prefix;
    private final Connection con;

    public SerializeRegenTaskToDB(String id, Location location, BlockData blockdata, Long timer) {
        this.id = id;
        this.loc = location;
        this.bd = blockdata;
        this.t = timer;
        this.prefix = FaevaleEngine.getInstance().getConfig().getString("table_prefix","faevale_");
        this.con  =  FaevaleEngine.getInstance().getDBConnection();
    }

    @Override
    public void run() {
        if(con == null) { return; }
        String sql = "INSERT INTO "+prefix+"regen_queue (ID,WORLD,X,Y,Z,PITCH,YAW,BLOCK_DATA,TIMER)\n" +
                     "VALUES ('"+id+"','"+loc.getWorld().getName()+"','"+loc.getX()+"','"+loc.getY()+"','"+loc.getZ()+
                     "','"+loc.getPitch()+"','"+loc.getYaw()+"','"+bd.getAsString()+"','"+t+"');";
        try {
            con.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}