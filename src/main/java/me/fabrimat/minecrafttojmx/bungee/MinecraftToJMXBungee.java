package me.fabrimat.minecrafttojmx.bungee;

import me.fabrimat.minecrafttojmx.bungee.jmx.BeamServerBungeeManager;
import me.fabrimat.minecrafttojmx.bungee.jmx.bungee.BungeeAdapter;
import me.fabrimat.minecrafttojmx.cache.CacheUpdater;
import net.md_5.bungee.api.plugin.Plugin;

import javax.management.*;
import java.util.concurrent.TimeUnit;

public class MinecraftToJMXBungee extends Plugin implements CacheUpdater {

    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info("Enabled successfully!");

        try {
            BeamServerBungeeManager.registerBungee();
        } catch (MalformedObjectNameException | NotCompliantMBeanException | InstanceAlreadyExistsException | MBeanRegistrationException | InstanceNotFoundException e) {
            e.printStackTrace();
            super.onDisable();
        }

        getProxy().getScheduler().schedule(this, this::updateCacheSync, 0, 1, TimeUnit.MINUTES);
    }

    public void updateCacheSync() {
        BungeeAdapter.getInstance().updateOnlinePlayers();
    }

    @Override
    public void updateCacheAsync() {}
}
