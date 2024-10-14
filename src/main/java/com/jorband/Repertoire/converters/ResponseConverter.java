package com.jorband.Repertoire.converters;

import com.jorband.Repertoire.dtos.response.Response;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ResponseConverter implements Converter<Object, Response> {
    @Override
    public Response convert(Object source) {
        return Response.builder()
                .statusCode(0)
                .success(true)
                .message("Success Operation")
                .data(source)
                .build();
    }
}
