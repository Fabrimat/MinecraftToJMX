package me.fabrimat.minecrafttojmx.bungee.jmx;

import me.fabrimat.minecrafttojmx.bungee.jmx.bungee.BungeeJMX;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class BeamServerBungeeManager {

    public static void registerBungee() throws MalformedObjectNameException, NotCompliantMBeanException, MBeanRegistrationException, InstanceAlreadyExistsException, InstanceNotFoundException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName mxbeanName = new ObjectName("me.fabrimat.minecrafttojmx:type=BungeeJMX");

        BungeeJMX bungeeJMX = new BungeeJMX();
        if(mbs.isRegistered(mxbeanName)) {
            mbs.unregisterMBean(mxbeanName);
        }
        mbs.registerMBean(bungeeJMX, mxbeanName);
    }
}
