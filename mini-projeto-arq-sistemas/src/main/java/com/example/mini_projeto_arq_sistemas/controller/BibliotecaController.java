package com.example.mini_projeto_arq_sistemas.controller;

import com.example.mini_projeto_arq_sistemas.model.Biblioteca;
import com.example.mini_projeto_arq_sistemas.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    @Autowired
    BibliotecaService bibliotecaService;

    @GetMapping
    public ResponseEntity<List<Biblioteca>> getBiblioteca() {
        return ResponseEntity.ok(bibliotecaService.getAllBiblioteca());
    }

    @PostMapping("/salvarLivros")
    public ResponseEntity<String> salvarLivros() {
        bibliotecaService.salvarBiblioteca();
        return ResponseEntity.ok("Livros Salvos!");
    }

}
