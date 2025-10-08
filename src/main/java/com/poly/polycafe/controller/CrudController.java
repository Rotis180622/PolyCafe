/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.polycafe.controller;

/**
 *
 * @author Gaudomun
 * @param <T>
 * @param <ID>
 */
public interface CrudController<T, ID> {
    void setForm(T entity);
    T getForm();
    
    void create();
    void update();
    void delete();
    void clear();
    
    void moveFirst();
    void moveNext();
    void movePrev();
    void moveLast();
    
    void checkAll();
    void uncheckAll();
    void deleteCheckedItems();
    void exportExcel();
    
    void fillToTable();
    
    void open();
}
