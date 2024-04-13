package br.com.fiap.opengroup.service;

import br.com.fiap.opengroup.dto.ServiceDTO;
import br.com.fiap.opengroup.dto.request.ArquivoRequest;
import br.com.fiap.opengroup.dto.response.ArquivoResponse;
import br.com.fiap.opengroup.entity.Arquivo;
import br.com.fiap.opengroup.entity.Cliente;
import br.com.fiap.opengroup.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ArquivoService implements ServiceDTO<Arquivo, ArquivoRequest, ArquivoResponse> {

    @Autowired
    private ArquivoRepository repo;
    @Autowired
    private ClienteService clienteService;

    @Override
    public Arquivo toEntity(ArquivoRequest r) {
        if (Objects.isNull(r)) return null;

        return Arquivo.builder()
                .nome(r.nome())
                .caminho(r.caminho())
                .dataArquivo(LocalDate.now())
                .dadosCliente(clienteService.findById(r.cliente()))
                .build();
    }

    @Override
    public ArquivoResponse toResponse(Arquivo e) {
        if (Objects.isNull(e)) return null;
        return ArquivoResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .caminho(e.getCaminho())
                .cliente(clienteService.findById(e.getDadosCliente().getId()).getNome())
                .build();
    }

    @Override
    public List<Arquivo> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Arquivo> findAll(Example<Arquivo> example) {
        return repo.findAll(example);
    }

    @Override
    public Arquivo findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Arquivo save(ArquivoRequest r) {
        try {
            return repo.save(toEntity(r));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
