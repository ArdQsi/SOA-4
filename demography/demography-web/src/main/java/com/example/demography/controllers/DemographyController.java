package com.example.demography.controllers;

import com.example.demography.config.JNDIConfig;
import com.example.demography.dto.CountMessageDto;
import com.example.demography.dto.PercentageMassageDto;
import com.example.demography.model.Color;
import com.example.demography.model.Nationality;
import com.example.demography.service.PersonService;
import jakarta.ejb.EJB;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("demography")
@RequiredArgsConstructor
public class DemographyController {
   PersonService personService = JNDIConfig.getPersonService();

    @GetMapping("{eyeColor}/percentage")
    public PercentageMassageDto getPercentage(@PathVariable Color eyeColor){
        System.out.println(eyeColor);
        return new PercentageMassageDto(personService.getPercentageEyeColor(eyeColor));
    }

    @GetMapping("nationality/{nationality}/eye-color/{eyeColor}")
    public CountMessageDto getCountPersons(@PathVariable Nationality nationality, @PathVariable Color eyeColor){
        System.out.println(nationality + " " + eyeColor);
        return new CountMessageDto(personService.getCountByEyeColorAndNationality(eyeColor,nationality));
    }
}
