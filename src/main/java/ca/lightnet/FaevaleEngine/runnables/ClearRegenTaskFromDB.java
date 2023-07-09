package ca.lightnet.FaevaleEngine.runnables;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;

public class ClearRegenTaskFromDB extends BukkitRunnable {
    private final String id;
    private final String prefix;
    private final Connection con;

    public ClearRegenTaskFromDB(String id) {
        this.id = id;
        this.prefix = FaevaleEngine.getInstance().getConfig().getString("table_prefix","faevale_");
        this.con  =  FaevaleEngine.getInstance().getDBConnection();
    }

    @Override
    public void run() {
        if(con == null) { return; }
        String sql = "DELETE FROM "+prefix+"regen_queue WHERE ID = '"+id+"';";
        try {
            con.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}