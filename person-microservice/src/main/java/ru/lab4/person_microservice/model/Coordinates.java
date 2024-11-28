package ru.lab4.person_microservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double x; //Значение поля должно быть больше -886, Поле не может быть null

    private double y; //Значение поля должно быть больше -53
}
