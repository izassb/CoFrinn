package com.example.course.model.service.implementation;

import java.util.Objects;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.course.model.entities.Usuario;
import com.example.course.model.exception.BusinessRuleException;
import com.example.course.model.exception.ErroAutentificacaoException;
import com.example.course.model.repository.UsuarioRepository;
import com.example.course.model.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {

        Optional<Usuario> usuario = repository.findByEmail(email);

        if(!usuario.isPresent())
            throw new ErroAutentificacaoException("Usuário não encontrado para o email informado");

        if(!usuario.get().getSenha().equals(senha))
            throw new ErroAutentificacaoException("Senha incorreta");

        return usuario.get();
    }

    @Override
    @Transactional
    public Usuario cadastrar(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean exist = repository.existsByEmail(email);
        if(exist) throw new BusinessRuleException("Já existe um usuario cadastrado com esse email");
    }

    @Override
    public Usuario obterPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Usuário não encontrado"));
    }

    @Override
    @Transactional
    public Usuario atualizar(Usuario usuario) {
        Objects.requireNonNull(usuario.getId());
        return repository.save(usuario);
    }
}
