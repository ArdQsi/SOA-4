package com.example.demography.service;

import com.example.demography.model.Color;
import com.example.demography.model.Nationality;
import com.example.demography.model.Person;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import com.example.demography.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
import java.util.List;


@Stateless
public class PersonServiceImpl implements PersonService{

    @EJB
    PersonRepository personRepository;

    public Double getPercentageEyeColor(Color eyeColor) {
        double countPersonEye, countAllPerson;
        countPersonEye = personRepository.getByEyeColor(eyeColor).block().size();
        countAllPerson = personRepository.getByAll().block().size();

        return countPersonEye * 100 / countAllPerson;
    }

    public Integer getCountByEyeColorAndNationality(Color eyeColor, Nationality nationality) {
        List<Person> personList = personRepository.getByEyeColor(eyeColor).block();

        Integer count = 0;
        for(Person person : personList){
            if(person.getNationality()==nationality){
                count++;
            }
        }
        return count;
    }
}
