/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
 import java.math.BigDecimal;


/**
 *
 * @author WellyOwO
 */
public class Revenue {
   
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class ByCategory {
        private String categoryName;
        private int quantity;
        private double revenue;
        private double minPrice;
        private double maxPrice;
        private double avgPrice;
    }

     
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
     public static class ByEmp {
         private String empName;
         private double revenue;
         private int numOfBills;
         private LocalDateTime firstBill;
         private LocalDateTime recentBill;
                 
     }
    
}
