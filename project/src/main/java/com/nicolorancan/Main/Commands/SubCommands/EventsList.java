package com.nicolorancan.Main.Commands.SubCommands;

import com.nicolorancan.Main.Commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class EventsList extends SubCommand {
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
    public void perform(Player player, String[] args) {
        String[] events = {"Server start", "Server stop", "Player join", "Player leave", "Player get killed", "Player completed advancement"};

        player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Available events:");
        for (int i = 0; i < events.length; i++) {
            player.sendMessage(ChatColor.RESET + events[i]);
        }
    }
}