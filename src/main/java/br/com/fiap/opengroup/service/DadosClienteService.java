package br.com.fiap.opengroup.service;

import br.com.fiap.opengroup.dto.ServiceDTO;
import br.com.fiap.opengroup.dto.request.ClienteRequest;
import br.com.fiap.opengroup.dto.response.ClienteResponse;
import br.com.fiap.opengroup.entity.Cliente;
import br.com.fiap.opengroup.entity.TipoEmpresa;
import br.com.fiap.opengroup.repository.DadosClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class DadosClienteService implements ServiceDTO<Cliente, ClienteRequest, ClienteResponse> {

    @Autowired
    private DadosClienteRepository repo;

    @Override
    public Cliente toEntity(ClienteRequest r) {
        if (Objects.isNull(r)) return null;
        return Cliente.builder()
                .nome(r.nome())
                .segmento(r.segmento())
                .localizacao(r.localizacao())
                .tempoAtuacao(r.tempoAtuacao())
                .numFuncionarios(r.numFuncionarios())
                .faturamentoAnual(r.faturamentoAnual())
                .canaisVenda(r.canaisVenda())
                .produtosServicos(gerarProdutoServico())
                .tipo(TipoEmpresa.valueOf(r.tipo()))
                .porte(r.porte())
                .concorrentes(gerarConcorrente())
                .desafios(r.desafios())
                .objetivos(gerarObjetivo())
                .build();

    }

    @Override
    public ClienteResponse toResponse(Cliente e) {
        if (Objects.isNull(e)) return null;
        return ClienteResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .segmento(e.getSegmento())
                .localizacao(e.getLocalizacao())
                .tempoAtuacao(e.getTempoAtuacao())
                .numFuncionarios(e.getNumFuncionarios())
                .canaisVenda(e.getCanaisVenda())
                .produtosServicos(e.getProdutosServicos())
                .tipo(String.valueOf(e.getTipo()))
                .porte(e.getPorte())
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

    public String gerarObjetivo() {
        String[] objetivos = {"Aumentar market share", "Expandir para novos mercados", "Melhorar a eficiência operacional"};
        return objetivos[new Random().nextInt(objetivos.length)];
    }

    public String gerarConcorrente() {
        String[] concorrentes = {"CodeWise Solutions", "InnovateTech Solutions", "DataSphere Solutions"};
        return concorrentes[new Random().nextInt(concorrentes.length)];
    }

    public String gerarProdutoServico() {
        String[] produtosServicos = {"Software", "Consultoria", "Desenvolvimento de aplicativos"};
        return produtosServicos[new Random().nextInt(produtosServicos.length)];
    }
}
