package com.desafio.lembretes.controller;

import com.desafio.lembretes.entity.Pessoa;
import com.desafio.lembretes.repository.PessoaRepository;
import com.desafio.lembretes.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    private final PessoaRepository pessoaRepository;
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaRepository pessoaRepository, PessoaService pessoaService) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaService = pessoaService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPessoas() {
        return ResponseEntity.ok(pessoaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPessoaById(@PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isEmpty()) {
            return ResponseEntity.badRequest().body("ID não encontrado!");
        }
        return ResponseEntity.ok(pessoa.get());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarPessoa(@RequestBody @Validated Pessoa pessoa) {
        try {
            final Pessoa pessoa1 = this.pessoaService.cadastrarPessoa(pessoa);
            return ResponseEntity.ok(String.format("Pessoa [ %s ] cadastrada com sucesso!", pessoa1.getNome()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editarPessoa(@RequestBody @Validated final Pessoa pessoa) {
        try {
            final Pessoa pessoaEdited = this.pessoaService.editarPessoa(pessoa);
            return ResponseEntity.ok(String.format("Pessoa [ %s ] atualizada com sucesso", pessoaEdited.getNome()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> excluirPessoa(@RequestParam("id") final Long id) {
        try {
            pessoaService.excluirPessoa(id);
            return ResponseEntity.ok("Registro excluido com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
