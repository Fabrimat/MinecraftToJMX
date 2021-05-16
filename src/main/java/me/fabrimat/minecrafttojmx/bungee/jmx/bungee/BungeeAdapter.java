package me.fabrimat.minecrafttojmx.bungee.jmx.bungee;

import net.md_5.bungee.api.ProxyServer;

import java.util.concurrent.atomic.AtomicInteger;

public class BungeeAdapter {

    private static BungeeAdapter instance;

    private final AtomicInteger connectedPlayers;

    private BungeeAdapter() {
        connectedPlayers = new AtomicInteger(0);

        updateOnlinePlayers();
    }

    public static BungeeAdapter getInstance() {
        if(instance == null) {
            instance = new BungeeAdapter();
        }
        return instance;
    }

    public void updateOnlinePlayers() {
        connectedPlayers.set(ProxyServer.getInstance().getOnlineCount());
    }

    public int getConnectedPlayers() {
        return connectedPlayers.intValue();
    }
}
