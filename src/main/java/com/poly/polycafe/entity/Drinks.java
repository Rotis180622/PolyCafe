/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Gaudomun
 */
@Entity
@Table(name = "Drinks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drinks {
    @Id
    @Column(name = "Id", length = 20, nullable = false)
    private String id;

    @Column(name = "Name", length = 50, nullable = false)
    private String name;

    @Column(name = "UnitPrice", nullable = false)
    private double unitPrice;

    @Column(name = "Discount", nullable = false)
    private double discount;

    @Column(name = "Image", length = 50, nullable = true)
    private String image;

    @Column(name = "Available", nullable = false)
    private boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryId", nullable = false)
    private Categories category;
    
    @OneToMany(mappedBy = "drink", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BillDetails> billDetails;

}
