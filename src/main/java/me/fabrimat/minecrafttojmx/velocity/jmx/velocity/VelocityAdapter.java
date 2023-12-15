package me.fabrimat.minecrafttojmx.velocity.jmx.velocity;

import me.fabrimat.minecrafttojmx.velocity.MinecraftToJMXVelocity;

import java.util.concurrent.atomic.AtomicInteger;

public class VelocityAdapter {

    private static VelocityAdapter instance;

    private final AtomicInteger playerCount;

    private VelocityAdapter() {
        playerCount = new AtomicInteger(0);

        updatePlayerCount();
    }

    public static VelocityAdapter getInstance() {
        if(instance == null) {
            instance = new VelocityAdapter();
        }
        return instance;
    }

    public void updatePlayerCount() {
        playerCount.set(MinecraftToJMXVelocity.getServer().getPlayerCount());
    }

    public int getPlayerCount() {
        return playerCount.get();
    }
}
