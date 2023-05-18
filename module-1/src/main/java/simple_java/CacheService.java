package simple_java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CacheService {
    private transient final int maxSize;
    private transient final ConcurrentHashMap<String, CacheEntry> cache;
    private transient final PriorityQueue<CacheEntry> evictionQueue;
    private transient final Lock evictionLock;
    private static final Logger logger =  LogManager.getLogger(CacheService.class.getName());

    private transient double numPuts;
    private transient int numEvictions;
    private transient double totalPutTime;

    public CacheService(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new ConcurrentHashMap<>();
        this.evictionQueue = new PriorityQueue<>(Comparator.comparingInt(CacheEntry::getAccessCount));
        this.evictionLock = new ReentrantLock();
        this.numPuts = 0;
        this.numEvictions = 0;
        this.totalPutTime = 0;
    }

    public CacheEntry get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry != null) {
            entry.updateAccessTime();
            evictionQueue.offer(entry);
            return entry;
        } else {
            return null;
        }
    }

    public void put(String key, String value) {
        long startTime = System.nanoTime();

        CacheEntry entry = new CacheEntry(key, value);
        CacheEntry oldEntry = cache.put(key, entry);

        if (oldEntry != null) {
            evictionQueue.remove(oldEntry);
        }

        if (cache.size() > maxSize) {
            evictEntries();
        }

        evictionQueue.offer(entry);

        numPuts++;
        totalPutTime += System.nanoTime() - startTime;
    }

    private void evictEntries() {
        evictionLock.lock();
        try {
            while (cache.size() > maxSize) {
                CacheEntry entryToEvict = evictionQueue.poll();
                if (entryToEvict != null) {
                    cache.remove(entryToEvict.getKey());
                    logger.debug("Removed entry: " + entryToEvict);
                    numEvictions++;
                }
            }
        } finally {
            evictionLock.unlock();
        }
    }

    public double getNumPuts() {
        return numPuts;
    }

    public int getNumEvictions() {
        return numEvictions;
    }

    public double getAveragePutTime() {
        if (numPuts == 0.0) {
            return 0.0;
        } else {
            return totalPutTime / numPuts;
        }
    }

    static class CacheEntry {
        private final String key;
        private final String value;
        private transient int accessCount;
        private transient long lastAccessTime;

        public CacheEntry(String key, String value) {
            this.key = key;
            this.value = value;
            this.accessCount = 0;
            this.lastAccessTime = System.nanoTime();
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public int getAccessCount() {
            return accessCount;
        }

        public void updateAccessTime() {
            accessCount++;
            lastAccessTime = System.nanoTime();
        }

        @Override
        public String toString() {
            return "CacheEntry{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    ", accessCount=" + accessCount +
                    ", lastAccessTime=" + lastAccessTime +
                    '}';
        }
    }
}
