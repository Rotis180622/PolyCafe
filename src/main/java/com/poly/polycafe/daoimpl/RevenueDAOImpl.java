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
    public static final String revenueByCategoryBase = """
        SELECT
            CAT.Name AS [CategoryName],
            CAST(SUM(BD.UnitPrice * (1 - BD.Discount) * BD.Quantity) AS FLOAT) AS [Revenue],
            SUM(BD.Quantity) AS [Quantity],
            CAST(MIN(D.UnitPrice) AS FLOAT) AS [MinPrice],
            CAST(MAX(D.UnitPrice) AS FLOAT) AS [MaxPrice],
            CAST(AVG(D.UnitPrice) AS FLOAT) AS [AvgPrice]
        FROM Bills B
        JOIN BillDetails BD ON BD.BillId = B.Id
        JOIN Drinks D ON D.Id = BD.DrinkId 
        JOIN Categories CAT ON CAT.Id = D.CategoryId
    """;

    // Không lọc thời gian
    public static final String revenueByCategorySql = 
        revenueByCategoryBase + " GROUP BY CAT.Name";

    // Lọc theo khoảng thời gian
    public static final String revenueByCategorySortByTimeRange = 
        revenueByCategoryBase + 
        " WHERE B.Checkin BETWEEN ? AND ? " +
        " GROUP BY CAT.Name";

        public static final String revenueByEmpBase = """
        SELECT 
            U.Username AS [EmpName], 
            COALESCE(CAST(SUM(BD.UnitPrice * BD.Quantity * (1 - BD.Discount)) AS FLOAT), 0) AS [Revenue], 
            COUNT(DISTINCT BD.Id) AS [NumOfBills], 
            MIN(B.Checkin) AS [FirstBill],
            MAX(B.Checkin) AS [RecentBill]
        FROM Users U
        LEFT JOIN Bills B ON B.Username = U.Username
        LEFT JOIN BillDetails BD ON BD.BillId = B.Id
    """;

    // Không lọc thời gian
    public static final String revenueByEmpSql = 
        revenueByEmpBase + 
        " GROUP BY U.Username " +
        " ORDER BY SUM(BD.UnitPrice * BD.Quantity * (1 - BD.Discount)) DESC";

    // Lọc theo khoảng thời gian
    public static final String revenueByEmpSortByTimeRange = 
        revenueByEmpBase +
        " WHERE B.Checkin BETWEEN ? AND ? " +
        " GROUP BY U.Username " +
        " ORDER BY SUM(BD.UnitPrice * BD.Quantity * (1 - BD.Discount)) DESC";

    @Override
    public List<Revenue.ByCategory> revenueByCat_TimeRange(LocalDateTime begin, LocalDateTime end) {
        return XQuery.getBeanList(Revenue.ByCategory.class, revenueByCategorySortByTimeRange, begin, end);
    }

    @Override
    public List<Revenue.ByEmp> revenueByEmp_TimeRange(LocalDateTime begin, LocalDateTime end) {
        return XQuery.getBeanList(Revenue.ByEmp.class, revenueByEmpSortByTimeRange, begin, end);
    }

    @Override
    public List<Revenue.ByCategory> revenueByCategory() {
        return XQuery.getBeanList(Revenue.ByCategory.class, revenueByCategorySql);
    }

    @Override
    public List<Revenue.ByEmp> revenueByEmp() {
        return XQuery.getBeanList(Revenue.ByEmp.class, revenueByEmpSql);
    }
    
}
