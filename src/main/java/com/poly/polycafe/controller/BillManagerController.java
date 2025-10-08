/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.polycafe.controller;

import com.poly.polycafe.entity.Bills;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author Gaudomun
 */
public interface BillManagerController extends CrudController<Bills, Long>{
    void fillToDetailTable(Long billId);
    
    void filterByTimeRange(LocalDateTime begin, LocalDateTime end);
}
