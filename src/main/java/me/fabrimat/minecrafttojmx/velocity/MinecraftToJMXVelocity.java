package me.fabrimat.minecrafttojmx.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import me.fabrimat.minecrafttojmx.cache.CacheUpdater;
import me.fabrimat.minecrafttojmx.velocity.jmx.BeamServerVelocityManager;
import me.fabrimat.minecrafttojmx.velocity.jmx.velocity.VelocityAdapter;

import javax.management.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


@Plugin(id = "minecrafttojmx", name = "Minecraft To JMX", version = "0.6-SNAPSHOT", authors = {"Fabrimat"})
public class MinecraftToJMXVelocity implements CacheUpdater {
    
    private static ProxyServer server;
    private static Logger logger;
    
    @Inject
    public MinecraftToJMXVelocity(ProxyServer server, Logger logger) {
        MinecraftToJMXVelocity.server = server;
        MinecraftToJMXVelocity.logger = logger;
        
        logger.info("Enabled successfully!");
    }
    
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        getLogger().info("Plugin initialized");
        try {
            BeamServerVelocityManager.registerVelocity();
        } catch (MalformedObjectNameException | NotCompliantMBeanException | InstanceAlreadyExistsException | MBeanRegistrationException | InstanceNotFoundException e) {
            getLogger().log(Level.SEVERE, "Error while loading plugin", e);
        }
    
        server.getScheduler().buildTask(this, this::updateCacheSync).repeat(1, TimeUnit.MINUTES).schedule();
    }

    public void updateCacheSync() {
        VelocityAdapter.getInstance().updatePlayerCount();
    }

    @Override
    public void updateCacheAsync() {}
    
    public static ProxyServer getServer() {
        return server;
    }
    
    public static Logger getLogger() {
        return logger;
    }
}
