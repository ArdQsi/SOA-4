package com.example.demography.controllers;


import com.example.demography.config.JNDIConfig;
import com.example.demography.dto.CountMessageDto;
import com.example.demography.dto.PercentageMassageDto;
import com.example.demography.mapper.ColorMapper;
import com.example.demography.mapper.NationalityMapper;
import com.example.demography.model.Color;
import com.example.demography.model.Nationality;

import com.example.demography.service.PersonService;
import https.example_com.please.GetCountRequest;
import https.example_com.please.GetCountResponse;
import https.example_com.please.GetPercentageRequest;
import https.example_com.please.GetPercentageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ControllerSOAP {
    PersonService personService = JNDIConfig.getPersonService();

    private static final String NAMESPACE_URI = "https://example.com/please";


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPercentageRequest")
    @ResponsePayload
    public GetPercentageResponse getPercentage(@RequestPayload GetPercentageRequest request) {
        System.out.println(ColorMapper.MAPPER.map(request.getEyeColor()));
        Color color = ColorMapper.MAPPER.map(request.getEyeColor());
        GetPercentageResponse response = new GetPercentageResponse();
        response.setPercentage(personService.getPercentageEyeColor(color));
        return response;
//        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountRequest")
    @ResponsePayload
    public GetCountResponse getCount(@RequestPayload GetCountRequest request) {
        System.out.println(ColorMapper.MAPPER.map(request.getEyeColor())
                + " " + NationalityMapper.MAPPER.map(request.getNationality()));
        Color color = ColorMapper.MAPPER.map(request.getEyeColor());
        Nationality nationality = NationalityMapper.MAPPER.map(request.getNationality());
        GetCountResponse response = new GetCountResponse();
        response.setCount(personService.getCountByEyeColorAndNationality(color,nationality));
        return response;
//        return null;
    }
}