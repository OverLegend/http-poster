package com.nicolorancan.HttpPosterV2.Commands.Subs;

import com.nicolorancan.HttpPosterV2.Commands.SubCommand;
import com.nicolorancan.HttpPosterV2.Utils.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload extends SubCommand {

    private ConfigManager configManager;

    public Reload(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "reloads the plugin config file";
    }

    @Override
    public String getSyntax() {
        return "/http reload";
    }

    @Override
    public void performForPlayer(Player player, String[] args) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Reloading..."));
        this.configManager.reload();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReload successful!"));
    }

    @Override
    public void performForConsole(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Reloading..."));
        this.configManager.reload();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReload successful!"));
    }
}
