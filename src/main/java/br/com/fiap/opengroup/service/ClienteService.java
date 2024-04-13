package br.com.fiap.opengroup.service;

import br.com.fiap.opengroup.dto.ServiceDTO;
import br.com.fiap.opengroup.dto.request.ClienteRequest;
import br.com.fiap.opengroup.dto.response.ClienteResponse;
import br.com.fiap.opengroup.entity.Cliente;
import br.com.fiap.opengroup.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteService implements ServiceDTO<Cliente, ClienteRequest, ClienteResponse> {

    @Autowired
    private ClienteRepository repo;

    @Override
    public Cliente toEntity(ClienteRequest r) {
        if (Objects.isNull(r)) return null;
        return Cliente.builder()
                .nome(r.nome())
                .email(r.email())
                .dataNascimento(r.dataNascimento())
                .genero(r.genero())
                .cep(r.cep())
                .telefone(r.telefone())
                .cpf(r.cpf())
                .profissao(r.profissao())
                .estadoCivil(r.estadoCivil())
                .ultimaCompra(r.ultimaCompra())
                .build();

    }

    @Override
    public ClienteResponse toResponse(Cliente e) {
        if (Objects.isNull(e)) return null;
        return ClienteResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .email(e.getEmail())
                .dataNascimento(e.getDataNascimento())
                .genero(e.getGenero())
                .profissao(e.getProfissao())
                .build();
    }

    @Override
    public List<Cliente> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Cliente> findAll(Example<Cliente> example) {
        return repo.findAll(example);
    }

    @Override
    public Cliente findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Cliente save(ClienteRequest r) {
        return repo.save(toEntity(r));
    }
}
