package com.poly.polycafe.daoimpl;

import com.poly.polycafe.dao.DrinkDAO;
import com.poly.polycafe.entity.Drink;
import com.poly.polycafe.utils.XJdbc;
import com.poly.polycafe.utils.XQuery;
import com.poly.polycafe.utils.XStr;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DrinkDAOImpl implements DrinkDAO {

    private final String createSql = "INSERT INTO Drinks(Id, Name, Image, UnitPrice, Discount, Available, CategoryId) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE Drinks SET Name=?, Image=?, UnitPrice=?, Discount=?, Available=?, CategoryId=? WHERE Id=?";
    private final String deleteByIdSql = "DELETE FROM Drinks WHERE Id=?";
    
    private final String findAllSql = "SELECT * FROM Drinks";
    private final String findByIdSql = findAllSql + " WHERE Id=?";
    private final String findByCategoryIdSql = findAllSql + " WHERE CategoryId=?";

    @Override
    public Drink create(Drink entity) {
        entity.setId(XStr.getKey());
        Object[] values = {
            entity.getId(),
            entity.getName(),
            entity.getImage(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.isAvailable(),
            entity.getCategoryId()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Drink entity) {
        Object[] values = {
            entity.getName(),
            entity.getImage(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.isAvailable(),
            entity.getCategoryId(),
            entity.getId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteByIdSql, id);
    }

    @Override
    public List<Drink> findAll() {
        return XQuery.getBeanList(Drink.class, findAllSql);
    }

    @Override
    public Drink findById(String id) {
        try {
            return XQuery.getSingleBean(Drink.class, findByIdSql, id);
        } catch (Exception ex) {
            Logger.getLogger(DrinkDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Drink> findByCategoryId(String categoryId) {
        return XQuery.getBeanList(Drink.class, findByCategoryIdSql, categoryId);
    }    
}
