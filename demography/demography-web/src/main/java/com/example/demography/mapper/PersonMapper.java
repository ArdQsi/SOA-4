package com.example.demography.mapper;

import com.example.demography.model.Person;
import https.example_com.please.SoapPerson;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper MAPPER = Mappers.getMapper(PersonMapper.class);

    Person map(SoapPerson soapPerson);
    SoapPerson map(Person person);
}
