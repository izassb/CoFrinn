package com.example.course.api.controller;

import java.math.BigDecimal;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.api.dto.UsuarioDTO;
import com.example.course.api.dto.UsuarioLoginDTO;
import com.example.course.model.entities.Usuario;
import com.example.course.model.exception.BusinessRuleException;
import com.example.course.model.exception.ErroAutentificacaoException;
import com.example.course.model.repository.UsuarioRepository;
import com.example.course.model.service.LancamentoService;
import com.example.course.model.service.UsuarioService;

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

    @GetMapping("/{id}/saldo")
    public ResponseEntity obterSaldo(@PathVariable Long id) {
        try {
            service.obterPorId(id);
        }catch(BusinessRuleException ex) {
            return ResponseEntity.notFound().build();
        }

        BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/{id}/despesa")
    public ResponseEntity obterDespesa(@PathVariable Long id) {
        try {
            service.obterPorId(id);
        }catch(BusinessRuleException ex) {
            return ResponseEntity.notFound().build();
        }

        BigDecimal despesa = lancamentoService.obterSaldoDespesaPorUsuario(id);
        return ResponseEntity.ok(despesa);
    }

    @GetMapping("/{id}/receita")
    public ResponseEntity obterReceita(@PathVariable Long id) {
        try {
            service.obterPorId(id);
        }catch(BusinessRuleException ex) {
            return ResponseEntity.notFound().build();
        }

        BigDecimal receita = lancamentoService.obterSaldoReceitaPorUsuario(id);
        return ResponseEntity.ok(receita);
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

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.obterPorId(id));
        }catch(BusinessRuleException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        try {
            service.obterPorId(id);
            Usuario atualizado = toEntity(dto);
            atualizado.setId(id);
            return ResponseEntity.ok(service.atualizar(atualizado));
        }catch(BusinessRuleException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    public Usuario toEntity(UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        return usuario;
    }

}