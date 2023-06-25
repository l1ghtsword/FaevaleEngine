package ca.lightnet.FaevaleEngine.libraries.services;

import ca.lightnet.FaevaleEngine.FaevaleEngine;
import ca.lightnet.FaevaleEngine.libraries.utilities.DefaultConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigRegistry {

    private final Map<String,FileConfiguration> configList;
    private final FaevaleEngine plugin = FaevaleEngine.getInstance();

    public ConfigRegistry() { configList = new HashMap<String, FileConfiguration>(); }

    public void addConfig(String componentName) {
        File configFile;
        FileConfiguration config;

        configFile = new File(plugin.getDataFolder(), componentName + ".yml");

        if (!configFile.exists()) {
            try { configFile.createNewFile(); }
            catch (IOException e) {
                FaevaleEngine.logSevere("&4Unable to create " + configFile.getName() + ".yml. Check server storage permissions!", configFile.getName() + "Create");
                return;
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);
        config.addDefaults(DefaultConfig.GetDefaults(componentName));
        config.options().copyDefaults(true);
        try { config.save(configFile); }
        catch(IOException e) {
            FaevaleEngine.logSevere("&4Unable to save " + configFile.getName() + ".yml. ",configFile.getName() + "Save");
        }
        configList.put(componentName, config);
    }

    public FileConfiguration getConfig(String componentName) {
        return configList.get(componentName);
    }

    public void reloadConfig(String componentName) { YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), componentName + ".yml")); }

    public void saveConfig(String componentName) {
        try {
            configList.get(componentName).save(new File(plugin.getDataFolder(),componentName+".yml"));
        }catch(IOException e){
            FaevaleEngine.logSevere("&4Unable to save RegenerationComponent.yml. Check server storage permissions!",componentName+"Config");
        }
    }
}
