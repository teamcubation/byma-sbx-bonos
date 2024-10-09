package com.services.bono.app.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity(name = "Bono")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Data
public class BonoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bono_seq")
    @SequenceGenerator(name = "bono_seq", sequenceName = "bono_sequence", allocationSize = 1)
    private long id;
    private double interestRate;
    private String name;
    private double price;
    private LocalDate creationDate;
}