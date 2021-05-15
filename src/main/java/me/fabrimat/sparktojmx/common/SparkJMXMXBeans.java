package me.fabrimat.sparktojmx.common;

import javax.management.DynamicMBean;

public interface SparkJMXMXBeans extends DynamicMBean {
    double getTpsLast5Secs();
    double getTpsLast10Secs();
    double getTpsLast1Min();
    double getTpsLast5Min();
    double getTpsLast15Min();

    double getMSPTLast10Secs();
    double getMSPTLast1Min();

}
