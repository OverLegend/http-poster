package com.nicolorancan.HttpPosterV2;

import com.nicolorancan.HttpPosterV2.Commands.CommandManager;
import com.nicolorancan.HttpPosterV2.Events.PlayerListener;
import com.nicolorancan.HttpPosterV2.Events.ServerListener;
import com.nicolorancan.HttpPosterV2.Utils.ConfigManager;
import com.nicolorancan.HttpPosterV2.Utils.PostData;
import com.nicolorancan.HttpPosterV2.Utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.util.Consumer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {

    private ConfigManager configManager;
    private PostData poster;
    private ServerListener serverListener;

    @Override
    public void onEnable() {

        Logger logger = this.getLogger();

        new UpdateChecker(this, 90451).getVersion(version -> {
            if (!this.getDescription().getVersion().equalsIgnoreCase(version))
                logger.info("There is a new update available.");
        });

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);

            this.configManager = new ConfigManager(this);
            this.poster = new PostData(this.configManager, this);
            this.saveConfig();

            this.getCommand("http").setExecutor(new CommandManager(this, this.configManager));
            this.getServer().getPluginManager().registerEvents(new PlayerListener(this.configManager, this.poster), this);

            this.serverListener = new ServerListener(this.configManager, this.poster, this);

            this.serverListener.serverStarted();
        } else {
            logger.warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        this.serverListener.serverStopped();
    }
}
