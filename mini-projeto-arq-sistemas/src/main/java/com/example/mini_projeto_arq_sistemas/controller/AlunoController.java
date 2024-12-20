package com.example.mini_projeto_arq_sistemas.controller;

import com.example.mini_projeto_arq_sistemas.model.Aluno;
import com.example.mini_projeto_arq_sistemas.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAlunos() {
        return ResponseEntity.ok(alunoService.getAllAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoId(@PathVariable Integer id) {
        return ResponseEntity.ok(alunoService.getAlunoPorId(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Aluno> getAlunoNome(@PathVariable String nome) {
        return ResponseEntity.ok(alunoService.getAlunoPorNome(nome));
    }

    @PostMapping("/salvarAlunos")
    public ResponseEntity<String> salvarAluno(){
        alunoService.salvarAlunosHistoria();
        return ResponseEntity.ok("{\\\"message\\\": \\\"Alunos salvos com sucesso!\\\"}");
    }

}
