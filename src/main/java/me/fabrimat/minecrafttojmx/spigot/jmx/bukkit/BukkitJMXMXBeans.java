package me.fabrimat.minecrafttojmx.spigot.jmx.bukkit;

import javax.management.DynamicMBean;

public interface BukkitJMXMXBeans extends DynamicMBean {
    int getOnlinePlayer();
    int getSchedulerActiveWorkers();
    int getSchedulerPendingTasks();
    int getWorldChunkLoaded();
    int getWorldEntities();
    int getTickNumber();
}
