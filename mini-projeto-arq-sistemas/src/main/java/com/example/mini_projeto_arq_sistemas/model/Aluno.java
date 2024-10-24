package com.example.mini_projeto_arq_sistemas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
