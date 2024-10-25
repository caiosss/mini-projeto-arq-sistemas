package com.example.mini_projeto_arq_sistemas.repository;

import com.example.mini_projeto_arq_sistemas.model.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Integer> {
}
