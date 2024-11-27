package com.example.demography.mapper;

import com.example.demography.model.Coordinates;
import https.example_com.please.SoapCoordinates;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoordinatesMapper {
    CoordinatesMapper MAPPER = Mappers.getMapper(CoordinatesMapper.class);

    Coordinates map(SoapCoordinates soapCoordinates);
    SoapCoordinates map(Coordinates coordinates);
}
