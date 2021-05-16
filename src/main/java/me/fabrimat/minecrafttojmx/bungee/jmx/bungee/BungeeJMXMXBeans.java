package me.fabrimat.minecrafttojmx.bungee.jmx.bungee;

import javax.management.DynamicMBean;

public interface BungeeJMXMXBeans extends DynamicMBean {
    int getConnectedPlayers();
}
