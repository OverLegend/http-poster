package com.nicolorancan.Main.Listeners;

import com.nicolorancan.Main.Utils.ConfigManager;
import com.nicolorancan.Main.Utils.PostRequester;
import org.bukkit.Statistic;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    private final PostRequester poster;
    private final ConfigManager config;

    public PlayerListener(PostRequester postRequester, ConfigManager configManager) {
        this.poster = postRequester;
        this.config = configManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (config.isEnabled("config.events.player.join.enable")) {

            String serverName = config.getServerName();

            Player executor = event.getPlayer();

            String executorName = executor.getName();
            String executorUuid = String.valueOf(executor.getUniqueId());
            String executorIsOnline = "true";

            String url = config.getOptionUrl("config.events.player.join.endpoint");

            String[] keys = {"serverName", "playerName", "uuid", "isOnline"};
            String[] values = {serverName, executorName, executorUuid, executorIsOnline};

            poster.SendRequest(url, keys, values);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (config.isEnabled("config.events.player.leave.enable")) {

            String serverName = config.getServerName();

            Player executor = event.getPlayer();

            String executorName = executor.getName();
            String executorUuid = String.valueOf(executor.getUniqueId());
            String executorIsOnline = "false";

            String url = config.getOptionUrl("config.events.player.leave.endpoint");

            String[] keys = {"serverName", "playerName", "uuid", "isOnline"};
            String[] values = {serverName, executorName, executorUuid, executorIsOnline};

            poster.SendRequest(url, keys, values);
        }
    }

    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
        if (config.isEnabled("config.events.player.advancement-completed.enable")) {

            String serverName = config.getServerName();

            Player executor = event.getPlayer();

            String executorName = executor.getName();
            String executorUuid = String.valueOf(executor.getUniqueId());

            Advancement advancement = event.getAdvancement();
            String advancementName = advancement.getKey().getKey();

            String url = config.getOptionUrl("config.events.player.advancement-completed.endpoint");

            String[] keys = {"serverName", "playerName", "uuid", "advancementName"};
            String[] values = {serverName, executorName, executorUuid, advancementName};

            poster.SendRequest(url, keys, values);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (config.isEnabled("config.events.player.die.enable")) {
            if (event.getEntity().getKiller() instanceof Player) {

                String serverName = config.getServerName();

                Player v = event.getEntity();
                Player k = event.getEntity().getKiller();

                String killerName = k.getName();
                String killerUuid = k.getUniqueId().toString();
                String killerKillCount = String.valueOf(k.getStatistic(Statistic.PLAYER_KILLS));
                String killerDeathCount = String.valueOf(k.getStatistic(Statistic.DEATHS));

                String victimName = v.getName();
                String victimUuid = v.getUniqueId().toString();
                String victimKillCount = String.valueOf(v.getStatistic(Statistic.PLAYER_KILLS));
                String victimDeathCount = String.valueOf(v.getStatistic(Statistic.DEATHS));

                String url = config.getOptionUrl("config.events.player.die.endpoint");

                String[] keys = {"serverName", "victimName", "victimUuid", "victimKills", "victimDeaths", "killerName", "killerUuid", "killerKills", "killerDeaths"};
                String[] values = {serverName, victimName, victimUuid, victimKillCount, victimDeathCount, killerName, killerUuid, killerKillCount, killerDeathCount};

                poster.SendRequest(url, keys, values);
            }
        }
    }
}
