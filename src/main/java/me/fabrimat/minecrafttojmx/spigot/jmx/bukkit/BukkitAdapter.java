package me.fabrimat.minecrafttojmx.spigot.jmx.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.concurrent.atomic.AtomicInteger;

public class BukkitAdapter {

    private static BukkitAdapter instance;

    private final AtomicInteger onlinePlayers;
    private final AtomicInteger schedulerActiveWorkers;
    private final AtomicInteger schedulerPendingTasks;
    private final AtomicInteger worldChunkLoaded;
    private final AtomicInteger worldEntities;

    private BukkitAdapter() {
        onlinePlayers = new AtomicInteger(0);
        schedulerActiveWorkers = new AtomicInteger(0);
        schedulerPendingTasks = new AtomicInteger(0);
        worldChunkLoaded = new AtomicInteger(0);
        worldEntities = new AtomicInteger(0);

        updateOnlinePlayers();
        updateScheduler();
        updateWorld();
    }

    public static BukkitAdapter getInstance() {
        if(instance == null) {
            instance = new BukkitAdapter();
        }
        return instance;
    }

    public void updateOnlinePlayers() {
        onlinePlayers.set(Bukkit.getServer().getOnlinePlayers().size());
    }

    public void updateScheduler() {
        schedulerActiveWorkers.set(Bukkit.getScheduler().getActiveWorkers().size());
        schedulerPendingTasks.set(Bukkit.getScheduler().getPendingTasks().size());
    }

    public void updateWorld() {
        int chunks = 0;
        int entities = 0;
        for(World world : Bukkit.getWorlds()) {
            chunks += world.getLoadedChunks().length;
            entities += world.getEntities().size();
        }
        worldChunkLoaded.set(chunks);
        worldEntities.set(entities);
    }

    public int getOnlinePlayers() {
        return onlinePlayers.intValue();
    }

    public int getSchedulerActiveWorkers() {
        return schedulerActiveWorkers.intValue();
    }

    public int getSchedulerPendingTasks() {
        return schedulerPendingTasks.intValue();
    }

    public int getWorldChunkLoaded() {
        return worldChunkLoaded.intValue();
    }

    public int getWorldEntities() {
        return worldEntities.intValue();
    }
}
