/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.polycafe.controller;

import com.poly.polycafe.entity.Drinks;

/**
 *
 * @author Gaudomun
 */
public interface DrinkController extends CrudController<Drinks, String>{
    void fillCategoriesTable();
    
    void searchByKeyword(String keyword);
}
