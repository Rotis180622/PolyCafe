/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.entity;

import jakarta.persistence.*;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "Id", nullable = false)
    private Long id;

    // Many bills belong to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Username", nullable = false)
    private Users user;

    // Many bills belong to one card
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CardId", nullable = false)
    private Cards card;

    @Column(name = "Checkin", nullable = false)
    private LocalDateTime checkin;

    @Column(name = "Checkout")
    private LocalDateTime checkout;

    @Column(name = "Status", nullable = false)
    private Integer status;
    
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BillDetails> billDetails;
}

