package br.com.fiap.opengroup.service;

import br.com.fiap.opengroup.dto.ServiceDTO;
import br.com.fiap.opengroup.dto.request.DadosRequest;
import br.com.fiap.opengroup.dto.response.DadosResponse;
import br.com.fiap.opengroup.entity.DadosCliente;
import br.com.fiap.opengroup.repository.DadosClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DadosClienteService implements ServiceDTO<DadosCliente, DadosRequest, DadosResponse> {

    @Autowired
    private DadosClienteRepository repo;

    @Override
    public DadosCliente toEntity(DadosRequest r) {
        if (Objects.isNull(r)) return null;
        return DadosCliente.builder()
                .nome(r.nome())
                .segmento(r.segmento())
                .localizacao(r.localizacao())
                .tempoAtuacao(r.tempoAtuacao())
                .numFuncionarios(r.numFuncionarios())
                .faturamentoAnual(r.faturamentoAnual())
                .canaisVenda(r.canaisVenda())
                .produtosServicos(r.produtosServicos())
                .tipo(r.tipo())
                .porte(r.porte())
                .concorrentes(r.concorrentes())
                .desafios(r.desafios())
                .objetivos(r.objetivos())
                .build();

    }

    @Override
    public DadosResponse toResponse(DadosCliente e) {
        if (Objects.isNull(e)) return null;
        return DadosResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .segmento(e.getSegmento())
                .localizacao(e.getLocalizacao())
                .tempoAtuacao(e.getTempoAtuacao())
                .numFuncionarios(e.getNumFuncionarios())
                .canaisVenda(e.getCanaisVenda())
                .produtosServicos(e.getProdutosServicos())
                .tipo(e.getTipo())
                .porte(e.getPorte())
                .build();
    }


    @Override
    public List<DadosCliente> findAll() {
        return repo.findAll();
    }

    @Override
    public List<DadosCliente> findAll(Example<DadosCliente> example) {
        return repo.findAll(example);
    }

    @Override
    public DadosCliente findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public DadosCliente save(DadosRequest r) {
        return repo.save(toEntity(r));
    }
}
