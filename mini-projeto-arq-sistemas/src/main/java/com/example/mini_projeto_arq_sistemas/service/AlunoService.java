package com.example.mini_projeto_arq_sistemas.service;

import com.example.mini_projeto_arq_sistemas.model.Aluno;
import com.example.mini_projeto_arq_sistemas.model.Biblioteca;
import com.example.mini_projeto_arq_sistemas.model.Disciplina;
import com.example.mini_projeto_arq_sistemas.repository.AlunoRepository;
import com.example.mini_projeto_arq_sistemas.repository.BibliotecaRepository;
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
public class AlunoService {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    BibliotecaRepository bibliotecaRepository;

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

    public Aluno getAlunoPorNome(String nome) {
        return alunoRepository.findByNome(nome);
    }

    public void salvarAlunosHistoria(){
        List<Aluno> alunos = alunosHistoria();
        alunoRepository.saveAll(alunos);
    }


    public Aluno getAlunoPorId(Integer id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public List<Aluno> getAllAlunos() {
        return alunosHistoria();
    }

    public Aluno inscreverAlunoPorNomeDeDisciplinas(String nome, String disciplinaNome) {
        Aluno aluno = alunoRepository.findByNome(nome);

        if(aluno.getStatus().equals("Ativo")){

            Disciplina disciplina = disciplinaRepository.findByNome(disciplinaNome);

            aluno.getDisciplinas().add(disciplina);

            return alunoRepository.save(aluno);
        }
        return null;
    }

    public Aluno removerAlunoDisciplina(String nome, String disciplinaNome) {
        Aluno aluno = alunoRepository.findByNome(nome);

        Disciplina disciplina = disciplinaRepository.findByNome(disciplinaNome);
        aluno.getDisciplinas().remove(disciplina);

        return alunoRepository.save(aluno);
    }

    public Aluno reservarLivro(String nome, String titulo) {
        Aluno aluno = alunoRepository.findByNome(nome);

        if(aluno.getStatus().equals("Ativo")) {

            Biblioteca biblioteca = bibliotecaRepository.findByTitulo(titulo);

            if (biblioteca.getStatus().equals("Reservado")) {
                return null;
            }

            biblioteca.setStatus("Reservado");
            bibliotecaRepository.save(biblioteca);

            aluno.getBiblioteca().add(biblioteca);

            return alunoRepository.save(aluno);
        }
        return null;
    }

    public Aluno cancelarReserva(String nome, String titulo) {
        Aluno aluno = alunoRepository.findByNome(nome);

        if(aluno.getStatus().equals("Ativo")) {
            Biblioteca biblioteca = bibliotecaRepository.findByTitulo(titulo);

            aluno.getBiblioteca().remove(biblioteca);

            biblioteca.setStatus("Disponível");
            bibliotecaRepository.save(biblioteca);

            return alunoRepository.save(aluno);
        }
        return null;
    }

}
