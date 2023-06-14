package com.epam.kabaldin.chatgpt.developer.database;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class SpringTransactionExample {
    @Transactional
    public void performTransaction() {
        // Perform database operations within the transaction
    }
}