package com.example.demography.service;

import com.example.demography.model.Color;
import com.example.demography.model.Nationality;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface PersonService {
    Double getPercentageEyeColor(Color eyeColor);

    Integer getCountByEyeColorAndNationality(Color eyeColor, Nationality nationality);
}
