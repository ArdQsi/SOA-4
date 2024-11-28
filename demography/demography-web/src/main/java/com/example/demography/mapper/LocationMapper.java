package com.example.demography.mapper;

import com.example.demography.model.Location;
import https.example_com.please.SoapLocation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationMapper {
    LocationMapper MAPPER = Mappers.getMapper(LocationMapper.class);

    Location map(SoapLocation soapLocation);
    SoapLocation map(Location location);
}
