package br.com.fiap.opengroup.controller;

import br.com.fiap.opengroup.dto.ControllerDTO;
import br.com.fiap.opengroup.dto.request.ClienteRequest;
import br.com.fiap.opengroup.dto.response.ClienteResponse;
import br.com.fiap.opengroup.entity.Cliente;
import br.com.fiap.opengroup.entity.TipoEmpresa;
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
public class ClienteController implements ControllerDTO<ClienteRequest, ClienteResponse> {

    @Autowired
    private DadosClienteService service;

    @GetMapping
    public ResponseEntity<Collection<ClienteResponse>> findAll(
            @RequestParam(name = "segmento", required = false) String segmento,
            @RequestParam(name = "tipo", required = false) String tipo,
            @RequestParam(name = "nome", required = false) String nome

    ){
        TipoEmpresa tipoEnum = null;
        if (tipo != null) {
            try {
                tipoEnum = TipoEmpresa.valueOf(tipo.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().build();
            }
        }
        var cliente = Cliente.builder()
                .nome(nome)
                .segmento(segmento)
                .tipo(tipoEnum != null ? TipoEmpresa.valueOf(tipoEnum.getSigla()) : null)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Cliente> example = Example.of(cliente, matcher);

        List<Cliente> dadosClientes = service.findAll(example);
        return ResponseEntity.ok(dadosClientes.stream().map(service::toResponse).toList());
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ClienteResponse> findById(@PathVariable Long id) {
        ClienteResponse dados = service.toResponse(service.findById(id));
        if (dados == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dados);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<ClienteResponse> save(@RequestBody @Valid ClienteRequest r) {
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
