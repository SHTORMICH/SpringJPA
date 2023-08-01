package com.epam.kabaldin.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.kabaldin.model.Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StorageInitializerTest {
    private StorageInitializer storageInitializer;

    @BeforeEach
    void setUp() {
        storageInitializer = new StorageInitializer();
    }

    @Test
    void testInitializeStorage() throws IOException {
        Map<String, List<Entity>> storage = new HashMap<>();
        storageInitializer.setStorageSourceFile("storage.txt");
        storageInitializer.postProcessBeforeInitialization(storage, "storage");

        List<Entity> users = storage.get("user");
        assertEquals(2, users.size());

        List<Entity> events = storage.get("event");
        assertEquals(2, events.size());

        List<Entity> tickets = storage.get("ticket");
        assertEquals(2, tickets.size());
    }

    @Test
    void testInitializeStorageWithInvalidData() throws IOException {
        Map<String, List<Entity>> storage = new HashMap<>();
        storageInitializer.setStorageSourceFile("test.txt");

        try {
            storageInitializer.postProcessBeforeInitialization(storage, "storage");
        } catch (RuntimeException e) {
            assertEquals("Error initializing storage.", e.getMessage());
            assertEquals(IOException.class, e.getCause().getClass());
        }

        assertEquals(0, storage.size());
    }

}
