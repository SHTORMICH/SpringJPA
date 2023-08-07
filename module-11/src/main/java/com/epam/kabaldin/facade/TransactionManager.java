package com.epam.kabaldin.facade;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionManager {

    private final BookingFacade bookingFacade;

    public TransactionManager(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @Transactional
    public void performBatchTransaction() {
        bookingFacade.preloadTickets();
    }
}
