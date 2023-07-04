package ca.lightnet.FaevaleEngine.libraries.services;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import org.bukkit.configuration.file.FileConfiguration;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class DatabaseService {

    private final String url,user,pass;
    private final List<Connection> pool = new ArrayList<>();

    public DatabaseService() {
        this.url = "jdbc:mysql://"+getConfig().getString("hostname")+":"+getConfig().getString("port")+
                   "/"+getConfig().getString("dbname")+getConfig().getString("flags");
        this.user = getConfig().getString("username");
        this.pass = getConfig().getString("password");

        for(int i = 0; i < getConfig().getInt("poolSize",5); i++) {
            this.pool.add(getNewConnection());
        }
        FaevaleEngine.getInstance().logInfo("Database connection started!","dbService");
    }

    public void closeConnections() {
        for (Connection con : pool) {
            try {
                con.close();
            } catch (SQLException err) {
                err.getStackTrace();
            }
        }
        FaevaleEngine.getInstance().logInfo("Database connections closed!","dbService");
    }

    public @Nullable Connection getConnection() {
        int LOOP_HALT = 20; //safety breakout limit

        for(int i = 0; i < LOOP_HALT; i++) {
            int select = ThreadLocalRandom.current().nextInt(0, getConfig().getInt("poolSize",5));
            try{
                if(this.pool.get(select).isValid(30)) {
                    return this.pool.get(select);
                } else {
                    this.pool.set(select,getNewConnection());
                }
            } catch(Exception ignore) {
                this.pool.set(select,getNewConnection());
            }
        }
        FaevaleEngine.getInstance().logSevere("No valid database connection exists!","dbService");
        return null;
    }

    private @Nullable Connection getNewConnection() {
        try {
            return DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (SQLException | NullPointerException err) {
            FaevaleEngine.getInstance().logSevere("Unable to create database connections!","dbService");
            err.getStackTrace();
            return null;
        }
    }

    private FileConfiguration getConfig() { return FaevaleEngine.getInstance().getConfig(); }
}
