package br.com.fiap.opengroup.controller;

import br.com.fiap.opengroup.dto.ControllerDTO;
import br.com.fiap.opengroup.dto.request.ItemRequest;
import br.com.fiap.opengroup.dto.response.ItemResponse;
import br.com.fiap.opengroup.entity.ItemRecomendado;
import br.com.fiap.opengroup.service.ItemRecomendadoService;
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
@RequestMapping(value = "/item")
public class ItemController implements ControllerDTO<ItemRequest, ItemResponse> {

    @Autowired
    private ItemRecomendadoService service;

    @GetMapping
    public ResponseEntity<Collection<ItemResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome
    ) {
        var item = ItemRecomendado.builder().nome(nome).build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<ItemRecomendado> example = Example.of(item, matcher);

        List<ItemRecomendado> itens = service.findAll(example);
        return ResponseEntity.ok(itens.stream().map(service::toResponse).toList());
    }


    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ItemResponse> findById(@PathVariable Long id) {
        var item = service.toResponse(service.findById(id));
        if (item == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(item);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<ItemResponse> save(@RequestBody @Valid ItemRequest r) {
        var saved = service.save(r);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created(uri).body(service.toResponse(saved));
    }
}
