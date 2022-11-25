package com.example.myfinances.api.controller;

import java.util.List;

import com.example.myfinances.api.dto.LancamentoDTO;
import com.example.myfinances.model.entity.Lancamento;
import com.example.myfinances.model.enumeration.TipoLancamento;
import com.example.myfinances.model.exception.BusinessRuleException;
import com.example.myfinances.model.repository.LancamentoRepository;
import com.example.myfinances.model.service.LancamentoService;
import com.example.myfinances.model.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/lancamentos")
@RequiredArgsConstructor
public class LancamentoController {

    private final LancamentoService service;
    private final UsuarioService usuarioService;
    private final LancamentoRepository repository;

    @GetMapping("/{id]")
    public ResponseEntity get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(toModel(service.obterPorId(id)));
        }catch(BusinessRuleException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity buscar(
            @RequestParam(value = "usuario", required = true) Long idUsuario,
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "tipo", required = false) String tipo
    ) {

        try {
            if(tipo != null && !tipo.equals( TipoLancamento.DESPESA.toString() )
                    && !tipo.equals( TipoLancamento.RECEITA.toString() )) {
                throw new BusinessRuleException("Tipo inválido");
            };

            LancamentoDTO filtro = new LancamentoDTO();
            filtro.setUsuario(idUsuario);
            filtro.setDescricao(descricao);
            filtro.setTipo(tipo);

            Lancamento entity = toEntity(filtro);
            List<Lancamento> lancamentos = service.buscar(entity);
            return ResponseEntity.ok(lancamentos);

        } catch(BusinessRuleException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody LancamentoDTO dto) {
        try {
            Lancamento entidade = toEntity(dto);
            entidade = service.salvar(entidade);
            return new ResponseEntity<>(entidade, HttpStatus.CREATED);
        }catch(BusinessRuleException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody LancamentoDTO dto) {
        try {
            service.obterPorId(id);
            Lancamento atualizado = toEntity(dto);
            atualizado.setId(id);
            return ResponseEntity.ok(service.atualizar(atualizado));
        }catch(BusinessRuleException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        try {
            service.deletar(service.obterPorId(id));
            return ResponseEntity.noContent().build();
        }catch(BusinessRuleException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    public LancamentoDTO toModel(Lancamento entity) {
        return LancamentoDTO.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .valor(entity.getValor())
                .tipo(entity.getTipo().name())
                .usuario(entity.getUsuario().getId())
                .build();
    }

    public Lancamento toEntity(LancamentoDTO dto) {

        Lancamento lancamento = new Lancamento();
        lancamento.setId(dto.getId());
        lancamento.setDescricao(dto.getDescricao());
        lancamento.setValor(dto.getValor());

        if(dto.getUsuario() != null)
            lancamento.setUsuario(usuarioService.obterPorId(dto.getUsuario()));

        if(dto.getTipo() != null)
            lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));

        return lancamento;
    }

    public ResponseEntity<List<Lancamento>> all() {

        List<Lancamento> lancamentos = repository.findAll();

        return ResponseEntity.ok(lancamentos);
    }
}