package com.nicolorancan.Main.Utils;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private final FileConfiguration configFile;

    public ConfigManager(FileConfiguration file) {
        String pluginVersion = "1.0";
        this.configFile = file;

        if (!this.exists()) {
            file.set("version", pluginVersion);

            file.set("config.server-name", "server");

            file.set("config.events.player.join.enable", false);
            file.set("config.events.player.join.endpoint", "http://example.com");

            file.set("config.events.player.leave.enable", false);
            file.set("config.events.player.leave.endpoint", "http://example.com");

            file.set("config.events.player.advancement-completed.enable", false);
            file.set("config.events.player.advancement-completed.endpoint", "http://example.com");

            file.set("config.events.player.killed.enable", false);
            file.set("config.events.player.killed.endpoint", "http://example.com");



            file.set("config.events.server.start.enable", false);
            file.set("config.events.server.start.endpoint", "http://example.com");

            file.set("config.events.server.stop.enable", false);
            file.set("config.events.server.stop.endpoint", "http://example.com");
        }
    }

    private boolean exists() {
        if (this.configFile.get("version") == null)
            return false;
        else
            return true;
    }

    public boolean isEnabled(String optionPath) { return this.configFile.getBoolean(optionPath); }
    public String getOptionUrl(String optionPath) { return this.configFile.getString(optionPath); }
    public String getServerName() { return this.configFile.getString("config.server-name"); }
}
