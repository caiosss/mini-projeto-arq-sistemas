package com.example.mini_projeto_arq_sistemas.controller;

import com.example.mini_projeto_arq_sistemas.model.Aluno;
import com.example.mini_projeto_arq_sistemas.model.Disciplina;
import com.example.mini_projeto_arq_sistemas.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/disciplinas")
public class DisiciplinaController {

    @Autowired
    DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<Disciplina>> getDisciplinas() {
        return  ResponseEntity.ok(disciplinaService.getAllDisciplinas());
    }

    @PostMapping("/salvarDisciplinas")
    public  ResponseEntity<String> salvarDisciplina() {
        disciplinaService.salvarDisciplinas();
        return ResponseEntity.ok("Disciplinas salvas com sucesso!");
    }

    @PostMapping("/{nome}/{disciplina}")
    public ResponseEntity<Aluno> inscreverAlunoPorNomeDeDisciplinas(
            @PathVariable String nome,
            @PathVariable String disciplina) {
        Aluno aluno = disciplinaService.inscreverAluno(nome, disciplina);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{nome}/{disciplina}")
    public ResponseEntity<Aluno> deletarAlunoPorNomeDeDisciplina(
            @PathVariable String nome,
            @PathVariable String disciplina
    ) {
        Aluno aluno = disciplinaService.removerAluno(nome, disciplina);
        return ResponseEntity.ok(aluno);
    }
}
