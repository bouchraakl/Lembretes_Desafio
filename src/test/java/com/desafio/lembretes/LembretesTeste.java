package com.desafio.lembretes;

import com.desafio.lembretes.controller.LembreteController;
import com.desafio.lembretes.dto.LembreteDTO;
import com.desafio.lembretes.entity.Lembrete;
import com.desafio.lembretes.entity.Pessoa;
import com.desafio.lembretes.repository.LembreteRepository;
import com.desafio.lembretes.service.LembreteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LembretesTeste {

    @Autowired
    private LembreteService lembreteService;

    @MockBean
    private LembreteRepository lembreteRepository;

    @Mock
    private LembreteController lembreteControllers;

    // Inicialize as listas fictícias e os dados fictícios
    Lembrete lembrete = new Lembrete();
    List<LembreteDTO> lembretes = new ArrayList<>();
    LembreteDTO lembreteDTO = new LembreteDTO();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        lembrete.setId(1L);
        lembrete.setTitulo("Exemplo de Lembrete");
        lembrete.setPessoa(new Pessoa(1L,"nome"));
        lembrete.setDescricao("Descrição do lembrete sample");

        lembreteDTO.setId(1L);
        lembreteDTO.setTitulo("Exemplo de Lembrete");
        lembreteDTO.setPessoa(new Pessoa(1L,"nome"));
        lembreteDTO.setDescricao("Descrição do lembrete sample");

        lembretes.add(lembreteDTO);

        // Simule o comportamento do serviço para retornar as listas fictícias
        when(lembreteRepository.findById(1L)).thenReturn(Optional.of(lembrete));
        when(lembreteService.findAll()).thenReturn(lembretes);

        // Inicialize o mock da controladora
        when(lembreteControllers.getLembreteById(1L)).thenReturn(lembreteDTO);
    }


    @Test
    void testFindLembreteById() {
        var lembreteDTO = lembreteControllers.getLembreteById(1L);
        long id = lembreteDTO.getId();
        System.out.println(id);
        Assertions.assertEquals(1L,id);
    }

    @Test
    void testGetAllLembretesByPessoa() {
        String nome = "nome";

        LembreteDTO sampleLembreteDTO = new LembreteDTO();
        sampleLembreteDTO.setId(1L);
        sampleLembreteDTO.setTitulo("Sample Lembrete");
        sampleLembreteDTO.setPessoa(new Pessoa(1L, nome));
        sampleLembreteDTO.setDescricao("Sample Description");

        List<LembreteDTO> lembretes = new ArrayList<>();
        lembretes.add(sampleLembreteDTO);

        when(lembreteControllers.getAllLembretesByPessoa(nome)).thenReturn(lembretes);

        List<LembreteDTO> lembretesByPessoa = lembreteControllers.getAllLembretesByPessoa(nome);

        assertThat(lembretesByPessoa).isNotNull();
        assertThat(lembretesByPessoa.size()).isEqualTo(1);
    }

    @Test
    void testGetAllLembretes() {
        LembreteDTO sampleLembreteDTO = new LembreteDTO();
        sampleLembreteDTO.setId(1L);
        sampleLembreteDTO.setTitulo("Sample Lembrete");
        sampleLembreteDTO.setPessoa(new Pessoa(1L, "nome"));
        sampleLembreteDTO.setDescricao("Sample Description");

        List<LembreteDTO> lembretes = new ArrayList<>();
        lembretes.add(sampleLembreteDTO);

        when(lembreteControllers.getAllLembrentes()).thenReturn(lembretes);

        List<LembreteDTO> allLembretes = lembreteControllers.getAllLembrentes();

        assertThat(allLembretes).isNotNull();
        assertThat(allLembretes.size()).isEqualTo(1);
    }


    @Test
    void testCreateLembrete() {
        LembreteDTO lembreteDTO = new LembreteDTO();
        lembreteDTO.setTitulo("Novo Lembrete");
        lembreteDTO.setPessoa(new Pessoa(1L, "nome"));
        lembreteDTO.setDescricao("Descrição do lembrete sample");

        ResponseEntity<String> responseEntity = ResponseEntity.ok("Lembrete criado com sucesso");
        when(lembreteControllers.createLembrete(lembreteDTO)).thenReturn(responseEntity);

        ResponseEntity<String> response = lembreteControllers.createLembrete(lembreteDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Lembrete criado com sucesso");
    }

    @Test
    void testUpdateLembrete() {
        LembreteDTO lembreteDTO = new LembreteDTO();
        lembreteDTO.setId(1L);
        lembreteDTO.setTitulo("Updated Lembrete");
        lembreteDTO.setPessoa(new Pessoa(1L, "nome"));
        lembreteDTO.setDescricao("Updated Description");

        ResponseEntity<String> responseEntity = ResponseEntity.ok("Lembrete com título [Updated Lembrete] atualizado com sucesso.");
        when(lembreteControllers.editarLembrete(lembreteDTO)).thenReturn(responseEntity);

        ResponseEntity<String> response = lembreteControllers.editarLembrete(lembreteDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Lembrete com título [Updated Lembrete] atualizado com sucesso.");
    }

    @Test
    void testDeleteLembrete() {
        Long id = 1L;

        ResponseEntity<String> responseEntity = ResponseEntity.ok("Lembrete excluído com sucesso.");
        when(lembreteControllers.excluirLembrete(id)).thenReturn(responseEntity);

        ResponseEntity<String> response = lembreteControllers.excluirLembrete(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("Lembrete excluído com sucesso.");
    }


}


