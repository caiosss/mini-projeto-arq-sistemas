package com.example.mini_projeto_arq_sistemas.controller;

import com.example.mini_projeto_arq_sistemas.model.Aluno;
import com.example.mini_projeto_arq_sistemas.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAlunos() {
        return ResponseEntity.ok(alunoService.getAllAlunos());
    }
}