package com.example.myfinances.model.service;

import com.example.myfinances.model.entity.Lancamento;

import java.math.BigDecimal;
import java.util.List;

public interface LancamentoService {

    Lancamento salvar(Lancamento lancamento);

    Lancamento atualizar(Lancamento lancamento);

    void deletar(Lancamento lancamento);

    List<Lancamento> buscar(Lancamento lancamentofiltro);

    void validar(Lancamento lancamento);

    Lancamento obterPorId(Long id);
}