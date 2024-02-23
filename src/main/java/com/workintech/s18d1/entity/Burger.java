package com.workintech.s18d1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "burger", schema = "fsweb")
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "is_vegan")
    private Boolean isVegan;

    @Column(name = "bread_type")
    @Enumerated(EnumType.STRING)
    private BreadType breadType;

    @Column(name = "contents")
    private String contents;
}
