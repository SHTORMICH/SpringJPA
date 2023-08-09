package com.epam.kabaldin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        XmlWebApplicationContext xmlContext = new XmlWebApplicationContext();
        xmlContext.setConfigLocation("/WEB-INF/applicationContext.xml");

        servletContext.addListener(new ContextLoaderListener(xmlContext));

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(xmlContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try (sessionFactory; Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Perform database operations using Hibernate

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
