package com.nicolorancan.main.commands.subcommands;

import com.nicolorancan.main.commands.SubCommand;
import com.nicolorancan.main.utils.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class EventsList implements SubCommand {

    private final ConfigManager config;

    public EventsList(ConfigManager configManager) {
        this.config = configManager;
    }

    public String getName() {
        return "events";
    }

    public String getDescription() {
        return "Return the list of all the available events.";
    }

    public String getSyntax() {
        return "/httpposter events";
    }

    public void performForPlayer(Player player, String[] args) {
        Map<String, String> eve = new HashMap<>();
        eve.put("Server start", "config.events.server.start.enable");
        eve.put("Server stop", "config.events.server.stop.enable");
        eve.put("Player join", "config.events.player.join.enable");
        eve.put("Player leave", "config.events.player.leave.enable");
        eve.put("Player killed", "config.events.player.killed.enable");
        eve.put("Player advancement", "config.events.player.advancement-completed.enable");

        player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Available events:");
        for (Entry<String, String> i : eve.entrySet()) {
            String key = i.getKey();
            String value = i.getValue();
            player.sendMessage(ChatColor.RESET + "" + ChatColor.AQUA + "" + key +
                    ChatColor.RESET + " - " + (config.isEnabled(value) ? "Enabled" : "Disabled"));
        }
    }

    public void performForConsole(CommandSender console, String[] args) {
        Map<String, String> eve = new HashMap<>();
        eve.put("Server start", "config.events.server.start.enable");
        eve.put("Server stop", "config.events.server.stop.enable");
        eve.put("Player join", "config.events.player.join.enable");
        eve.put("Player leave", "config.events.player.leave.enable");
        eve.put("Player killed", "config.events.player.killed.enable");
        eve.put("Player advancement", "config.events.player.advancement-completed.enable");

        console.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Available events:");
        for (Entry<String, String> i : eve.entrySet()) {
            String key = i.getKey();
            String value = i.getValue();
            console.sendMessage(ChatColor.RESET + "" + ChatColor.AQUA + "" + key +
                    ChatColor.RESET + " - " + (config.isEnabled(value) ? "Enabled" : "Disabled"));
        }
    }
}