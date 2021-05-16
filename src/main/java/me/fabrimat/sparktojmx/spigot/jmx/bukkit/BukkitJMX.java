package me.fabrimat.sparktojmx.spigot.jmx.bukkit;

import javax.management.*;
import java.lang.reflect.Constructor;

public class BukkitJMX implements BukkitJMXMXBeans {
    // internal variables describing the MBean
    private final String dClassName = this.getClass().getName();

    // internal variables for describing MBean elements
    private final MBeanAttributeInfo[] dAttributes = new MBeanAttributeInfo[5];
    private final MBeanConstructorInfo[] dConstructors = new MBeanConstructorInfo[1];
    private final MBeanOperationInfo[] dOperations = new MBeanOperationInfo[0];
    private MBeanInfo dMBeanInfo = null;

    private final BukkitAdapter bukkitAdapter;

    public BukkitJMX() {
        buildDynamicMBeanInfo();
        bukkitAdapter = BukkitAdapter.getInstance();
    }

    @Override
    public Object getAttribute(String s) throws AttributeNotFoundException, MBeanException, ReflectionException {
        switch(s) {
            case "OnlinePlayers":
                return this.getOnlinePlayer();
            case "SchedulerActiveWorkers":
                return this.getSchedulerActiveWorkers();
            case "SchedulerPendingTasks":
                return this.getSchedulerPendingTasks();
            case "WorldChunkLoaded":
                return this.getWorldChunkLoaded();
            case "WorldEntities":
                return this.getWorldEntities();
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

        Descriptor descriptor = new ImmutableDescriptor("interfaceClassName=me.fabrimat.sparktojmx.spigot.jmx.bukkit.BukkitJMXMXBeans", "mxbean=true");

        dAttributes[0] = new MBeanAttributeInfo(
                "OnlinePlayers",                 // name
                "int",      // type
                "OnlinePlayers",  // description
                true,                    // readable
                false,                   // writable
                false,
                descriptor);
        dAttributes[1] = new MBeanAttributeInfo(
                "SchedulerActiveWorkers",                 // name
                "int",      // type
                "SchedulerActiveWorkers",  // description
                true,                    // readable
                false,                   // writable
                false,
                descriptor);
        dAttributes[2] = new MBeanAttributeInfo(
                "SchedulerPendingTasks",                 // name
                "int",      // type
                "SchedulerPendingTasks",  // description
                true,                    // readable
                false,                   // writable
                false,
                descriptor);
        dAttributes[3] = new MBeanAttributeInfo(
                "WorldChunkLoaded",                 // name
                "int",      // type
                "WorldChunkLoaded",  // description
                true,                    // readable
                false,                   // writable
                false,
                descriptor);
        dAttributes[4] = new MBeanAttributeInfo(
                "WorldEntities",                 // name
                "int",      // type
                "WorldEntities",  // description
                true,                    // readable
                false,                   // writable
                false,
                descriptor);

        // use reflection to get constructor signatures
        Constructor[] constructors = this.getClass().getConstructors();
        dConstructors[0] = new MBeanConstructorInfo(
                "BukkitJMX(): No-parameter constructor",  //description
                constructors[0]);                  // the contructor object

        String dDescription = "BukkitJMX";
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
    public int getOnlinePlayer() {
        return bukkitAdapter.getOnlinePlayers();
    }

    @Override
    public int getSchedulerActiveWorkers() {
        return bukkitAdapter.getSchedulerActiveWorkers();
    }

    @Override
    public int getSchedulerPendingTasks() {
        return bukkitAdapter.getSchedulerPendingTasks();
    }

    @Override
    public int getWorldChunkLoaded() {
        return bukkitAdapter.getWorldChunkLoaded();
    }

    @Override
    public int getWorldEntities() {
        return bukkitAdapter.getWorldEntities();
    }
}
