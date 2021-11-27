package me.fabrimat.minecrafttojmx.velocity.jmx.velocity;

import me.fabrimat.minecrafttojmx.velocity.MinecraftToJMXVelocity;

import java.util.concurrent.atomic.AtomicInteger;

public class VelocityAdapter {

    private static VelocityAdapter instance;

    private final AtomicInteger connectedPlayers;

    private VelocityAdapter() {
        connectedPlayers = new AtomicInteger(0);

        updateOnlinePlayers();
    }

    public static VelocityAdapter getInstance() {
        if(instance == null) {
            instance = new VelocityAdapter();
        }
        return instance;
    }

    public void updateOnlinePlayers() {
        connectedPlayers.set(MinecraftToJMXVelocity.getServer().getPlayerCount());
    }

    public int getConnectedPlayers() {
        return connectedPlayers.intValue();
    }
}
