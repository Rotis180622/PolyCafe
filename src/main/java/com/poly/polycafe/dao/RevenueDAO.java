/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.polycafe.dao;


import com.poly.polycafe.entity.Revenue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gaudomun
 */
public interface RevenueDAO {
    /**
     * Truy vấn doanh thu theo loại sách
     * @param begin
     * @param end
     * @return 
     */
    List<Revenue.ByCategory>revenueByCategory(LocalDateTime begin, LocalDateTime end);
    
    /**
     * Truy vấn doanh thu theo nhân viên
     * @param begin
     * @param end
     * @return 
     */
    List<Revenue.ByEmp>revenueByEmp(LocalDateTime begin, LocalDateTime end);
}
