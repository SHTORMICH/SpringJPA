package com.epam.kabaldin.dao;

import com.epam.kabaldin.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface EventDAO extends CrudRepository<Event, Long> {
    List<Event> findAllByTitle(String title, Pageable pageable);
    List<Event> findAllByDay(Date day, Pageable pageable);
}
