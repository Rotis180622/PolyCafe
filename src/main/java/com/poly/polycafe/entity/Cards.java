/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
/**
 *
 * @author Gaudomun
 */

@Entity
@Table(name = "Cards")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "bills")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cards {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Id", nullable=false)
    @EqualsAndHashCode.Include
    private Integer id;

    @Enumerated(EnumType.ORDINAL) // lưu số 0/1/2
    // @Enumerated(EnumType.STRING) // lưu chữ "ERROR"/"OPERATING"/"LOST"
    @Column(name = "Status", nullable = false)
    private CardStatus status;
    
    @OneToMany(mappedBy="card", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
    private List<Bills> bills = new ArrayList<>();

    public enum CardStatus {
        ERROR, OPERATING, LOST;
    }

}
