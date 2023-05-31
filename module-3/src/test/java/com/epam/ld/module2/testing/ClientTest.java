package com.epam.ld.module2.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientTest {
    private Client client;

    @BeforeEach
    public void setup() {
        client = new Client();
    }

    @Test
    public void testGetAddresses() {
        String addresses = "123 Main St, City";
        client.setAddresses(addresses);

        String result = client.getAddresses();

        Assertions.assertEquals(addresses, result);
    }

    @Test
    public void testSetAddresses() {
        String addresses = "456 Elm St, Town";

        client.setAddresses(addresses);

        String result = client.getAddresses();

        Assertions.assertEquals(addresses, result);
    }

    @Test
    public void testGetFullName() {
        String fullName = "John Doe";
        client.setFullName(fullName);

        String result = client.getFullName();

        Assertions.assertEquals(fullName, result);
    }

    @Test
    public void testSetFullName() {
        String fullName = "Jane Smith";

        client.setFullName(fullName);

        String result = client.getFullName();

        Assertions.assertEquals(fullName, result);
    }
}