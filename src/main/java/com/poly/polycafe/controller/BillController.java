/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.polycafe.controller;

import com.poly.polycafe.entity.Bill;

/**
 *
 * @author Gaudomun
 */
public interface BillController {
    void open();
    
    void fillOrderTable();
    void deleteDrink();
    void cancelBill();
    
    Bill createBill();
    void createBillDetail();
    
    Bill getForm();
}
