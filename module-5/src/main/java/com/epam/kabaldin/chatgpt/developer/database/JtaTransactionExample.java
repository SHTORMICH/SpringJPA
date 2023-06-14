package com.epam.kabaldin.chatgpt.developer.database;

import javax.annotation.Resource;
import javax.transaction.UserTransaction;

public class JtaTransactionExample {
    @Resource
    private UserTransaction userTransaction;

    public void performTransaction() {
        try {
            userTransaction.begin();

            // Perform database operations within the transaction

            // Commit the transaction
            userTransaction.commit();
        } catch (Exception e) {
            // Handle exception
            try {
                // Roll back the transaction on error
                userTransaction.rollback();
            } catch (Exception rollbackEx) {
                // Handle rollback exception
            }
        }
    }
}
