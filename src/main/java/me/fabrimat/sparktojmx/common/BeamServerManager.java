package me.fabrimat.sparktojmx.common;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class BeamServerManager {

    public static void registerSpark() throws MalformedObjectNameException, NotCompliantMBeanException, MBeanRegistrationException, InstanceAlreadyExistsException, InstanceNotFoundException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName mxbeanName = new ObjectName("me.fabrimat.sparktojmx:type=SparkJMX");

        SparkJMX sparkJMX = new SparkJMX();
        if(mbs.isRegistered(mxbeanName)) {
            mbs.unregisterMBean(mxbeanName);
        }
        mbs.registerMBean(sparkJMX, mxbeanName);
    }
}
