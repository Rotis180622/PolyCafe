/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.polycafe.controller;

import java.time.LocalDateTime;

/**
 *
 * @author Gaudomun
 */
public interface RevenueController {
    void fillToEmpTable();
    void fillToCatTable();
    
    void filterByTimeRange(LocalDateTime begin, LocalDateTime end);
    
    void calculateSum();
}
