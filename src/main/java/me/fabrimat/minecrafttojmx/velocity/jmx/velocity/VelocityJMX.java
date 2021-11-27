package me.fabrimat.minecrafttojmx.velocity.jmx.velocity;

import javax.management.*;
import java.lang.reflect.Constructor;

public class VelocityJMX implements VelocityJMXMXBeans {
    // internal variables describing the MBean
    private final String dClassName = this.getClass().getName();

    // internal variables for describing MBean elements
    private final MBeanAttributeInfo[] dAttributes = new MBeanAttributeInfo[1];
    private final MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
    private final MBeanOperationInfo[] dOperations = new MBeanOperationInfo[0];
    private MBeanInfo dMBeanInfo = null;

    private final VelocityAdapter bungeeAdapter;

    public VelocityJMX() {
        buildDynamicMBeanInfo();
        bungeeAdapter = VelocityAdapter.getInstance();
    }

    @Override
    public Object getAttribute(String s) throws AttributeNotFoundException, MBeanException, ReflectionException {
        switch(s) {
            case "ConnectedPlayers":
                return this.getConnectedPlayers();
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

        Descriptor descriptor = new ImmutableDescriptor("interfaceClassName=me.fabrimat.minecrafttojmx.velocity.jmx.velocity.VelocityJMXMXBeans", "mxbean=true");

        dAttributes[0] = new MBeanAttributeInfo(
                "ConnectedPlayers",                 // name
                "int",      // type
                "ConnectedPlayers",  // description
                true,                    // readable
                false,                   // writable
                false,
                descriptor);

        // use reflection to get constructor signatures
        Constructor[] constructors = this.getClass().getConstructors();
        dConstructors[0] = new MBeanConstructorInfo(
                "VelocityJMX(): No-parameter constructor",  //description
                constructors[0]);                  // the contructor object

        String dDescription = "VelocityJMX";
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

    @Override
    public int getConnectedPlayers() {
        return bungeeAdapter.getConnectedPlayers();
    }
}
