package com.example.myfinances.model.service;

import com.example.myfinances.model.entity.Usuario;

public interface UsuarioService {
    Usuario autenticar(String email, String senha);
    Usuario cadastrar(Usuario usuario);
    void validarEmail(String email);
    Usuario obterPorId(Long id);
}
