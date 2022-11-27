package com.example.myfinances.model.repository;

import com.example.myfinances.model.entity.Lancamento;
import com.example.myfinances.model.enumeration.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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