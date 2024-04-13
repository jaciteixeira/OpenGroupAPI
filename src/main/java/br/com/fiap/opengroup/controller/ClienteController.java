package br.com.fiap.opengroup.controller;

import br.com.fiap.opengroup.dto.ControllerDTO;
import br.com.fiap.opengroup.dto.request.ClienteRequest;
import br.com.fiap.opengroup.dto.response.ClienteResponse;
import br.com.fiap.opengroup.entity.Cliente;
import br.com.fiap.opengroup.service.ClienteService;
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
@RequestMapping(value = "/cliente")
public class ClienteController implements ControllerDTO<ClienteRequest, ClienteResponse> {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<Collection<ClienteResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "cpf", required = false) String cpf,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "genero", required = false) String genero,
            @RequestParam(name = "profissao", required = false) String profissao
    ){
        var cliente = Cliente.builder()
                .nome(nome)
                .cpf(cpf)
                .email(email)
                .genero(genero)
                .profissao(profissao)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Cliente> example = Example.of(cliente, matcher);

        // Realizar a consulta utilizando o exemplo
        List<Cliente> clientes = service.findAll(example);
        return ResponseEntity.ok(clientes.stream().map(service::toResponse).toList());
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ClienteResponse> findById(@PathVariable Long id) {
        ClienteResponse cliente = service.toResponse(service.findById(id));
        if (cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cliente);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<ClienteResponse> save(@RequestBody @Valid ClienteRequest r) {
        var saved = service.save(r);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created(uri).body(service.toResponse(saved));
    }
}
