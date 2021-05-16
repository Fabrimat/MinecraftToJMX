package me.fabrimat.sparktojmx.cache;

public interface CacheUpdater {
    void updateCacheAsync();
    void updateCacheSync();
}
