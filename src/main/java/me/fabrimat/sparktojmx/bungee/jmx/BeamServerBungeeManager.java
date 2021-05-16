package me.fabrimat.sparktojmx.bungee.jmx;

import me.fabrimat.sparktojmx.bungee.jmx.bungee.BungeeJMX;
import me.fabrimat.sparktojmx.spigot.jmx.bukkit.BukkitJMX;
import me.fabrimat.sparktojmx.spigot.jmx.spark.SparkJMX;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class BeamServerBungeeManager {

    public static void registerBungee() throws MalformedObjectNameException, NotCompliantMBeanException, MBeanRegistrationException, InstanceAlreadyExistsException, InstanceNotFoundException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName mxbeanName = new ObjectName("me.fabrimat.sparktojmx:type=BukkitJMX");

        BungeeJMX bungeeJMX = new BungeeJMX();
        if(mbs.isRegistered(mxbeanName)) {
            mbs.unregisterMBean(mxbeanName);
        }
        mbs.registerMBean(bungeeJMX, mxbeanName);
    }
}
