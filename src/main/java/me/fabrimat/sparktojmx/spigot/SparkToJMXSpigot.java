package me.fabrimat.sparktojmx.spigot;

import me.fabrimat.sparktojmx.common.BeamServerManager;
import me.fabrimat.sparktojmx.common.SparkAdapter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.management.*;

public class SparkToJMXSpigot extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();

        try {
            BeamServerManager.registerSpark();
        } catch (MalformedObjectNameException | NotCompliantMBeanException | InstanceAlreadyExistsException | MBeanRegistrationException | InstanceNotFoundException e) {
            e.printStackTrace();
            super.onDisable();
        }

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, this::updateCache, 0L, 20L*60L);
    }

    private void updateCache() {
        SparkAdapter.getInstance().updateTPS();
        SparkAdapter.getInstance().updateMSPT();
    }
}
