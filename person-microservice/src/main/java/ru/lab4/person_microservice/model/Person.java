package ru.lab4.person_microservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import ru.lab4.person_microservice.model.converter.LocalDateDeserializer;
import ru.lab4.person_microservice.model.converter.LocalDateSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @Column(nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates; //Поле не может быть null

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime creationDate = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private double height; //Значение поля должно быть больше 0

    @Column(nullable = false)
    private LocalDateTime birthday; //Поле не может быть null

    @Column(nullable = false)
    private String passportID; //Длина строки не должна быть больше 32, Строка не может быть пустой, Поле не может быть null

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Color eyeColor; //Поле не может быть null

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Nationality nationality; //Поле не может быть null

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location; //Поле не может быть null
}
