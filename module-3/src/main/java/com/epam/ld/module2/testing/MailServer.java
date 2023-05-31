package com.epam.ld.module2.testing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Mail server class.
 */
public class MailServer {
    private static final Logger LOGGER = LogManager.getLogger(MailServer.class.getName());

    /**
     * Send notification.
     *
     * @param addresses  the addresses
     * @param messageContent the message content
     */
    public void send(String addresses, String messageContent) {
        LOGGER.debug(addresses + " " + messageContent);
    }
}
