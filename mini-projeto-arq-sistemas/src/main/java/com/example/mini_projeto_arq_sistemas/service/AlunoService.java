package com.example.mini_projeto_arq_sistemas.service;

import com.example.mini_projeto_arq_sistemas.model.Aluno;
import com.example.mini_projeto_arq_sistemas.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    AlunoRepository alunoRepository;

    private List<Aluno> getAlunos() {
        ResponseEntity<List<Aluno>> response = restTemplate.exchange(
                "https://rmi6vdpsq8.execute-api.us-east-2.amazonaws.com/msAluno",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Aluno>>() {}
        );
        return response.getBody();
    }

    private List<Aluno> alunosHistoria() {
        List<Aluno> alunos = getAlunos();
        List<Aluno> alunosHistoria = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if(aluno.getCurso().equals("História") && aluno.getModalidade().equals("Presencial")) {
                alunosHistoria.add(aluno);
            }
        }
        return alunosHistoria;
    }


    public List<Aluno> getAllAlunos() {
        return alunosHistoria();
    }

}
