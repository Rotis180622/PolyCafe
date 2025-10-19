/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.polycafe.dao;


import com.poly.polycafe.entity.Revenue;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Gaudomun
 */
public interface RevenueDAO {
    List<Revenue.ByCategory>revenueByCategory();
    List<Revenue.ByCategory> revenueByCat_TimeRange(LocalDateTime begin, LocalDateTime end);
   
    List<Revenue.ByEmp> revenueByEmp();
    List<Revenue.ByEmp> revenueByEmp_TimeRange(LocalDateTime begin, LocalDateTime end);
}
