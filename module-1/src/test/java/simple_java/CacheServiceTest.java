package simple_java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simple_java.CacheService;

import static org.junit.jupiter.api.Assertions.*;

class CacheServiceTest {
    private static final int MAX_SIZE = 5;
    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        cacheService = new CacheService(MAX_SIZE);
    }

    @Test
    void testCachePutAndGet() {
        cacheService.put("key1", "value1");
        cacheService.put("key2", "value2");
        cacheService.put("key3", "value3");

        CacheService.CacheEntry entry1 = cacheService.get("key1");
        assertNotNull(entry1);
        assertEquals("value1", entry1.getValue());

        CacheService.CacheEntry entry2 = cacheService.get("key2");
        assertNotNull(entry2);
        assertEquals("value2", entry2.getValue());

        CacheService.CacheEntry entry3 = cacheService.get("key3");
        assertNotNull(entry3);
        assertEquals("value3", entry3.getValue());

        CacheService.CacheEntry entry4 = cacheService.get("nonexistent");
        assertNull(entry4);
    }

    @Test
    void getShouldReturnNullForNonexistentKey() {
        CacheService.CacheEntry entry = cacheService.get("nonexistent");
        assertNull(entry);
    }

    @Test
    void testCacheEviction() {
        cacheService.put("key1", "value1");
        cacheService.put("key2", "value2");
        cacheService.put("key3", "value3");
        cacheService.put("key4", "value4");
        cacheService.put("key5", "value5");
        cacheService.put("key6", "value6");

        assertNull(cacheService.get("key1")); // Evicted due to maxSize
        assertNotNull(cacheService.get("key2"));
        assertNotNull(cacheService.get("key3"));
        assertNotNull(cacheService.get("key4"));
        assertNotNull(cacheService.get("key5"));
        assertNotNull(cacheService.get("key6"));
    }

    @Test
    void testCacheStatistics() {
        cacheService.put("key1", "value1");
        cacheService.put("key2", "value2");
        cacheService.put("key3", "value3");

        assertEquals(3, cacheService.getNumPuts());
        assertEquals(0, cacheService.getNumEvictions());

        cacheService.put("key4", "value4");
        cacheService.put("key5", "value5");
        cacheService.put("key6", "value6");

        assertEquals(6, cacheService.getNumPuts());
        assertEquals(1, cacheService.getNumEvictions());
    }
}