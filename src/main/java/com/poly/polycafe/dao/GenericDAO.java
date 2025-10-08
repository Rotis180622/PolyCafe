/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.dao;

import com.poly.polycafe.entity.Users;
import com.poly.polycafe.utils.HibernateUtil;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 *
 * @author Gaudomun
 * @param <T>
 * @param <ID>
 */
public class GenericDAO<T, ID extends Serializable> {
     private final Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public T save(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    
    /** Update entity
     * @param entity
     * @return  */
    public T update(T entity) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSession(); 
        try {
            transaction = session.beginTransaction();
            T mergedEntity = (T) session.merge(entity); 
            transaction.commit();
            return mergedEntity;
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close(); 
            }
        }
    }

    /** Delete entity
     * @param entity
     * @return  */
   public boolean delete(T entity) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSession(); 
        try {
            transaction = session.beginTransaction();
            T mergedEntity = (T) session.merge(entity);
            session.remove(mergedEntity);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close(); 
            }
        }
    }

    
    public T findById(ID id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(entityClass, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /** Find all entities
     * @return  */
    public List<T> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("from " + entityClass.getName(), entityClass).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        demo1();

    }

    private static void demo1() {
       GenericDAO<Users, String> usersDao = new GenericDAO<>(Users.class); 
       List<Users> users = usersDao.findAll();

       for(Users user : users) {
           System.out.println(user.getFullname());
           System.out.println(user.getUsername());
           System.out.printf("Vai trò: %s \n", user.isManager()? "Quản lý":"Nhân viên");
           System.out.printf("Trạng thái: %s \n\n", user.isEnabled()?"Làm việc":"Đã nghỉ");
   
       }
    }
}
