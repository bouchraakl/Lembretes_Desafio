package com.desafio.lembretes.controller;

import com.desafio.lembretes.entity.Lembrete;
import com.desafio.lembretes.repository.LembreteRepository;
import com.desafio.lembretes.service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lembrete")
public class LembreteController {

    private final LembreteRepository lembreteRepository;
    private final LembreteService lembreteService;

    @Autowired
    public LembreteController(LembreteRepository lembreteRepository, LembreteService lembreteService) {
        this.lembreteRepository = lembreteRepository;
        this.lembreteService = lembreteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lembrete> getLembreteById(@PathVariable("id") Long id) {
        Optional<Lembrete> lembrete = lembreteRepository.findById(id);
        return lembrete.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Lembrete>> getAllLembrentes() {
        List<Lembrete> lembretes = lembreteRepository.findAll();
        return ResponseEntity.ok(lembretes);
    }

    @GetMapping("/all/{nome}")
    public ResponseEntity<List<Lembrete>> getAllLembretesByPessoa(@PathVariable("nome") String nome) {
        List<Lembrete> lembretes = lembreteRepository.findAllByPessoaNome(nome);
        return ResponseEntity.ok(lembretes);
    }

    @PostMapping
    public ResponseEntity<String> createLembrete(@RequestBody @Validated Lembrete lembrete) {
        try {
            lembreteService.createLembrete(lembrete);
            return ResponseEntity.ok("Lembrete criado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> editarLembrete(@RequestBody @Validated Lembrete lembrete) {
        try {
            lembreteService.editarLembrete(lembrete);
            return ResponseEntity.ok(String.format("Lembrete com título [%s] atualizado com sucesso.",
                    lembrete.getTitulo()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Erro: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Erro: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> excluirLembrete(@RequestParam("id") Long id) {
        try {
            lembreteService.excluirLembrete(id);
            return ResponseEntity.ok("Lembrete excluído com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().body("Erro: " + e.getMessage());
        }
    }
}
