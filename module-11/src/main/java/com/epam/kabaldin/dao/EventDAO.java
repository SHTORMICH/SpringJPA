package com.epam.kabaldin.dao;

import com.epam.kabaldin.model.Event;
import com.epam.kabaldin.model.impl.EventImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface EventDAO extends CrudRepository<EventImpl, Long> {
    Event save(Event event);
    List<Event> findAllByTitle(String title, Pageable pageable);
    List<Event> findAllByDate(Date date, Pageable pageable);
}
