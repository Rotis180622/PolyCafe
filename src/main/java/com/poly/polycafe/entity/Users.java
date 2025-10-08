/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import lombok.Builder;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @Column(name = "Username", length = 20, nullable = false)
    private String username;

    @Column(name = "Password", length = 255, nullable = false)
    private String password;

    @Column(name = "Enabled", nullable = false)
    private boolean enabled;

    @Column(name = "Fullname", length = 100, nullable = false)
    private String fullname;

    @Column(name = "Photo", length = 255)
    private String photo;

    @Column(name = "Manager")
    private boolean manager; // có thể null

    // Một user có nhiều Bills
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bills> bills;
}

