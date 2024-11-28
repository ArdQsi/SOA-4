package ru.lab4.person_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.lab4.person_microservice.model.Color;
import ru.lab4.person_microservice.model.Person;


import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>, PagingAndSortingRepository<Person, Integer>, JpaSpecificationExecutor<Person> {
    @Query("SELECT COUNT(p) FROM Person p WHERE p.eyeColor < :eyeColor")
    Long countByEyeColorLessThan(Color eyeColor);

    @Query("SELECT p FROM Person p WHERE p.eyeColor < :eyeColor")
    List<Person> filterByEyeColorLessThan(Color eyeColor);

    @Query("SELECT p FROM Person p WHERE p.height > :height")
    List<Person> filterByHeightMoreThan(Double height);
}
