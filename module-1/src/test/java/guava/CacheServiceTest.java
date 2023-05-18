package guava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheServiceTest {
    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        cacheService = new CacheService();
    }

    @Test
    void testCachePutAndGet() {
        String key = "key";
        CacheService.CacheEntry entry = new CacheService.CacheEntry("value");

        cacheService.put(key, entry);
        CacheService.CacheEntry retrievedEntry = cacheService.get(key);

        assertEquals(entry, retrievedEntry);
    }

    @Test
    void testCacheEviction() throws InterruptedException {
        String key = "key";
        CacheService.CacheEntry entry = new CacheService.CacheEntry("value");

        cacheService.put(key, entry);

        Thread.sleep(6000);

        CacheService.CacheEntry retrievedEntry = cacheService.get(key);

        assertNull(retrievedEntry);
    }

    @Test
    void testCacheStatistics() {
        String key1 = "key1";
        CacheService.CacheEntry entry1 = new CacheService.CacheEntry("value1");

        String key2 = "key2";
        CacheService.CacheEntry entry2 = new CacheService.CacheEntry("value2");

        cacheService.put(key1, entry1);
        cacheService.put(key2, entry2);

        long evictionCount = cacheService.getEvictionCount();
        long size = cacheService.getSize();

        assertEquals(0, evictionCount);
        assertEquals(2, size);
    }

    @Test
    void testCacheConcurrentAccess() throws InterruptedException {
        final int numThreads = 10;
        final int numOperationsPerThread = 1000;

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < numOperationsPerThread; j++) {
                    String key = "key" + threadId + "-" + j;
                    CacheService.CacheEntry entry = new CacheService.CacheEntry("value" + threadId + "-" + j);
                    cacheService.put(key, entry);
                    CacheService.CacheEntry retrievedEntry = cacheService.get(key);
                    assertEquals(entry, retrievedEntry);
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(numThreads * numOperationsPerThread, cacheService.getSize());
    }
}