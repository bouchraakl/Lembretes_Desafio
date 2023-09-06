package com.desafio.lembretes;

import com.desafio.lembretes.controller.PessoaController;
import com.desafio.lembretes.dto.LembreteDTO;
import com.desafio.lembretes.dto.PessoaDTO;
import com.desafio.lembretes.entity.Pessoa;
import com.desafio.lembretes.repository.PessoaRepository;
import com.desafio.lembretes.service.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PessoasTeste {

    @Autowired
    private PessoaService pessoaService;

    @MockBean
    private PessoaRepository pessoaRepository;

    @Mock
    private PessoaController pessoaController;

    Pessoa pessoa = new Pessoa();
    List<PessoaDTO> pessoaDTOS = new ArrayList<>();
    PessoaDTO pessoaDTO = new PessoaDTO();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pessoa.setId(1L);
        pessoa.setNome("nome Sample");

        pessoaDTO.setId(1L);
        pessoaDTO.setNome("Exemplo de Nome");


        pessoaDTOS.add(pessoaDTO);

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(pessoaService.findAll()).thenReturn(pessoaDTOS);

        when(pessoaController.getPessoaById(1L)).thenReturn(pessoaDTO);
    }

    @Test
    void testFindPessoaById() {
        var pessoaDto = pessoaController.getPessoaById(1L);
        long id = pessoaDto.getId();
        System.out.println(id);
        Assertions.assertEquals(1L,id);
    }

    @Test
    void testGetAllPessoas() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(1L);
        pessoaDTO.setNome("nome");

        List<PessoaDTO> pessoaDTOS = new ArrayList<>();
        pessoaDTOS.add(pessoaDTO);

        when(pessoaController.getAllPessoas()).thenReturn(pessoaDTOS);

        List<PessoaDTO> allPessoas = pessoaController.getAllPessoas();

        assertThat(allPessoas).isNotNull();
        assertThat(allPessoas.size()).isEqualTo(1);
    }



    @Test
    void testCreatePessoa() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(1L);
        pessoaDTO.setNome("nome");

        ResponseEntity<String> responseEntity = ResponseEntity.ok("Pessoa criada com sucesso");
        when(pessoaController.cadastrarPessoa(pessoaDTO)).thenReturn(responseEntity);

        ResponseEntity<String> response = pessoaController.cadastrarPessoa(pessoaDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Pessoa criada com sucesso");
    }

    @Test
    void testUpdatePessoa() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(1L);
        pessoaDTO.setNome("Updated Nome");

        ResponseEntity<String> responseEntity = ResponseEntity.ok("Pessoa com título [Updated Pessoa] " +
                "atualizada com sucesso.");
        when(pessoaController.editarPessoa(pessoaDTO)).thenReturn(responseEntity);

        ResponseEntity<String> response = pessoaController.editarPessoa(pessoaDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Pessoa com título [Updated Pessoa] atualizada com sucesso.");
    }


    @Test
    void testDeletePessoa() {
        Long id = 1L;

        ResponseEntity<String> responseEntity = ResponseEntity.ok("Pessoa excluída com sucesso.");
        when(pessoaController.excluirPessoa(id)).thenReturn(responseEntity);

        ResponseEntity<String> response = pessoaController.excluirPessoa(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Pessoa excluída com sucesso.");
    }



}
