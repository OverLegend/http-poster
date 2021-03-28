package com.nicolorancan.Main.Listeners;

import com.nicolorancan.Main.Utils.ConfigManager;
import com.nicolorancan.Main.Utils.PostRequester;

public class ServerListener {

    private PostRequester poster;
    private ConfigManager config;

    public ServerListener(PostRequester postRequester, ConfigManager configManager) {
        this.poster = postRequester;
        this.config = configManager;
    }

    public void triggerStart() {
        if (config.isEnabled("config.events.server.start.enable")) {

            String url = config.getOptionUrl("config.events.server.stop.endpoint");

            String[] keys = {"serverName", "running"};
            String[] values = {config.getServerName(), "true"};

            poster.SendRequest(url, keys, values);
        }
    }

    public void triggerStop() {
        if (config.isEnabled("config.events.server.stop.enable")) {

            String url = config.getOptionUrl("config.events.server.stop.endpoint");

            String[] keys = {"serverName", "running"};
            String[] values = {config.getServerName(), "false"};

            poster.SendRequest(url, keys, values);
        }
    }
}
