package me.fabrimat.sparktojmx.spigot.jmx.bukkit;

import javax.management.DynamicMBean;

public interface BukkitJMXMXBeans extends DynamicMBean {
    int getOnlinePlayer();
    int getSchedulerActiveWorkers();
    int getSchedulerPendingTasks();
    int getWorldChunkLoaded();
    int getWorldEntities();
}
