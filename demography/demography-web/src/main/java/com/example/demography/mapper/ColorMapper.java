package com.example.demography.mapper;

import com.example.demography.model.Color;
import https.example_com.please.SoapColor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ColorMapper {
    ColorMapper MAPPER = Mappers.getMapper(ColorMapper.class);

    Color map(SoapColor soapColor);
    SoapColor map(Color color);
}
