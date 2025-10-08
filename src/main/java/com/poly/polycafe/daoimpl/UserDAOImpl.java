package com.poly.polycafe.daoimpl;

import com.poly.polycafe.dao.UserDAO;
import com.poly.polycafe.entity.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.poly.polycafe.utils.XJdbc;
import com.poly.polycafe.utils.XQuery;

public class UserDAOImpl implements UserDAO {

    private final String createSql = "INSERT INTO Users(Username, Password, Enabled, Fullname, Photo, Manager) VALUES(?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE Users SET Password=?, Enabled=?, Fullname=?, Photo=?, Manager=? WHERE Username=?";
    private final String deleteByIdSql = "DELETE FROM Users WHERE Username=?";
    
    private final String findAllSql = "SELECT * FROM Users";
    private final String findByIdSql = "SELECT * FROM Users WHERE Username=?";

    @Override
    public User create(User entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getPassword(),
            entity.isEnabled(),
            entity.getFullname(),
            entity.getPhoto(),
            entity.isManager()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(User entity) {
        Object[] values = {
            entity.getPassword(),
            entity.isEnabled(),
            entity.getFullname(),
            entity.getPhoto(),
            entity.isManager(),
            entity.getUsername()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteByIdSql, id);
    }

    @Override
    public List<User> findAll() {
        return XQuery.getBeanList(User.class, findAllSql);
    }

    @Override
    public User findById(String id) {
        try {
            return XQuery.getSingleBean(User.class, findByIdSql, id);
        } catch (Exception ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
