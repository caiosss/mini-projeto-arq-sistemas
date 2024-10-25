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
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "titulo")
    String titulo;
    @Column(name = "autor")
    String autor;
    @Column(name = "ano")
    Integer ano;
    @Column(name = "status")
    String status;

}
