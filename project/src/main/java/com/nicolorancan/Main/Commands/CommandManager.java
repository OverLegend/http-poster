package com.nicolorancan.Main.Commands;

import com.nicolorancan.Main.Commands.SubCommands.EventsList;
import com.nicolorancan.Main.Main;
import com.nicolorancan.Main.Utils.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();
    private ConfigManager config;
    private Main plugin;

    public CommandManager(ConfigManager configManager, Main plugin) {
        subCommands.add(new EventsList(configManager));
        this.config = configManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 0) {
            boolean founded = false;
            for (int i = 0; i < subCommands.size() && !founded; i++) {
                if (args[0].equalsIgnoreCase(subCommands.get(i).getName())) {

                    founded = true;

                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        subCommands.get(i).performForPlayer(player, args);
                    } else
                        subCommands.get(i).performForConsole(sender, args);
                }
            }

            if (args[0].equalsIgnoreCase("reload")) {
                founded = true;
                plugin.reloadCnf();
                sender.sendMessage("Plugin reloaded.");
            }

            if (args[0].equalsIgnoreCase("help")) {
                founded = true;

                sender.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "HttpPoster command list:" + ChatColor.RESET + "");
                subCommands.forEach(subCommand -> {
                    sender.sendMessage(ChatColor.AQUA + "" + subCommand.getSyntax() + ChatColor.RESET + " - " + subCommand.getDescription());
                });

            }

            if (!founded) {
                sender.sendMessage("Command not found.");
                sender.sendMessage("Use " + ChatColor.AQUA + "/httpposter help " + ChatColor.RESET + "for a list of all available commands.");
            }
        } else {
            sender.sendMessage("You need to specify a sub-command.");
            sender.sendMessage("Use " + ChatColor.AQUA + "/httpposter help " + ChatColor.RESET + "for a list of all available commands.");
        }
        return true;
    }
}