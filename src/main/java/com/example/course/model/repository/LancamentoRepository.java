package com.example.course.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.course.model.entities.Lancamento;
import com.example.course.model.enumeration.TipoLancamento;

import java.math.BigDecimal;


public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    @Query( value =
            "select sum(l.valor) from Lancamento l join l.usuario u "
                    + "where u.id = :idUsuario and l.tipo = :tipo"
    )
    BigDecimal obterSaldoPorTipoLancamentoEUsuario(
            @Param("idUsuario") Long idUsuario,
            @Param("tipo") TipoLancamento tipo);
}