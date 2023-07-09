package ca.lightnet.FaevaleEngine.components;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.libraries.models.objects.Component;
import ca.lightnet.FaevaleEngine.listeners.GiveBlockDropsListener;
import ca.lightnet.FaevaleEngine.listeners.RegenerateBlockListener;
import ca.lightnet.FaevaleEngine.listeners.WildsBlockBreakListener;
import ca.lightnet.FaevaleEngine.runnables.ClearRegenTaskFromDB;
import ca.lightnet.FaevaleEngine.runnables.RespawnBlockTask;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.ResultSet;

public class BlockBreakComponent extends Component {

    @Override
    public void onLoad(){
        registerListener(new WildsBlockBreakListener(getComponentName()));
        registerListener(new RegenerateBlockListener(getComponentName()));
        registerListener(new GiveBlockDropsListener(getComponentName()));
        registerConfig();
        createDBTable();
        clearTable();
    }

    @Override
    public void onSave(){
        this.saveConfig();
    }

    @Override
    public void onUnload(){
        this.saveConfig();
    }

    //TODO: clean up the SQL calls, i don't like this being in the component class.
    private void createDBTable() {
        Connection con = FaevaleEngine.getInstance().getDBConnection();
        String tableName = FaevaleEngine.getInstance().getConfig().getString("table_prefix","faevale_")+"regen_queue";
        String sql = "CREATE TABLE IF NOT EXISTS "+tableName+"(  \n" +
                "    ID VARCHAR(40) NOT NULL,  \n" +
                "    WORLD VARCHAR(50),  \n" +
                "    X DECIMAL(7,4) NOT NULL,  \n" +
                "    Y DECIMAL(7,4) NOT NULL,  \n" +
                "    Z DECIMAL(7,4) NOT NULL,  \n" +
                "    PITCH DECIMAL(7,4),  \n" +
                "    YAW DECIMAL(7,4),  \n" +
                "    BLOCK_DATA VARCHAR(4000) NOT NULL,  \n" +
                "    TIMER INT NOT NULL,  \n" +
                "    PRIMARY KEY(ID)  \n" +
                ");";

        if(con == null) { return; }
        try {
            con.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void clearTable() {
        Connection con = FaevaleEngine.getInstance().getDBConnection();
        String tableName = FaevaleEngine.getInstance().getConfig().getString("table_prefix","faevale_")+"regen_queue";
        String sql = "SELECT * FROM  "+tableName+";";
        ResultSet results = null;

        if(con == null) { return; }
        try {
            results = con.createStatement().executeQuery(sql);
            while(results.next()) {
                new RespawnBlockTask(
                        results.getString("WORLD"),
                        results.getDouble("X"),
                        results.getDouble("Y"),
                        results.getDouble("Z"),
                        results.getFloat("PITCH"),
                        results.getFloat("YAW"),
                        Bukkit.getServer().createBlockData(results.getString("BLOCK_DATA"))
                ).runTaskLater(FaevaleEngine.getInstance(),results.getLong("TIMER"));

                new ClearRegenTaskFromDB(
                        results.getString("ID")
                ).runTaskLater(FaevaleEngine.getInstance(),results.getLong("TIMER"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
