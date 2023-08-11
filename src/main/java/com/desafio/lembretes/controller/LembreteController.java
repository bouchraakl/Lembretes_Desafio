package com.desafio.lembretes.controller;

import com.desafio.lembretes.dto.LembreteDTO;
import com.desafio.lembretes.repository.LembreteRepository;
import com.desafio.lembretes.service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public LembreteDTO getLembreteById(@PathVariable("id") Long id) {
        return lembreteService.findById(id);
    }

    @GetMapping("/all")
    public List<LembreteDTO> getAllLembrentes() {
        return lembreteService.findAll();
    }

    @GetMapping("/all/{nome}")
    public List<LembreteDTO> getAllLembretesByPessoa(@PathVariable("nome") String nome) {
        List<LembreteDTO> lembretes = lembreteService.findAllByPessoaNome(nome);
        return ResponseEntity.ok(lembretes).getBody();
    }

    @PostMapping
    public ResponseEntity<String> createLembrete(@RequestBody @Validated LembreteDTO lembrete) {
        try {
            lembreteService.createLembrete(lembrete);
            return ResponseEntity.ok("Lembrete criado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> editarLembrete(@RequestBody @Validated LembreteDTO lembrete) {
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
