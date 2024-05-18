package br.com.fiap.opengroup.controller;

import br.com.fiap.opengroup.dto.ControllerDTO;
import br.com.fiap.opengroup.dto.request.UsuarioLoginRequest;
import br.com.fiap.opengroup.dto.request.UsuarioRequest;
import br.com.fiap.opengroup.dto.response.UsuarioResponse;
import br.com.fiap.opengroup.entity.Usuario;
import br.com.fiap.opengroup.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController implements ControllerDTO<UsuarioRequest, UsuarioResponse> {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<Collection<UsuarioResponse>> findAll(
            @RequestParam(name = "identificacao", required = false) String identificacao
    ) {
        var item = Usuario.builder()
                .identificacao(identificacao)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withMatcher("identificacao", match -> match.contains());

        Example<Usuario> example = Example.of(item, matcher);

        List<Usuario> itens = service.findAll(example);
        return ResponseEntity.ok(itens.stream().map(service::toResponse).toList());
    }


    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        var item = service.toResponse(service.findById(id));
        if (item == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(item);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<UsuarioResponse> save(@RequestBody @Valid UsuarioRequest r) {
        System.out.println(r);
        var saved = service.save(r);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created(uri).body(service.toResponse(saved));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UsuarioResponse> login(@RequestBody @Valid UsuarioLoginRequest r) {
        var user = service.findByEmail(r.email());

        if (Objects.isNull(user)) {
            return ResponseEntity.notFound().build();
        }

        if (!user.getSenha().equals(r.senha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var response = service.toResponse(user);
        return ResponseEntity.ok(response);
    }

}
