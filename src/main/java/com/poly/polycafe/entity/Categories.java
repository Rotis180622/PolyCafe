/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 *
 * @author Gaudomun
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Categories")

public class Categories {
    @Id
    @Column(name="Id", length=20, nullable=false)
    private String id;
    
    @Column(name="Name", length=50, nullable=false)
    private String name;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
    private List<Drinks> drinks;
}
