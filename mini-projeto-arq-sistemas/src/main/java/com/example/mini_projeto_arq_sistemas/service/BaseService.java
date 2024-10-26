package com.example.mini_projeto_arq_sistemas.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseService<T> {


    RestTemplate restTemplate = new RestTemplate();

    protected abstract String getUrl();

    protected abstract ParameterizedTypeReference<List<T>> getResponseType();

    public List<T> getEntidades() {
        ResponseEntity<List<T>> response = restTemplate.exchange(
                getUrl(),
                HttpMethod.GET,
                null,
                getResponseType()
        );
        return response.getBody();
    }

}
