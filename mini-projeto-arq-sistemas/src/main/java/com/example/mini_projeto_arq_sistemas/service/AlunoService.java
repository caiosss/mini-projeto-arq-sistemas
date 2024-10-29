package com.example.mini_projeto_arq_sistemas.service;

import com.example.mini_projeto_arq_sistemas.model.Aluno;
import com.example.mini_projeto_arq_sistemas.model.Biblioteca;
import com.example.mini_projeto_arq_sistemas.model.Disciplina;
import com.example.mini_projeto_arq_sistemas.repository.AlunoRepository;
import com.example.mini_projeto_arq_sistemas.repository.BibliotecaRepository;
import com.example.mini_projeto_arq_sistemas.repository.DisciplinaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class AlunoService extends BaseService<Aluno> {



    @Autowired
    AlunoRepository alunoRepository;

    @Override
    protected String getUrl() {
        return "https://rmi6vdpsq8.execute-api.us-east-2.amazonaws.com/msAluno";
    }

    @Override
    protected ParameterizedTypeReference<List<Aluno>> getResponseType() {
        return new ParameterizedTypeReference<List<Aluno>>() {};
    }

    private List<Aluno> getAlunos() {
        ResponseEntity<List<Aluno>> response = restTemplate.exchange(
                getUrl(),
                HttpMethod.GET,
                null,
                getResponseType()
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

    public Aluno getAlunoPorNome(String nome) {
        return alunoRepository.findByNome(nome);
    }

    public void salvarAlunosHistoria(){
        List<Aluno> alunos = alunoRepository.findAll();

        if(alunos.isEmpty()) {
            List<Aluno> a = alunosHistoria();
            alunoRepository.saveAll(a);
        }

        alunoRepository.saveAll(alunos);
    }

    public Aluno getAlunoPorId(Integer id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public List<Aluno> getAllAlunos() {
        List<Aluno> alunosHistoria = alunoRepository.findAll();

        if(alunosHistoria.isEmpty()) {
            List<Aluno> a = alunosHistoria();
            alunoRepository.saveAll(a);
            return a;
        }

        return alunosHistoria;
    }


}
