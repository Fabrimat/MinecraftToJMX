package me.fabrimat.sparktojmx.spigot;

import me.fabrimat.sparktojmx.cache.CacheUpdater;
import me.fabrimat.sparktojmx.spigot.jmx.BeamServerBukkitManager;
import me.fabrimat.sparktojmx.spigot.jmx.bukkit.BukkitAdapter;
import me.fabrimat.sparktojmx.spigot.jmx.spark.SparkAdapter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.management.*;
import java.util.logging.Level;

public class SparkToJMXSpigot extends JavaPlugin implements CacheUpdater {

    @Override
    public void onEnable() {
        super.onEnable();

        try {
            Class.forName("me.lucko.spark.api.Spark");
        } catch (ClassNotFoundException e) {
            getLogger().log(Level.SEVERE, "You are using a corrupted version of Spark. Please download it from the official website.");
            e.printStackTrace();
        }

        try {
            BeamServerBukkitManager.registerSpark();
            BeamServerBukkitManager.registerBukkit();
        } catch (MalformedObjectNameException | NotCompliantMBeanException | InstanceAlreadyExistsException | MBeanRegistrationException | InstanceNotFoundException e) {
            e.printStackTrace();
            super.onDisable();
        }

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, this::updateCacheAsync, 0L, 20L*60L);
        Bukkit.getScheduler().runTaskTimer(this, this::updateCacheSync, 0L, 20L*60L);
    }

    public void updateCacheAsync() {
        SparkAdapter.getInstance().updateTPS();
        SparkAdapter.getInstance().updateMSPT();
    }

    public void updateCacheSync() {
        BukkitAdapter.getInstance().updateOnlinePlayers();
        BukkitAdapter.getInstance().updateScheduler();
        BukkitAdapter.getInstance().updateWorld();
    }
}
