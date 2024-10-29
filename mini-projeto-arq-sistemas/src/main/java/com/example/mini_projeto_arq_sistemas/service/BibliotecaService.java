package com.example.mini_projeto_arq_sistemas.service;

import com.example.mini_projeto_arq_sistemas.model.Aluno;
import com.example.mini_projeto_arq_sistemas.model.Biblioteca;
import com.example.mini_projeto_arq_sistemas.repository.AlunoRepository;
import com.example.mini_projeto_arq_sistemas.repository.BibliotecaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class BibliotecaService extends BaseService<Biblioteca> {

    @Autowired
    BibliotecaRepository bibliotecaRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Override
    protected String getUrl() {
        return "https://qiiw8bgxka.execute-api.us-east-2.amazonaws.com/acervo/biblioteca";
    }

    @Override
    protected ParameterizedTypeReference<List<Biblioteca>> getResponseType() {
        return new ParameterizedTypeReference<List<Biblioteca>>() {};
    }

    private List<Biblioteca> consumirBiblioteca() {
        ResponseEntity<List<Biblioteca>> response = restTemplate.exchange(
                getUrl(),
                HttpMethod.GET,
                null,
                getResponseType()
        );
        return response.getBody();
    }

    private List<Biblioteca> getAllBiblioteca() {
        List<Biblioteca> biblioteca = consumirBiblioteca();
        for (Biblioteca b : biblioteca) {
            b.setStatus("Disponível");
        }
        return biblioteca;
    }

    public List<Biblioteca> getBiblioteca() {
        List<Biblioteca> biblioteca = bibliotecaRepository.findAll();

        if (biblioteca.isEmpty()) {
            List<Biblioteca> b = getAllBiblioteca();
            bibliotecaRepository.saveAll(b);
            return b;
        }

        return biblioteca;
    }

    public void salvarBiblioteca() {
        bibliotecaRepository.saveAll(getAllBiblioteca());
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
            bibliotecaRepository.delete(biblioteca);

            return alunoRepository.save(aluno);
        }
        return null;
    }


}
