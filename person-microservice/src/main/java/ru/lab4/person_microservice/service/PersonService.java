package ru.lab4.person_microservice.service;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.lab4.person_microservice.model.Color;
import ru.lab4.person_microservice.model.Nationality;
import ru.lab4.person_microservice.model.Person;
import ru.lab4.person_microservice.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public Page<Person> findAllPersonsByFilter(int pageNumber,
                                               int pageSize,
                                               Integer id,
                                               String name,
                                               Double coordinateX,
                                               Double coordinateY,
                                               String creationDate,
                                               Double height,
                                               String birthday,
                                               String passportId,
                                               String eyeColor,
                                               Integer locationX,
                                               Double locationY,
                                               Float locationZ,
                                               String nationality) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Specification<Person> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }
            if (name != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (coordinateX != null) {
                predicates.add(criteriaBuilder.equal(root.get("coordinates").get("x"), coordinateX));
            }
            if (coordinateY != null) {
                predicates.add(criteriaBuilder.equal(root.get("coordinates").get("y"), coordinateY));
            }
            if (creationDate != null) {
                predicates.add(criteriaBuilder.equal(root.get("creationDate"), creationDate));
            }
            if (height != null) {
                predicates.add(criteriaBuilder.equal(root.get("height"), height));
            }
            if (birthday != null) {
                predicates.add(criteriaBuilder.equal(root.get("birthday"), birthday));
            }
            if (passportId != null) {
                predicates.add(criteriaBuilder.equal(root.get("passportId"), passportId));
            }
            if (eyeColor != null) {
                predicates.add(criteriaBuilder.equal(root.get("eyeColor"), Color.valueOf(eyeColor)));
            }
            if (locationX != null) {
                predicates.add(criteriaBuilder.equal(root.get("location").get("x"), locationX));
            }
            if (locationY != null) {
                predicates.add(criteriaBuilder.equal(root.get("location").get("y"), locationY));
            }
            if (locationZ != null) {
                predicates.add(criteriaBuilder.equal(root.get("location").get("z"), locationZ));
            }
            if (nationality != null) {
                predicates.add(criteriaBuilder.equal(root.get("nationality"), Nationality.valueOf(nationality)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return personRepository.findAll(specification, pageable);
    }

    public Long countByEyeColorLessThan(String eyeColor) {
        return personRepository.countByEyeColorLessThan(Color.valueOf(eyeColor));
    }

    public List<Person> filterByEyeColorLessThan(String eyeColor) {
        return personRepository.filterByEyeColorLessThan(Color.valueOf(eyeColor));
    }

    public List<Person> filterByHeightMoreThan(Double height) {
        return personRepository.filterByHeightMoreThan(height);
    }
}