package com.nicolorancan.HttpPosterV2.Events;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nicolorancan.HttpPosterV2.Utils.ConfigManager;
import com.nicolorancan.HttpPosterV2.Utils.PostData;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerListener {

    private ConfigManager config;
    private PostData poster;
    private Plugin plugin;

    public ServerListener(ConfigManager config, PostData poster, Plugin plugin) {
        this.config = config;
        this.poster = poster;
        this.plugin = plugin;
    }

    public static boolean isNumeric(String string) {
        int intValue;

        System.out.println(String.format("Parsing string: \"%s\"", string));

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }

    Runnable helloRunnable = () -> {
        if (this.config.isEnabled("timers.players.enabled")) {

            String stringData;
            JsonObject jsonData = new JsonObject();

            JsonArray players = new JsonArray();

            for (Player onlinePlayer : this.plugin.getServer().getOnlinePlayers()) {

                JsonObject playerData = new JsonObject();

                String playerName = onlinePlayer.getName();

                UUID playerUUID = onlinePlayer.getUniqueId();

                float playerExp = onlinePlayer.getExp();

                int playerExpLevel = onlinePlayer.getLevel();

                double playerHealth = onlinePlayer.getHealthScale();
                double playerFood = onlinePlayer.getFoodLevel();

                int playerKills = onlinePlayer.getStatistic(Statistic.PLAYER_KILLS);
                int playerEntityKills = onlinePlayer.getStatistic(Statistic.MOB_KILLS);
                int playerDeaths = onlinePlayer.getStatistic(Statistic.DEATHS);

                int playerLastDeath = onlinePlayer.getStatistic(Statistic.TIME_SINCE_DEATH);

                playerData.addProperty("player-name", playerName);
                playerData.addProperty("player-uuid", String.valueOf(playerUUID));
                playerData.addProperty("player-exp", playerExp);
                playerData.addProperty("player-exp-level", playerExpLevel);

                JsonObject playerDataStats = new JsonObject();

                playerDataStats.addProperty("player-healthbar", playerHealth);
                playerDataStats.addProperty("player-foodbar", playerFood);
                playerDataStats.addProperty("player-kills", playerKills);
                playerDataStats.addProperty("player-entity-kills", playerEntityKills);
                playerDataStats.addProperty("player-deaths", playerDeaths);
                playerDataStats.addProperty("player-last-death", playerLastDeath);

                playerData.add("player-statistics", playerDataStats);
                players.add(playerData);
            }

            jsonData.addProperty("server-name", this.config.getOptionUrl("server-name"));
            jsonData.add("players", players);

            stringData = jsonData.toString();

            this.poster.sendData(this.config.getOptionUrl("timers.players.endpoint"), stringData, false);
        }
    };

    public void serverStarted() {
        if (this.config.isEnabled("events.server.start-stop.enabled")) {
            String serverName = this.config.getOptionUrl("server-name");
            String eventType = "start";

            String[] keys = {"server-name", "type"};
            String[] values = {serverName, eventType};

            this.poster.sendData(this.config.getOptionUrl("events.server.start-stop.endpoint"), keys, values, false);
        }

        int initial = Integer.parseInt(this.config.getOptionUrl("timers.players.start-delay"));
        int interval = Integer.parseInt(this.config.getOptionUrl("timers.players.interval-delay"));

        if (initial < 0)
            initial = 0;
        if (interval <= 0)
            interval = 1;

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this.helloRunnable, initial, interval, TimeUnit.SECONDS);
    }

    public void serverStopped() {
        if (this.config.isEnabled("events.server.start-stop.enabled")) {
            String serverName = this.config.getOptionUrl("server-name");
            String eventType = "stop";

            String[] keys = {"server-name", "type"};
            String[] values = {serverName, eventType};

            this.poster.sendData(this.config.getOptionUrl("events.server.start-stop.endpoint"), keys, values, true);
        }
    }
}
