package com.example.mini_projeto_arq_sistemas.service;

import com.example.mini_projeto_arq_sistemas.model.Disciplina;
import com.example.mini_projeto_arq_sistemas.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisciplinaService {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    DisciplinaRepository disciplinaRepository;

    private List<Disciplina> getDisciplinas() {
        ResponseEntity<List<Disciplina>> response = restTemplate.exchange(
                "https://sswfuybfs8.execute-api.us-east-2.amazonaws.com/disciplinaServico/msDisciplina",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Disciplina>>() {}
        );
        return response.getBody();
    }

    private List<Disciplina> filtroHistoria() {
        List<Disciplina> disciplinas = getDisciplinas();
        List<Disciplina> filtroHistoria = new ArrayList<>();
        for (Disciplina disciplina : disciplinas) {
            if(disciplina.getCurso().equals("História")) {
                filtroHistoria.add(disciplina);
            }
        }
        return filtroHistoria;
    }

    public void salvarDisciplinas() {
        List<Disciplina> disciplinas = filtroHistoria();
        disciplinaRepository.saveAll(disciplinas);
    }

    public List<Disciplina> getAllDisciplinas() {
        return filtroHistoria();
    }

}
