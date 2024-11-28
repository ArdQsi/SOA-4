package ru.lab4.person_microservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lab4.person_microservice.model.Person;
import ru.lab4.person_microservice.dto.ErrorMessageDto;
import ru.lab4.person_microservice.service.PersonService;



import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("persons")
public class PersonController {
    private final PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable int id) {
        Optional<Person> person = personService.findById(id);
        if (person.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessageDto(HttpStatus.NOT_FOUND.value(), "Not found item"));
        } else {
            return ResponseEntity.ok(person);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        try {
            personService.save(person);
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), "Validation Failed"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable int id, @RequestBody Person person) {
        try {
            Optional<Person> personToUpdate = personService.findById(id);
            if (personToUpdate.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorMessageDto(HttpStatus.NOT_FOUND.value(), "Not found item"));
            }
            person.setId(id);
            personService.update(person);
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), "Validation Failed"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable int id) {
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            personService.delete(id);
            return ResponseEntity.ok("Removal was successful");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorMessageDto(HttpStatus.NOT_FOUND.value(), "Not found item"));
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getAllPersonsByFilter(
            @RequestParam(value = "page-number", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page-size", defaultValue = "10") int pageSize,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "coordinate-x", required = false) Double coordinateX,
            @RequestParam(value = "coordinate-y", required = false) Double coordinateY,
            @RequestParam(value = "creation-date", required = false) String creationDate,
            @RequestParam(value = "height", required = false) Double height,
            @RequestParam(value = "birthday", required = false) String birthday,
            @RequestParam(value = "passport-id", required = false) String passportId,
            @RequestParam(value = "eye-color", required = false) String eyeColor,
            @RequestParam(value = "nationality", required = false) String nationality,
            @RequestParam(value = "location-x", required = false) Integer locationX,
            @RequestParam(value = "location-y", required = false) Double locationY,
            @RequestParam(value = "location-z", required = false) Float locationZ) {

        try {
            Page<Person> persons = personService.findAllPersonsByFilter(pageNumber, pageSize, id, name, coordinateX,
                    coordinateY, creationDate, height, birthday, passportId, eyeColor, locationX, locationY, locationZ, nationality);
            return ResponseEntity.ok(persons.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), "Validation Failed"));
        }
    }

    @GetMapping("/count/less/{eyeColor}")
    public ResponseEntity<?> countLessEyeColor(@PathVariable String eyeColor) {
        try {
            Long count = personService.countByEyeColorLessThan(eyeColor);
            return ResponseEntity.ok("Amount: " + count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), "Validation Failed"));
        }
    }


    @GetMapping("/filter/less/{eyeColor}")
    public ResponseEntity<?> filterLessEyeColor(@PathVariable("eyeColor") String eyeColor) {
        try {
            List<Person> persons = personService.filterByEyeColorLessThan(eyeColor);
            if (persons.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorMessageDto(HttpStatus.NOT_FOUND.value(), "Not found item"));
            }
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), "Validation Failed"));
        }
    }

    @GetMapping("/filter/more/{height}")
    public ResponseEntity<?> filterMoreHeight(@PathVariable("height") Double height) {
        try {
            if (height == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), "Validation Failed"));
            }
            List<Person> persons = personService.filterByHeightMoreThan(height);
            if (persons.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorMessageDto(HttpStatus.NOT_FOUND.value(), "Not found item"));
            }
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorMessageDto(HttpStatus.BAD_REQUEST.value(), "Validation Failed"));
        }
    }
}