package com.epam.kabaldin.dao;

import com.epam.kabaldin.model.Ticket;
import com.epam.kabaldin.model.impl.TicketImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TicketDAO extends CrudRepository<TicketImpl, Long> {
    List<Ticket> findAllById(Long id, Pageable pageable);
}

