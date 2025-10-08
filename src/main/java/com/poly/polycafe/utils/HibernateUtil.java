/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.utils;

import com.poly.polycafe.entity.BillDetails;
import com.poly.polycafe.entity.Bills;
import com.poly.polycafe.entity.Cards;
import com.poly.polycafe.entity.Categories;
import com.poly.polycafe.entity.Users;
import com.poly.polycafe.entity.Drinks;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Gaudomun
 */

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
     
    static {
        try {
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(Users.class);
            cfg.addAnnotatedClass(Cards.class);
            cfg.addAnnotatedClass(Bills.class);
            cfg.addAnnotatedClass(Categories.class);
            cfg.addAnnotatedClass(BillDetails.class);
            cfg.addAnnotatedClass(Drinks.class);
            
            sessionFactory = cfg.configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        }catch(HibernateException ex) {
            System.err.println("Lỗi" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
    
    
}
