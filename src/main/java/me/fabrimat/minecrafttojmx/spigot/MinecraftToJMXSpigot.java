package me.fabrimat.minecrafttojmx.spigot;

import me.fabrimat.minecrafttojmx.cache.CacheUpdater;
import me.fabrimat.minecrafttojmx.spigot.jmx.BeamServerBukkitManager;
import me.fabrimat.minecrafttojmx.spigot.jmx.bukkit.BukkitAdapter;
import me.fabrimat.minecrafttojmx.spigot.jmx.spark.SparkAdapter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.management.*;
import java.util.logging.Level;

public class MinecraftToJMXSpigot extends JavaPlugin implements CacheUpdater {

    private boolean spark = false;

    @Override
    public void onEnable() {
        super.onEnable();

        Plugin sparkPlugin = Bukkit.getPluginManager().getPlugin("spark");
        if(sparkPlugin != null && sparkPlugin.isEnabled()) {
            spark = true;

            try {
                Class.forName("me.lucko.spark.api.Spark");
            } catch (ClassNotFoundException e) {
                getLogger().log(Level.SEVERE, "You are using a corrupted version of Spark. Please download it from the official website.");
                e.printStackTrace();
            }
        }

        try {
            if(isSpark()) {
                BeamServerBukkitManager.registerSpark();
            }
            BeamServerBukkitManager.registerBukkit();
        } catch (MalformedObjectNameException | NotCompliantMBeanException | InstanceAlreadyExistsException | MBeanRegistrationException | InstanceNotFoundException e) {
            e.printStackTrace();
            super.onDisable();
        }

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, this::updateCacheAsync, 0L, 20L*60L);
        Bukkit.getScheduler().runTaskTimer(this, this::updateCacheSync, 0L, 20L*60L);
    }

    public void updateCacheAsync() {
        if(isSpark()) {
            SparkAdapter.getInstance().updateTPS();
            SparkAdapter.getInstance().updateMSPT();
        }
    }

    public void updateCacheSync() {
        BukkitAdapter.getInstance().updateOnlinePlayers();
        BukkitAdapter.getInstance().updateScheduler();
        BukkitAdapter.getInstance().updateWorld();
    }

    public boolean isSpark() {
        return spark;
    }
}
