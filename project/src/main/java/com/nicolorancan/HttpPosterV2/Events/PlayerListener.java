package com.nicolorancan.HttpPosterV2.Events;

import com.nicolorancan.HttpPosterV2.Utils.ConfigManager;
import com.nicolorancan.HttpPosterV2.Utils.PostData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.clip.placeholderapi.PlaceholderAPI;

import java.util.List;
import java.util.Vector;

public class PlayerListener implements Listener {

    private ConfigManager config;
    private PostData poster;

    public PlayerListener(ConfigManager config, PostData poster) {
        this.config = config;
        this.poster = poster;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer() instanceof Player && this.config.isEnabled("events.player.join-leave.enabled")) {

            String serverName = this.config.getOptionUrl("server-name");

            Player player = event.getPlayer();

            String playerName = player.getName();
            String playerUuid = String.valueOf(player.getUniqueId());

            String playerPlaytime = "%statistic_seconds_played%";
            playerPlaytime = PlaceholderAPI.setPlaceholders(player, playerPlaytime);

            String eventType = "join";

            Location locationVector = player.getLocation();
            String playerLocation = locationVector.getBlockX() + "," + locationVector.getBlockY() + "," + locationVector.getBlockZ();

            String[] keys = {"server-name", "player-name", "player-uuid", "player-location", "player-playtime", "type"};
            String[] values = {serverName, playerName, playerUuid, playerLocation, playerPlaytime, eventType};

            this.poster.sendData(this.config.getOptionUrl("events.player.join-leave.endpoint"), keys, values, false);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (event.getPlayer() instanceof Player && this.config.isEnabled("events.player.join-leave.enabled")) {

            String serverName = this.config.getOptionUrl("server-name");

            Player player = event.getPlayer();

            String playerName = player.getName();
            String playerUuid = String.valueOf(player.getUniqueId());

            String playerPlaytime = "%statistic_seconds_played%";
            playerPlaytime = PlaceholderAPI.setPlaceholders(player, playerPlaytime);

            String eventType = "quit";

            Location locationVector = player.getLocation();
            String playerLocation = locationVector.getBlockX() + "," + locationVector.getBlockY() + "," + locationVector.getBlockZ();

            String[] keys = {"server-name", "player-name", "player-uuid", "player-location", "player-playtime", "type"};
            String[] values = {serverName, playerName, playerUuid, playerLocation, playerPlaytime, eventType};

            this.poster.sendData(this.config.getOptionUrl("events.player.join-leave.endpoint"), keys, values, false);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer() instanceof Player && this.config.isEnabled("events.player.block-broken.enabled")) {

            String serverName = this.config.getOptionUrl("server-name");

            Player player = event.getPlayer();

            String playerName = player.getName();
            String playerUuid = String.valueOf(player.getUniqueId());

            String playerPlaytime = "%statistic_seconds_played%";
            playerPlaytime = PlaceholderAPI.setPlaceholders(player, playerPlaytime);

            String blockName = String.valueOf(event.getBlock().getType());

            Location locationVector = event.getBlock().getLocation();
            String blockLocation = locationVector.getBlockX() + "," + locationVector.getBlockY() + "," + locationVector.getBlockZ();

            String[] keys = {"server-name", "player-name", "player-uuid", "player-playtime", "block-type", "block-position"};
            String[] values = {serverName, playerName, playerUuid, playerPlaytime, blockName, blockLocation};

            List<String> blacklist = this.config.getArray("events.player.block-broken.blacklist");
            boolean skip = false;

            for (int i = 0; i < blacklist.size() && !skip; i++)
                if (blacklist.get(i).equalsIgnoreCase(blockName))
                    skip = true;

            if (!skip)
                this.poster.sendData(this.config.getOptionUrl("events.player.block-broken.endpoint"), keys, values, false);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer() instanceof Player && this.config.isEnabled("events.player.block-placed.enabled")) {

            String serverName = this.config.getOptionUrl("server-name");

            Player player = event.getPlayer();

            String playerName = player.getName();
            String playerUuid = String.valueOf(player.getUniqueId());

            String playerPlaytime = "%statistic_seconds_played%";
            playerPlaytime = PlaceholderAPI.setPlaceholders(player, playerPlaytime);

            String blockType = String.valueOf(event.getBlock().getType());

            Location locationVector = event.getBlock().getLocation();
            String blockLocation = locationVector.getBlockX() + "," + locationVector.getBlockY() + "," + locationVector.getBlockZ();

            String[] keys = {"server-name", "player-name", "player-uuid", "player-playtime", "block-type", "block-position"};
            String[] values = {serverName, playerName, playerUuid, playerPlaytime, blockType, blockLocation};


            List<String> blacklist = this.config.getArray("events.player.block-placed.blacklist");
            boolean skip = false;

            for (int i = 0; i < blacklist.size() && !skip; i++)
                if (blacklist.get(i).equalsIgnoreCase(blockType))
                    skip = true;

            if (!skip)
                this.poster.sendData(this.config.getOptionUrl("events.player.block-placed.endpoint"), keys, values, false);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player && this.config.isEnabled("events.player.mob-killed.enabled")) {

            String serverName = this.config.getOptionUrl("server-name");

            Player player = event.getEntity().getKiller();
            Entity entity = event.getEntity();

            String playerName = player.getName();
            String playerUuid = String.valueOf(player.getUniqueId());

            String playerPlaytime = "%statistic_seconds_played%";
            playerPlaytime = PlaceholderAPI.setPlaceholders(player, playerPlaytime);

            String entityType = String.valueOf(entity.getType());

            Location locationVector = entity.getLocation();
            String entityLocation = locationVector.getBlockX() + "," + locationVector.getBlockY() + "," + locationVector.getBlockZ();

            String[] keys = {"server-name", "player-name", "player-uuid", "player-playtime", "entity-type", "entity-position"};
            String[] values = {serverName, playerName, playerUuid, playerPlaytime, entityType, entityLocation};


            List<String> blacklist = this.config.getArray("events.player.mob-killed.blacklist");
            boolean skip = false;

            for (int i = 0; i < blacklist.size() && !skip; i++)
                if (blacklist.get(i).equalsIgnoreCase(entityType))
                    skip = true;

            if (!skip)
                this.poster.sendData(this.config.getOptionUrl("events.player.mob-killed.endpoint"), keys, values, false);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getPlayer() instanceof Player && this.config.isEnabled("events.player.chatted.enabled")) {

            String serverName = this.config.getOptionUrl("server-name");

            Player player = event.getPlayer();

            String playerName = player.getName();
            String playerUuid = String.valueOf(player.getUniqueId());

            String playerPlaytime = "%statistic_seconds_played%";
            playerPlaytime = PlaceholderAPI.setPlaceholders(player, playerPlaytime);

            String messageContent = event.getMessage();

            Location locationVector = player.getLocation();
            String playerLocation = locationVector.getBlockX() + "," + locationVector.getBlockY() + "," + locationVector.getBlockZ();

            String[] keys = {"server-name", "player-name", "player-uuid", "player-playtime", "player-position", "message-content"};
            String[] values = {serverName, playerName, playerUuid, playerPlaytime, playerLocation, messageContent};

            this.poster.sendData(this.config.getOptionUrl("events.player.chatted.endpoint"), keys, values, false);
        }
    }
}
