package com.example.mini_projeto_arq_sistemas.controller;

import com.example.mini_projeto_arq_sistemas.model.Aluno;
import com.example.mini_projeto_arq_sistemas.model.Biblioteca;
import com.example.mini_projeto_arq_sistemas.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    @Autowired
    BibliotecaService bibliotecaService;

    @GetMapping
    public ResponseEntity<List<Biblioteca>> getBiblioteca() {
        return ResponseEntity.ok(bibliotecaService.getBiblioteca());
    }

    @PostMapping("/salvarLivros")
    public ResponseEntity<String> salvarLivros() {
        bibliotecaService.salvarBiblioteca();
        return ResponseEntity.ok("Livros Salvos!");
    }

    @PostMapping("/{nome}/reservarLivro/{livro}")
    public ResponseEntity<Aluno> reservarLivros(
            @PathVariable String nome,
            @PathVariable String livro
    ) {
        Aluno aluno = bibliotecaService.reservarLivro(nome, livro);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{nome}/devolverLivro/{livro}")
    public ResponseEntity<Aluno> devolverLivro(
            @PathVariable String nome,
            @PathVariable String livro
    ) {
        Aluno aluno = bibliotecaService.cancelarReserva(nome, livro);
        return ResponseEntity.ok(aluno);
    }

}
