package com.poly.polycafe.dao;

import com.poly.polycafe.entity.Drink;
import java.util.List;


public interface DrinkDAO extends CrudDAO<Drink, String>{
    /**
     * Truy vấn theo loại
     * 
     * @param categoryId mã loại đồ uống
     * @return kết quả truy vấn
     */
    List<Drink> findByCategoryId(String categoryId);
}
