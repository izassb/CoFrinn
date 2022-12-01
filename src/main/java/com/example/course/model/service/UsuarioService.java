package com.example.course.model.service;

import com.example.course.model.entities.Usuario;

public interface UsuarioService {
    Usuario autenticar(String email, String senha);
    Usuario cadastrar(Usuario usuario);
    void validarEmail(String email);
    Usuario obterPorId(Long id);
    Usuario atualizar(Usuario usuario);
}
