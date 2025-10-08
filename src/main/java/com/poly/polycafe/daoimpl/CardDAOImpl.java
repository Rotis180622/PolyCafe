package com.poly.polycafe.daoimpl;

import com.poly.polycafe.dao.CardDAO;
import com.poly.polycafe.entity.Card;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.poly.polycafe.utils.XJdbc;
import com.poly.polycafe.utils.XQuery;

public class CardDAOImpl implements CardDAO {

    private final String createSql = "INSERT INTO Cards VALUES(?, ?)";
    private final String updateSql = "UPDATE Cards SET Status=? WHERE Id=?";
    private final String deleteByIdSql = "DELETE FROM Cards WHERE Id=?";
    
    private final String findAllSql = "SELECT * FROM Cards";
    private final String findByIdSql = findAllSql + " WHERE Id=?";

    @Override
    public Card create(Card entity) {
        Object[] values = {
            entity.getId(),
            entity.getStatus()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Card entity) {
        Object[] values = {
            entity.getStatus(),
            entity.getId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(deleteByIdSql, id);
    }

    @Override
    public List<Card> findAll() {
        return XQuery.getBeanList(Card.class, findAllSql);
    }

    @Override
    public Card findById(Integer id) {
        try {
            return XQuery.getSingleBean(Card.class,findByIdSql, id);
        } catch (Exception ex) {
            Logger.getLogger(CardDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
