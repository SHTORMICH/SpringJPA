package guava;

import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalNotification;
import com.google.common.cache.CacheLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class CacheService {
    private static final Logger LOGGER = LogManager.getLogger(CacheService.class.getName());
    private static final int MAX_SIZE = 100000;
    private static final long EXPIRE_TIME_SECONDS = 5;

    private transient LoadingCache<String, CacheEntry> cache;

    public CacheService() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(MAX_SIZE)
                .expireAfterAccess(EXPIRE_TIME_SECONDS, TimeUnit.SECONDS)
                .removalListener((RemovalNotification<String, CacheEntry> notification) -> {
                    LOGGER.debug("Entry removed: " + notification.getKey());
                })
                .build(new CacheLoader<String, CacheEntry>() {
                    @Override
                    public CacheEntry load(String key) {
                        return new CacheEntry("key");
                    }
                });
    }

    public CacheEntry get(String key) {
        return cache.getIfPresent(key);
    }

    public void put(String key, CacheEntry entry) {
        cache.put(key, entry);
    }

    public long getAveragePutTime() {
        return (long) cache.stats().averageLoadPenalty();
    }

    public long getEvictionCount() {
        return cache.stats().evictionCount();
    }

    public long getSize() {
        return cache.size();
    }

    protected static class CacheEntry {
        private transient String data;

        public CacheEntry(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }
}
