package com.example.myfinances.api.controller;

import java.util.List;

import com.example.myfinances.api.dto.UsuarioDTO;
import com.example.myfinances.api.dto.UsuarioLoginDTO;
import com.example.myfinances.model.entity.Usuario;
import com.example.myfinances.model.exception.BusinessRuleException;
import com.example.myfinances.model.exception.ErroAutentificacaoException;
import com.example.myfinances.model.repository.UsuarioRepository;
import com.example.myfinances.model.service.LancamentoService;
import com.example.myfinances.model.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final LancamentoService lancamentoService;
    private final UsuarioRepository repository;

    @PostMapping
    public ResponseEntity save(@RequestBody UsuarioDTO dto){
        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();

        try {
            var usuarioSalvo = service.cadastrar(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        }
        catch(BusinessRuleException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioLoginDTO dto) {

        try {
            Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
            return ResponseEntity.ok(usuarioAutenticado);
        }
        catch(ErroAutentificacaoException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> all() {

        List<Usuario> users = repository.findAll();

        return ResponseEntity.ok(users);
    }

}