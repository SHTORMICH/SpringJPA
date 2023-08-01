package com.epam.kabaldin;

import com.epam.kabaldin.facade.BookingFacadeImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookingFacadeImpl bookingFacade = applicationContext.getBean(BookingFacadeImpl.class);
    }
}
