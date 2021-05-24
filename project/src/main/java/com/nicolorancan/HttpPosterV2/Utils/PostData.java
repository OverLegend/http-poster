package com.nicolorancan.HttpPosterV2.Utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class PostData {

    private ConfigManager config;
    private JavaPlugin plugin;

    public PostData(ConfigManager config, JavaPlugin plugin) {

        this.config = config;
        this.plugin = plugin;
    }

    public void sendData(String url, String stringData, boolean onShutdown) {
        if (stringData.length() > 0) {
            if (!onShutdown) {
                Bukkit.getScheduler().runTaskAsynchronously(this.plugin, new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL purl = new URL(url);

                            HttpURLConnection httpURLConnection = (HttpURLConnection) purl.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setRequestProperty("Content-Type", "application/json");

                            byte[] raw = stringData.getBytes(StandardCharsets.UTF_8);

                            OutputStream stream = httpURLConnection.getOutputStream();
                            stream.write(raw);

                            int code = httpURLConnection.getResponseCode();

                            if (code == 200) {
                                httpURLConnection.getResponseMessage();
                                httpURLConnection.disconnect();
                            } else {
                                plugin.getLogger().warning(url + " unreachable.");
                            }

                        } catch (IOException e) {
                            plugin.getLogger().warning(url + " unreachable.");
                        }
                    }
                });
            } else {
                try {
                    URL purl = new URL(url);

                    HttpURLConnection httpURLConnection = (HttpURLConnection) purl.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");

                    byte[] raw = stringData.getBytes(StandardCharsets.UTF_8);

                    OutputStream stream = httpURLConnection.getOutputStream();
                    stream.write(raw);

                    int code = httpURLConnection.getResponseCode();

                    if (code == 200) {
                        httpURLConnection.getResponseMessage();
                        httpURLConnection.disconnect();
                    } else {
                        this.plugin.getLogger().warning(url + " unreachable.");
                    }

                } catch (IOException e) {
                    this.plugin.getLogger().warning(url + " unreachable.");
                }
            }
        } else {
            this.plugin.getLogger().warning("Internal error. Check if this is the latest version available, otherwise report it.");
        }
    }

    public void sendData(String url, String[] Keys, String[] Values, boolean onShutdown) {

        if (Keys.length == Values.length) {

            if (!onShutdown) {
                Bukkit.getScheduler().runTaskAsynchronously(this.plugin, new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL purl = new URL(url);

                            HttpURLConnection httpURLConnection = (HttpURLConnection) purl.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setRequestProperty("Content-Type", "application/json");

                            StringBuilder data = new StringBuilder();

                            for (int i = 0; i < Keys.length; i++) {
                                if (i == 0)
                                    data.append("{");

                                data.append("\"").append(Keys[i]).append("\": \"").append(Values[i]).append("\"");

                                if (i != Values.length - 1)
                                    data.append(",");
                                else
                                    data.append("}");
                            }

                            String dataStringed = data.toString();

                            byte[] raw = dataStringed.getBytes(StandardCharsets.UTF_8);

                            OutputStream stream = httpURLConnection.getOutputStream();
                            stream.write(raw);

                            int code = httpURLConnection.getResponseCode();

                            if (code == 200) {
                                httpURLConnection.getResponseMessage();
                                httpURLConnection.disconnect();
                            } else {
                                plugin.getLogger().warning(url + " unreachable.");
                            }

                        } catch (IOException e) {
                            plugin.getLogger().warning(url + " unreachable.");
                        }
                    }
                });
            } else {
                try {
                    URL purl = new URL(url);

                    HttpURLConnection httpURLConnection = (HttpURLConnection) purl.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");

                    StringBuilder data = new StringBuilder();

                    for (int i = 0; i < Keys.length; i++) {
                        if (i == 0)
                            data.append("{");

                        data.append("\"").append(Keys[i]).append("\": \"").append(Values[i]).append("\"");

                        if (i != Values.length - 1)
                            data.append(",");
                        else
                            data.append("}");
                    }

                    String dataStringed = data.toString();

                    byte[] raw = dataStringed.getBytes(StandardCharsets.UTF_8);

                    OutputStream stream = httpURLConnection.getOutputStream();
                    stream.write(raw);

                    int code = httpURLConnection.getResponseCode();

                    if (code == 200) {
                        httpURLConnection.getResponseMessage();
                        httpURLConnection.disconnect();
                    } else {
                        this.plugin.getLogger().warning(url + " unreachable.");
                    }

                } catch (IOException e) {
                    this.plugin.getLogger().warning(url + " unreachable.");
                }
            }
        } else {
            this.plugin.getLogger().warning("Internal error. Check if this is the latest version available, otherwise report it.");
        }
    }
}
