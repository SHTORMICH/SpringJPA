package com.epam.kabaldin.service.impl;

import com.epam.kabaldin.dao.EventDAO;
import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.impl.EventImpl;
import com.epam.kabaldin.service.EventService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
public class EventServiceImpl implements EventService, ApplicationContextAware {
    private EventDAO eventDAO;

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext.getBean(EventDAO.class));
        System.out.println("Hello world!");
    }

    @Override
    public Event getEventById(Long eventId) {
        Optional<EventImpl> eventOp = eventDAO.findById(eventId);
        eventOp.isPresent();
        return eventOp.get();
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return eventDAO.findAllByTitle(title, pageable);
    }

    @Override
    public List<Event> getEventsForDay(Date date, int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return eventDAO.findAllByDate(date, pageable);
    }

    @Override
    public Event createEvent(Event event) {
        eventDAO.save(((EventImpl) event));
        return event;
    }

    @Override
    public Event updateEvent(Event event) {
        eventDAO.save(((EventImpl) event));
        return event;
    }

    @Override
    public boolean deleteEvent(Long eventId) {
        eventDAO.deleteById(eventId);
        return true;
    }


}
