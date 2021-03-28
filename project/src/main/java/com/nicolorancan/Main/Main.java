package com.nicolorancan.Main;

import com.nicolorancan.Main.Commands.CommandManager;
import com.nicolorancan.Main.Listeners.PlayerListener;
import com.nicolorancan.Main.Listeners.ServerListener;
import com.nicolorancan.Main.Utils.ConfigManager;
import com.nicolorancan.Main.Utils.PostRequester;
import com.nicolorancan.Main.Utils.UpdateCheck;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private ConfigManager config;
    private PostRequester poster;
    private ServerListener serverListener;

    public void onEnable() {
        this.poster = new PostRequester();
        this.config = new ConfigManager(this.getConfig());
        this.saveConfig();

        this.serverListener = new ServerListener(this.getPoster(), this.getConfigFile());
        serverListener.triggerStart();

        getServer().getPluginManager().registerEvents(new PlayerListener(this.getPoster(), this.getConfigFile()), this);

        getCommand("httpposter").setExecutor(new CommandManager(this.config, this));

        Logger logger = this.getLogger();

        new UpdateCheck(this, this.config, 90451).getVersion(version -> {
            if (!this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("[HttpPoster] Update available: https://www.spigotmc.org/resources/httpposter.90451/");
            }
        });
    }

    public void onDisable() {
        serverListener.triggerStop();
    }
    public PostRequester getPoster() {return this.poster;}
    public ConfigManager getConfigFile() {return this.config;}
    public void reloadCnf() {
        this.reloadConfig();
        this.config = new ConfigManager(this.getConfig());

        this.serverListener = new ServerListener(this.getPoster(), this.config);
        getServer().getPluginManager().registerEvents(new PlayerListener(this.getPoster(), this.config), this);
        getCommand("httpposter").setExecutor(new CommandManager(this.config, this));

        Logger logger = this.getLogger();
        new UpdateCheck(this, this.config, 90451).getVersion(version -> {
            if (!this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("[HttpPoster] Update available: https://www.spigotmc.org/resources/httpposter.90451/");
            }
        });
    }
}
