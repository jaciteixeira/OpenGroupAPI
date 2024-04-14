package br.com.fiap.opengroup.controller;

import br.com.fiap.opengroup.dto.ControllerDTO;
import br.com.fiap.opengroup.dto.request.DadosRequest;
import br.com.fiap.opengroup.dto.response.DadosResponse;
import br.com.fiap.opengroup.entity.DadosCliente;
import br.com.fiap.opengroup.service.DadosClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/dados")
public class DadosController implements ControllerDTO<DadosRequest, DadosResponse> {

    @Autowired
    private DadosClienteService service;

    @GetMapping
    public ResponseEntity<Collection<DadosResponse>> findAll(
            @RequestParam(name = "segmento", required = false) String segmento,
            @RequestParam(name = "tipo", required = false) String tipo,
            @RequestParam(name = "nome", required = false) String nome

    ){
        var cliente = DadosCliente.builder()
                .nome(nome)
                .segmento(segmento)
                .tipo(tipo)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<DadosCliente> example = Example.of(cliente, matcher);

        List<DadosCliente> dadosClientes = service.findAll(example);
        return ResponseEntity.ok(dadosClientes.stream().map(service::toResponse).toList());
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<DadosResponse> findById(@PathVariable Long id) {
        DadosResponse dados = service.toResponse(service.findById(id));
        if (dados == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dados);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<DadosResponse> save(@RequestBody @Valid DadosRequest r) {
        var saved = service.save(r);
        if (saved == null) return ResponseEntity.badRequest().build();

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created(uri).body(service.toResponse(saved));
    }
}
