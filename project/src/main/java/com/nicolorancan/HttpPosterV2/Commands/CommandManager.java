package com.nicolorancan.HttpPosterV2.Commands;

import com.nicolorancan.HttpPosterV2.Commands.Subs.Reload;
import com.nicolorancan.HttpPosterV2.Main;
import com.nicolorancan.HttpPosterV2.Utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private Main plugin;
    private ConfigManager configManager;
    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager(Main plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;

        this.subCommands.add(new Reload(configManager));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (args.length > 0) {

            boolean founded = false;
            for (int i = 0; i < this.subCommands.size() && !founded; i++) {
                if (args[0].equalsIgnoreCase(this.subCommands.get(i).getName())) {
                    founded = true;

                    if (sender instanceof Player) {
                        if (sender.hasPermission("http." + this.subCommands.get(i).getName()))
                            this.subCommands.get(i).performForPlayer((Player) sender, args);
                        else
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', configManager.getOptionUrl("messages.no-permission")) );
                    }
                    else
                        if (sender.hasPermission("http." + this.subCommands.get(i).getName()))
                            this.subCommands.get(i).performForConsole(sender, args);
                        else
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', configManager.getOptionUrl("messages.no-permission")) );
                }
            }

            if (!founded && args[0].equalsIgnoreCase("help")) {
                founded = true;

                if (sender.hasPermission("http.help")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3HttpPosterV2 commands:"));

                    for (int i = 0; i < this.subCommands.size(); i++)
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + this.subCommands.get(i).getSyntax() + "&f: " + this.subCommands.get(i).getDescription()));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', configManager.getOptionUrl("messages.no-permission")) );
                }

            }

            if (!founded) {
                if (sender.hasPermission("http.notfound")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', configManager.getOptionUrl("messages.not-found")));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', configManager.getOptionUrl("messages.no-permission")) );
                }
            }
        } else {
            if (sender.hasPermission("http.about")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Coded by: &bAxereos"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fVersion: " + configManager.getOptionUrl("version")));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fUse /http help for a list of the available commands."));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', configManager.getOptionUrl("messages.no-permission")));
            }
        }

        return true;
    }
}
