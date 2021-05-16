package me.fabrimat.sparktojmx.spigot.jmx;

import me.fabrimat.sparktojmx.spigot.jmx.bukkit.BukkitJMX;
import me.fabrimat.sparktojmx.spigot.jmx.spark.SparkJMX;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class BeamServerBukkitManager {

    public static void registerSpark() throws MalformedObjectNameException, NotCompliantMBeanException, MBeanRegistrationException, InstanceAlreadyExistsException, InstanceNotFoundException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName mxbeanName = new ObjectName("me.fabrimat.sparktojmx:type=SparkJMX");

        SparkJMX sparkJMX = new SparkJMX();
        if(mbs.isRegistered(mxbeanName)) {
            mbs.unregisterMBean(mxbeanName);
        }
        mbs.registerMBean(sparkJMX, mxbeanName);
    }

    public static void registerBukkit() throws MalformedObjectNameException, NotCompliantMBeanException, MBeanRegistrationException, InstanceAlreadyExistsException, InstanceNotFoundException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName mxbeanName = new ObjectName("me.fabrimat.sparktojmx:type=BukkitJMX");

        BukkitJMX bukkitJMX = new BukkitJMX();
        if(mbs.isRegistered(mxbeanName)) {
            mbs.unregisterMBean(mxbeanName);
        }
        mbs.registerMBean(bukkitJMX, mxbeanName);
    }
}
