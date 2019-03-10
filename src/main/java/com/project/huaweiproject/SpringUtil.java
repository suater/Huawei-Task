/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.huaweiproject;
 
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory; 
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author suat.erdogan
 */
@Configuration
public class SpringUtil {
    @Autowired
    private static SessionFactory sessionFactory;
    /**
     * 
     * @return hibernet sesion factory
     */
    @Bean
    public static SessionFactory getSessionFactory() {
         
        try {
             
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    
    
     return sessionFactory;
}
     
    
}
