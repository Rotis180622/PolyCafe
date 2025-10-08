package com.poly.polycafe.daoimpl;

import com.poly.polycafe.dao.CategoryDAO;
import com.poly.polycafe.entity.Category;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.poly.polycafe.utils.XJdbc;
import com.poly.polycafe.utils.XQuery;

public class CategoryDAOImpl implements CategoryDAO {

    private final String createSql = "INSERT INTO Categories(Id, Name) VALUES(?, ?)";
    private final String updateSql = "UPDATE Categories SET Name=? WHERE Id=?";
    private final String deleteByIdSql = "DELETE FROM Categories WHERE Id=?";
    
    private final String findAllSql = "SELECT * FROM Categories";
    private final String findByIdSql = findAllSql + " WHERE Id=?";

    @Override
    public Category create(Category entity) {
        Object[] values = {
            entity.getId(),
            entity.getName()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Category entity) {
        Object[] values = {
            entity.getName(),
            entity.getId()
        };
            XJdbc.executeUpdate(updateSql, values);

    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteByIdSql, id);
    }

    @Override
    public List<Category> findAll() {
        return XQuery.getBeanList(Category.class, findAllSql);
    }

    @Override
    public Category findById(String id) {
        try {
            return XQuery.getSingleBean(Category.class, findByIdSql, id);
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
