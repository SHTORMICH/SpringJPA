package com.epam.kabaldin.dao;

import com.epam.kabaldin.model.Ticket;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Repository
@Transactional
public interface TicketDAO extends JpaRepository<Ticket, Long> {

    boolean cancelTicket(long ticketId);

    List<Ticket> findAllById(Long id, Pageable pageable);
}

