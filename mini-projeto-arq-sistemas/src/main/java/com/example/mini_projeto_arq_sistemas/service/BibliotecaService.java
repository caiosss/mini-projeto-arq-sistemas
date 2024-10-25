package com.example.mini_projeto_arq_sistemas.service;

import com.example.mini_projeto_arq_sistemas.model.Biblioteca;
import com.example.mini_projeto_arq_sistemas.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BibliotecaService {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    BibliotecaRepository bibliotecaRepository;

    private List<Biblioteca> getBiblioteca() {
        ResponseEntity<List<Biblioteca>> response = restTemplate.exchange(
                "https://qiiw8bgxka.execute-api.us-east-2.amazonaws.com/acervo/biblioteca",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Biblioteca>>() {}
        );

        return response.getBody();
    }

    public List<Biblioteca> getAllBiblioteca() {
        List<Biblioteca> biblioteca = getBiblioteca();
        for (Biblioteca b : biblioteca) {
            b.setStatus("Disponível");
        }
        return biblioteca;
    }

    public void salvarBiblioteca() {
        bibliotecaRepository.saveAll(getAllBiblioteca());
    }

//    private Biblioteca getBibliotecaByTitulo(String titulo) {
//        return bibliotecaRepository.findByTitulo(titulo);
//    }
//
//    public void alterarStatus(String titulo) {
//        Biblioteca biblioteca = getBibliotecaByTitulo(titulo);
//
//        biblioteca.setStatus("Reservado");
//        bibliotecaRepository.save(biblioteca);
//    }
}
