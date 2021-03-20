package com.nicolorancan.Main;

import com.nicolorancan.Main.Commands.HttpPost;
import com.nicolorancan.Main.Listeners.PlayerListener;
import com.nicolorancan.Main.Listeners.ServerListener;
import com.nicolorancan.Main.Utils.ConfigManager;
import com.nicolorancan.Main.Utils.PostRequester;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private ConfigManager config;
    private PostRequester poster;
    private ServerListener serverListener;

    public void onEnable() {
        this.poster = new PostRequester();
        this.config = new ConfigManager(this.getConfig());
        this.saveConfig();
        this.serverListener = new ServerListener(this.getPoster(), this.getConfigFile());
        serverListener.triggerStart();

        this.getCommand("httppost").setExecutor(new HttpPost());
        getServer().getPluginManager().registerEvents(new PlayerListener(this.getPoster(), this.getConfigFile()), this);

    }

    public void onDisable() {
        serverListener.triggerStop();
    }
    public PostRequester getPoster() {return this.poster;}
    public ConfigManager getConfigFile() {return this.config;}
}
