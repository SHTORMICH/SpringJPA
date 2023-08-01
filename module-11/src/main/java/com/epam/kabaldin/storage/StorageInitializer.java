package com.epam.kabaldin.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.epam.kabaldin.model.Entity;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.User;
import com.epam.kabaldin.model.impl.EventImpl;
import com.epam.kabaldin.model.impl.TicketImpl;
import com.epam.kabaldin.model.impl.UserImpl;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ResourceUtils;

public class StorageInitializer implements BeanPostProcessor {
    private String storageSourceFile;

    public void setStorageSourceFile(String storageSourceFile) {
        this.storageSourceFile = storageSourceFile;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if ("storage".equals(beanName) && bean instanceof Map) {
            try {
                initializeStorage((Map<String, List<Entity>>) bean);
            } catch (IOException e) {
                throw new RuntimeException("Error initializing storage.", e);
            }
        }
        return bean;
    }

    private void initializeStorage(Map<String, List<Entity>> storage) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        List<String> lines = Files.readAllLines(ResourceUtils.getFile("classpath:" + storageSourceFile).toPath());
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 1) {
                String entityType = parts[0].trim();
                if (entityType.equalsIgnoreCase("user")) {
                    if (parts.length >= 4) {
                        User user = getUser(parts);
                        getSubstorage(storage, "user").add(user);
                    }
                } else if (entityType.equalsIgnoreCase("event")) {
                    if (parts.length >= 4) {
                        Event event = getEvent(parts);
                        getSubstorage(storage, "event").add(event);
                    }
                } else if (entityType.equalsIgnoreCase("ticket")) {
                    if (parts.length >= 6) {
                        Ticket ticket = getTicket(parts);
                        getSubstorage(storage, "ticket").add(ticket);
                    }
                }
            }
        }
    }

    private static Ticket getTicket(String[] parts) {
        Ticket ticket = new TicketImpl();
        ticket.setId(Long.parseLong(parts[1].trim()));
        ticket.setEventId(Long.parseLong(parts[2].trim()));
        ticket.setUserId(Long.parseLong(parts[3].trim()));
        ticket.setCategory(Ticket.Category.valueOf(parts[4].trim()));
        ticket.setPlace(Integer.parseInt(parts[5].trim()));
        return ticket;
    }

    private static Event getEvent(String[] parts) {
        Event event = new EventImpl();
        event.setId(Long.parseLong(parts[1].trim()));
        event.setTitle(parts[2].trim());
        event.setDate(Date.valueOf(parts[3].trim()));
        return event;
    }

    private static User getUser(String[] parts) {
        User user = new UserImpl();
        user.setId(Long.parseLong(parts[1].trim()));
        user.setName(parts[2].trim());
        user.setEmail(parts[3].trim());
        return user;
    }

    private List<Entity> getSubstorage (Map<String, List<Entity>> storage, String key) {
        return storage.computeIfAbsent(key, k -> new ArrayList<>());
    }
}


