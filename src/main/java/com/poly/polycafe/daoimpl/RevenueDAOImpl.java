/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.daoimpl;


import com.poly.polycafe.dao.RevenueDAO;
import com.poly.polycafe.entity.Revenue;
import com.poly.polycafe.utils.XQuery;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Gaudomun
 */
public class RevenueDAOImpl implements RevenueDAO{
    public static String revenueByCategorySql = """
                                                  SELECT
                                                  CAT.Name,
                                                  SUM(BD.UnitPrice*(1-BD.Discount)*BD.Quantity) AS[Revenue],
                                                  SUM(BD.Quantity) AS[Quantity],
                                                  MIN(D.UnitPrice) AS[MinPrice],
                                                  MAX(D.UnitPrice) AS[MaxPrice],
                                                  AVG(D.UnitPrice) AS [AvgPrice]
                                                  FROM Bills B
                                                  JOIN BillDetails BD ON BD.BillId = B.Id
                                                  JOIN DRINKS D ON D.Id = BD.DrinkId 
                                                  JOIN Categories CAT ON CAT.Id = D.CategoryId
                                                  GROUP BY CAT.Name
                                                """;
    public static String revenueByEmpSql = 
            """
            SELECT 
                           
                                           
            """;



    @Override
    public List<Revenue.ByCategory> revenueByCategory(LocalDateTime begin, LocalDateTime end) {
        return XQuery.getBeanList(Revenue.ByCategory.class, revenueByCategorySql, begin, end);
    }

    @Override
    public List<Revenue.ByEmp> revenueByEmp(LocalDateTime begin, LocalDateTime end) {
        return XQuery.getBeanList(Revenue.ByEmp.class, revenueByEmpSql, begin, end);
    }
    
}
