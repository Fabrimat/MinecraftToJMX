package me.fabrimat.minecrafttojmx.velocity.jmx;

import me.fabrimat.minecrafttojmx.velocity.jmx.velocity.VelocityJMX;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class BeamServerVelocityManager {

    public static void registerVelocity() throws MalformedObjectNameException, NotCompliantMBeanException, MBeanRegistrationException, InstanceAlreadyExistsException, InstanceNotFoundException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName mxbeanName = new ObjectName("me.fabrimat.minecrafttojmx:type=VelocityJMX");

        VelocityJMX velocityJMX = new VelocityJMX();
        if(mbs.isRegistered(mxbeanName)) {
            mbs.unregisterMBean(mxbeanName);
        }
        mbs.registerMBean(velocityJMX, mxbeanName);
    }
}
