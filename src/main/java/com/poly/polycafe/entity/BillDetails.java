/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BillDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY(100000,1)
    @Column(name = "Id", nullable = false)
    private Long id;

    // Many BillDetails belong to one Bill
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BillId", nullable = false)
    private Bills bill;

    // Many BillDetails belong to one Drink
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DrinkId", nullable = false)
    private Drinks drink;

    @Column(name = "UnitPrice", nullable = false)
    private double unitPrice;

    @Column(name = "Discount", nullable = false)
    private double discount;

    @Column(name = "Quantity", nullable = false)
    private int quantity;
}
