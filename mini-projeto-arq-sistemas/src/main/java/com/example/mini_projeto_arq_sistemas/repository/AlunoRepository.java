package com.example.mini_projeto_arq_sistemas.repository;

import com.example.mini_projeto_arq_sistemas.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    Aluno findByNome(String nome);

}
