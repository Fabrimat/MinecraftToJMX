package me.fabrimat.minecrafttojmx.velocity.jmx.velocity;

import javax.management.DynamicMBean;

public interface VelocityJMXMXBeans extends DynamicMBean {
    int getPlayerCount();
}
