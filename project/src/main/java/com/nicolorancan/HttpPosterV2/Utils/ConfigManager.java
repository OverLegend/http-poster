package com.nicolorancan.HttpPosterV2.Utils;

import com.nicolorancan.HttpPosterV2.Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;

public class ConfigManager {

    private FileConfiguration config;
    private Main plugin;
    private String version;

    public ConfigManager(Main plugin) {

        this.config = plugin.getConfig();
        this.version = plugin.getDescription().getVersion();
        this.plugin = plugin;

        if (config.get("version") == null) {
            this.config.set("version", this.version);

            this.config.set("server-name", "server");

            // Events

            this.config.set("events.player.join-leave.enabled", false);
            this.config.set("events.player.join-leave.endpoint", "http://www.example.com");

            this.config.set("events.player.chatted.enabled", false);
            this.config.set("events.player.chatted.endpoint", "http://www.example.com");

            String[] breakBlacklist = {"GRASS_BLOCK", "COBBLESTONE"};

            this.config.set("events.player.block-broken.enabled", false);
            this.config.set("events.player.block-broken.endpoint", "http://www.example.com");
            this.config.set("events.player.block-broken.blacklist", Arrays.asList(breakBlacklist));

            String[] placeBlacklist = {"GRASS_BLOCK", "COBBLESTONE"};

            this.config.set("events.player.block-placed.enabled", false);
            this.config.set("events.player.block-placed.endpoint", "http://www.example.com");
            this.config.set("events.player.block-placed.blacklist", Arrays.asList(placeBlacklist));

            String[] entityBlacklist = {"BAT", "ZOMBIE"};

            this.config.set("events.player.mob-killed.enabled", false);
            this.config.set("events.player.mob-killed.endpoint", "http://www.example.com");
            this.config.set("events.player.mob-killed.blacklist", Arrays.asList(entityBlacklist));

            this.config.set("events.server.start-stop.enabled", false);
            this.config.set("events.server.start-stop.endpoint", "http://www.example.com");

            // Timers

            this.config.set("timers.players.enabled", false);
            this.config.set("timers.players.endpoint", "http://www.example.com");
            this.config.set("timers.players.start-delay", 1500);
            this.config.set("timers.players.interval-delay", 10000);

            // Messages

            this.config.set("messages.no-permission", "You don't have the permission to run this command.");
            this.config.set("messages.not-found", "Command not found. Use /http help for a list of the available commands.");
        }
    }

    public void reload() {
        this.plugin.reloadConfig();
        this.plugin.saveConfig();
        this.plugin.saveDefaultConfig();
        this.config = this.plugin.getConfig();
    }

    public boolean isEnabled(String optionPath) { return this.config.getBoolean(optionPath); }
    public String getOptionUrl(String optionPath) { return this.config.getString(optionPath); }
    public List<String> getArray(String optionPath) { return this.config.getStringList(optionPath); }
}
