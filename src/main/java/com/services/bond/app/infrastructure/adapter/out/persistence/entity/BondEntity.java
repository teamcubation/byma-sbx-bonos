package com.services.bond.app.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Bond")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BondEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bond_seq")
    @SequenceGenerator(name = "bond_seq", sequenceName = "bond_sequence", allocationSize = 1)
    private long id;
    private double interestRate;
    private String name;
    private double price;
    private LocalDate creationDate;
}