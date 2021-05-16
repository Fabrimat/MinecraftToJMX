package me.fabrimat.minecrafttojmx.spigot.jmx.spark;

import javax.management.*;
import java.lang.reflect.Constructor;

public class SparkJMX implements SparkJMXMXBeans {
    // internal variables describing the MBean
    private final String dClassName = this.getClass().getName();

    // internal variables for describing MBean elements
    private final MBeanAttributeInfo[] dAttributes = new MBeanAttributeInfo[7];
    private final MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
    private final MBeanOperationInfo[] dOperations = new MBeanOperationInfo[0];
    private MBeanInfo dMBeanInfo = null;

    private final SparkAdapter sparkAdapter;

    public SparkJMX() {
        buildDynamicMBeanInfo();
        sparkAdapter = SparkAdapter.getInstance();
    }

    @Override
    public double getTpsLast5Secs() {
        return sparkAdapter.getTPS().getLast5Sec();
    }

    @Override
    public double getTpsLast10Secs() {
        return sparkAdapter.getTPS().getLast10Sec();
    }

    @Override
    public double getTpsLast1Min() {
        return sparkAdapter.getTPS().getLast1Min();
    }

    @Override
    public double getTpsLast5Min() {
        return sparkAdapter.getTPS().getLast5Min();
    }

    @Override
    public double getTpsLast15Min() {
        return sparkAdapter.getTPS().getLast15Min();
    }

    @Override
    public double getMSPTLast10Secs() {
        return sparkAdapter.getMSPT().getLast10Sec();
    }

    @Override
    public double getMSPTLast1Min() {
        return sparkAdapter.getMSPT().getLast1Min();
    }

    @Override
    public Object getAttribute(String s) throws AttributeNotFoundException, MBeanException, ReflectionException {
        switch(s) {
            case "TpsLast5Sec":
                return this.getTpsLast5Secs();
            case "TpsLast10Sec":
                return this.getTpsLast10Secs();
            case "TpsLast1Min":
                return this.getTpsLast1Min();
            case "TpsLast5Min":
                return this.getTpsLast5Min();
            case "TpsLast15Min":
                return this.getTpsLast15Min();
            case "MSPTLast10Sec":
                return this.getMSPTLast10Secs();
            case "MSPTLast1Min":
                return this.getMSPTLast1Min();
            default:
                throw new AttributeNotFoundException("Attribute not found " + s);
        }
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        throw new MBeanException(new Exception("Class is readonly"));
    }

    @Override
    public AttributeList getAttributes(String[] strings) {
        return null;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributeList) {
        return null;
    }

    @Override
    public Object invoke(String s, Object[] objects, String[] strings) throws MBeanException, ReflectionException {
        return null;
    }

    // internal method
    private void buildDynamicMBeanInfo() {

        Descriptor descriptor = new ImmutableDescriptor("interfaceClassName=me.fabrimat.minecrafttojmx.spigot.jmx.spark.SparkJMXMXBeans", "mxbean=true");

        dAttributes[0] = new MBeanAttributeInfo(
                "TpsLast5Sec",                 // name
                "double",      // type
                "TpsLast5Sec",  // description
                true,                    // readable
                false,                   // writable
                false,
                descriptor);
        dAttributes[1] = new MBeanAttributeInfo(
                "TpsLast10Sec",                 // name
                "double",      // type
                "TpsLast10Sec",  // description
                true,                    // readable
                false,                   // writable
                false,
                descriptor);
        dAttributes[2] = new MBeanAttributeInfo(
                "TpsLast1Min",                 // name
                "double",      // type
                "TpsLast1Min",  // description
                true,                    // readable
                false,                   // writable
                false,
                descriptor);
        dAttributes[3] = new MBeanAttributeInfo(
                "TpsLast5Min",                 // name
                "double",      // type
                "TpsLast5Min",  // description
                true,                    // readable
                false,                   // writable
                false,
                descriptor);
        dAttributes[4] = new MBeanAttributeInfo(
                "TpsLast15Min",                 // name
                "double",      // type
                "TpsLast15Min",  // description
                true,                    // readable
                false,                   // writable
                false,
                descriptor);
        dAttributes[5] = new MBeanAttributeInfo(
                "MSPTLast10Sec",                 // name
                "double",      // type
                "TpsLast10Sec",  // description
                true,                    // readable
                false,                   // writable
                false,
                descriptor);
        dAttributes[6] = new MBeanAttributeInfo(
                "MSPTLast1Min",                 // name
                "double",      // type
                "TpsLast1Min",  // description
                true,                    // readable
                false,                   // writable
                false,
                descriptor);

        // use reflection to get constructor signatures
        Constructor[] constructors = this.getClass().getConstructors();
        dConstructors[0] = new MBeanConstructorInfo(
                "SparkJMX(): No-parameter constructor",  //description
                constructors[0]);                  // the contructor object

        String dDescription = "SparkJMX";
        dMBeanInfo = new MBeanInfo(dClassName,
                dDescription,
                dAttributes,
                dConstructors,
                dOperations,
                new MBeanNotificationInfo[0]);
    }

    // exposed method implementing the DynamicMBean.getMBeanInfo interface
    public MBeanInfo getMBeanInfo() {

        // return the information we want to expose for management:
        // the dMBeanInfo private field has been built at instantiation time,
        return(dMBeanInfo);
    }
}
