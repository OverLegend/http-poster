package com.nicolorancan.Main.Commands.SubCommands;

import com.nicolorancan.Main.Commands.SubCommand;
import com.nicolorancan.Main.Utils.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class EventsList extends SubCommand {

    private ConfigManager config;

    public EventsList(ConfigManager configManager) {
        this.config = configManager;
    }

    @Override
    public String getName() {
        return "events";
    }

    @Override
    public String getDescription() {
        return "Return the list of all the available events.";
    }

    @Override
    public String getSyntax() {
        return "/httpposter events";
    }

    @Override
    public void performForPlayer(Player player, String[] args) {
        Map<String, String> eve = new HashMap<String, String>();
        eve.put("Server start", "config.events.server.start.enable");
        eve.put("Server stop", "config.events.server.stop.enable");
        eve.put("Player join", "config.events.player.join.enable");
        eve.put("Player leave", "config.events.player.leave.enable");
        eve.put("Player killed", "config.events.player.killed.enable");
        eve.put("Player advancement", "config.events.player.advancement-completed.enable");

        player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Available events:");
        for (String i : eve.keySet()) {
            player.sendMessage( ChatColor.RESET + "" + ChatColor.AQUA + "" + i + ChatColor.RESET + " - " + (config.isEnabled(eve.get(i)) ? "Enabled" : "Disabled"));
        }
    }

    @Override
    public void performForConsole(CommandSender console, String[] args) {
        Map<String, String> eve = new HashMap<String, String>();
        eve.put("Server start", "config.events.server.start.enable");
        eve.put("Server stop", "config.events.server.stop.enable");
        eve.put("Player join", "config.events.player.join.enable");
        eve.put("Player leave", "config.events.player.leave.enable");
        eve.put("Player killed", "config.events.player.killed.enable");
        eve.put("Player advancement", "config.events.player.advancement-completed.enable");

        console.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Available events:");
        for (String i : eve.keySet()) {
            console.sendMessage( ChatColor.RESET + "" + ChatColor.AQUA + "" + i + ChatColor.RESET + " - " + (config.isEnabled(eve.get(i)) ? "Enabled" : "Disabled"));
        }
    }
}