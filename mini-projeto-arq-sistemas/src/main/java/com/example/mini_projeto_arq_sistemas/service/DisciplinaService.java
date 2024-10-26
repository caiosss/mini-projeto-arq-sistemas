package com.example.mini_projeto_arq_sistemas.service;

import com.example.mini_projeto_arq_sistemas.model.Aluno;
import com.example.mini_projeto_arq_sistemas.model.Disciplina;
import com.example.mini_projeto_arq_sistemas.repository.AlunoRepository;
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
public class DisciplinaService extends BaseService<Disciplina> {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Override
    protected String getUrl() {
        return "https://sswfuybfs8.execute-api.us-east-2.amazonaws.com/disciplinaServico/msDisciplina";
    }

    @Override
    protected ParameterizedTypeReference<List<Disciplina>> getResponseType() {
        return new ParameterizedTypeReference<List<Disciplina>>() {};
    }

    private List<Disciplina> getDisciplinas() {
        ResponseEntity<List<Disciplina>> response = restTemplate.exchange(
                getUrl(),
                HttpMethod.GET,
                null,
                getResponseType()
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

    public Aluno inscreverAluno(String nome, String disciplinaNome) {
        Aluno aluno = alunoRepository.findByNome(nome);

        if(aluno.getStatus().equals("Ativo")){

            Disciplina disciplina = disciplinaRepository.findByNome(disciplinaNome);

            if(aluno.getDisciplinas().contains(disciplina)){
                return alunoRepository.save(aluno);
            }

            aluno.getDisciplinas().add(disciplina);

            return alunoRepository.save(aluno);
        }
        return null;
    }

    public Aluno removerAluno(String nome, String disciplinaNome) {
        Aluno aluno = alunoRepository.findByNome(nome);

        Disciplina disciplina = disciplinaRepository.findByNome(disciplinaNome);

        aluno.getDisciplinas().remove(disciplina);
        disciplinaRepository.delete(disciplina);

        return alunoRepository.save(aluno);
    }


}
