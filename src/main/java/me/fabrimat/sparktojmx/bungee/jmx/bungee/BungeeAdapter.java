package me.fabrimat.sparktojmx.bungee.jmx.bungee;

import org.bukkit.Bukkit;
import org.bukkit.World;

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
        connectedPlayers.set(Bukkit.getServer().getOnlinePlayers().size());
    }

    public int getConnectedPlayers() {
        return connectedPlayers.intValue();
    }
}
