package com.generation.blogpessoal.repository;


import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start(){
        usuarioRepository.deleteAll();
        usuarioRepository.save(new Usuario(0L, "Joao da Silva", "joao@email.com","12345678", "https://globo.com"));
        usuarioRepository.save(new Usuario(0L, "Jose da Silva", "jose@email.com","12345678", "https://globo.com"));
        usuarioRepository.save(new Usuario(0L, "Joaquim da Silva", "joaquim@email.com","12345678", "https://globo.com"));
        usuarioRepository.save(new Usuario(0L, "Maria Antunes", "Maria@email.com","12345678", "https://globo.com"));
    }

    @Test
    @DisplayName("Retorna 1 usuário")
    public void deveRetornarUmUsuario(){
        Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com");
        assertTrue(usuario.get().getUsuario().equals("joao@email.com"));
    }

    @Test
    @DisplayName("deveRetornarTresUsuarios")
    public void deveRetornarTresUsuarios(){
        List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
        assertEquals(3, listaDeUsuarios.size());
        assertTrue(listaDeUsuarios.get(0).getNome().equals("Joao da Silva"));
        assertTrue(listaDeUsuarios.get(1).getNome().equals("Jose da Silva"));
        assertTrue(listaDeUsuarios.get(2).getNome().equals("Joaquim da Silva"));
    }

    @AfterAll
    public void end(){
        usuarioRepository.deleteAll();
    }
}

