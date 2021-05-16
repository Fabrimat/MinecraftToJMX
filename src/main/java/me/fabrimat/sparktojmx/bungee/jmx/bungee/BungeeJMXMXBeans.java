package me.fabrimat.sparktojmx.bungee.jmx.bungee;

import javax.management.DynamicMBean;

public interface BungeeJMXMXBeans extends DynamicMBean {
    int getConnectedPlayers();
}
