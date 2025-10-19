/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.dao;

/**
 *
 * @author Gaudomun
 */
public class BillVmDAO {
    String sql = """
                    SELECT 
                        D.Name,
                        BD.UnitPrice,
                        BD.Quantity,
                        BD.Discount
                    FROM BillDetails BD
                    JOIN Drinks D ON BD.DrinkId = D.Id
                 """;
}
