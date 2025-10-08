package com.poly.polycafe.daoimpl;

import com.poly.polycafe.dao.BillDetailDAO;
import com.poly.polycafe.entity.BillDetail;
import com.poly.polycafe.utils.XJdbc;
import com.poly.polycafe.utils.XQuery;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;


public class BillDetailDAOImpl implements BillDetailDAO {

    private final String createSql = "INSERT INTO BillDetails(BillId, DrinkId, UnitPrice, Discount, Quantity) VALUES(?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE BillDetails SET BillId=?, DrinkId=?, UnitPrice=?, Discount=?, Quantity=? WHERE Id=?";
    private final String deleteByIdSql = "DELETE FROM BillDetails WHERE Id=?";

    private final String findAllSql = "SELECT bd.*, d.name AS drinkName FROM BillDetails bd JOIN Drinks d ON d.Id=bd.DrinkId";
    private final String findByIdSql = findAllSql + " WHERE bd.Id=?";
    private final String findByBillIdSql = findAllSql + " WHERE bd.BillId=?";
    private final String findByDrinkIdSql = findAllSql + " WHERE bd.DrinkId=?";

    @Override
    public BillDetail create(BillDetail entity) {
        Object[] values = {
            entity.getBillId(),
            entity.getDrinkId(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getQuantity()
        };
        XJdbc.executeUpdate(createSql, values);
       return entity;
    }

    @Override
    public void update(BillDetail entity) {
        Object[] values = {
            entity.getBillId(),
            entity.getDrinkId(),
            entity.getUnitPrice(),
            entity.getDiscount(),
            entity.getQuantity(),
            entity.getId()
        };
            XJdbc.executeUpdate(updateSql, values);
        
    }

    @Override
    public void deleteById(Long id) {
            XJdbc.executeUpdate(deleteByIdSql, id);

    }

    @Override
    public List<BillDetail> findAll() {
            return XQuery.getBeanList(BillDetail.class,findAllSql);

    }

    @Override
    public BillDetail findById(Long id) {
        try {
            return XQuery.getSingleBean(BillDetail.class,findByIdSql, id);
        } catch (Exception ex) {
            Logger.getLogger(BillDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<BillDetail> findByBillId(Long billId) {
            return XQuery.getBeanList(BillDetail.class, findByBillIdSql, billId);
       
    }

    @Override
    public List<BillDetail> findByDrinkId(String drinkId) {
            return XQuery.getBeanList(BillDetail.class, findByDrinkIdSql, drinkId);
      
    }
}
