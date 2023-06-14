package com.epam.kabaldin.chatgpt.developer.database;

import redis.clients.jedis.Jedis;

public class RedisCacheExample {
    private Jedis jedis;

    public RedisCacheExample() {
        // Initialize Redis connection
        jedis = new Jedis("localhost");
    }

    public String getCachedData(String key) {
        // Check if data exists in Redis cache
        if (jedis.exists(key)) {
            // Retrieve data from Redis cache
            return jedis.get(key);
        } else {
            // Fetch data from the database
            String data = fetchDataFromDatabase(key);

            // Store data in Redis cache
            jedis.set(key, data);

            return data;
        }
    }

    private String fetchDataFromDatabase(String key) {
        // Simulate fetching data from the database
        // ...

        return "Data for key: " + key;
    }

    public void close() {
        // Close Redis connection
        jedis.close();
    }
}