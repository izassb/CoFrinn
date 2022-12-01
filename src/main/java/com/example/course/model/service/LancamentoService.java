package com.example.course.model.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.course.model.entities.Lancamento;

public interface LancamentoService {

    Lancamento salvar(Lancamento lancamento);

    Lancamento atualizar(Lancamento lancamento);

    void deletar(Lancamento lancamento);

    List<Lancamento> buscar(Lancamento lancamentofiltro);

    void validar(Lancamento lancamento);

    Lancamento obterPorId(Long id);

    BigDecimal obterSaldoPorUsuario(Long id);

    BigDecimal obterSaldoDespesaPorUsuario(Long id);

    BigDecimal obterSaldoReceitaPorUsuario(Long id);
}