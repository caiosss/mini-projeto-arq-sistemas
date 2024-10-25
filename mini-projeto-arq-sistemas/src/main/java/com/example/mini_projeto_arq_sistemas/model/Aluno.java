package com.example.mini_projeto_arq_sistemas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Aluno {

    @Id
    @Column(name = "id")
    Integer id;
    @Column(name = "nome")
    String nome;
    @Column(name = "curso")
    String curso;
    @Column(name = "modalidade")
    String modalidade;
    @Column(name = "status")
    String status;
    @ManyToMany
    @JoinTable(
            name = "aluno_disciplina",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    List<Disciplina> disciplinas;
    @ManyToMany
    @JoinTable(
            name = "aluno_livro",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "biblioteca_id")
    )
    List<Biblioteca> biblioteca;
}
