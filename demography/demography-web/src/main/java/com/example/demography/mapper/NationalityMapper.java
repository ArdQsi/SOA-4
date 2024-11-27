package com.example.demography.mapper;

import com.example.demography.model.Nationality;
import https.example_com.please.SoapNationality;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NationalityMapper {
    NationalityMapper MAPPER = Mappers.getMapper(NationalityMapper.class);

    Nationality map(SoapNationality soapNationality);
    SoapNationality map(Nationality nationality);
}
