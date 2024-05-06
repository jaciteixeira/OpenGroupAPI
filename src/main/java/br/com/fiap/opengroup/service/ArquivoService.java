package br.com.fiap.opengroup.service;

import br.com.fiap.opengroup.dto.ServiceDTO;
import br.com.fiap.opengroup.dto.request.ArquivoRequest;
import br.com.fiap.opengroup.dto.response.ArquivoResponse;
import br.com.fiap.opengroup.entity.Arquivo;
import br.com.fiap.opengroup.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ArquivoService implements ServiceDTO<Arquivo, ArquivoRequest, ArquivoResponse> {

    @Autowired
    private ArquivoRepository repo;
    @Autowired
    private DadosClienteService dadosClienteService;

    public List<Arquivo> findAllByDadosClienteId(Long dadosClienteId) {
        return repo.findAllByDadosClienteId(dadosClienteId);
    }

    @Override
    public Arquivo toEntity(ArquivoRequest r) {
        if (Objects.isNull(r)) return null;
        var dados = dadosClienteService.findById(r.dadosClienteId());
        if (Objects.isNull(dados)) return null;

        return Arquivo.builder()
                .nome(r.nome())
                .tipo(r.tipo())
                .tamanho(r.tamanho())
                .palavrasChave(r.palavrasChave())
                .link(r.link())
                .dataUpload(LocalDate.now())
                .resumo(r.resumo())
                .dadosCliente(dados)
                .build();
    }

    @Override
    public ArquivoResponse toResponse(Arquivo e) {
        if (Objects.isNull(e)) return null;
        return ArquivoResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .tipo(e.getTipo())
                .link(e.getLink())
                .tamanho(e.getTamanho())
                .resumo(e.getResumo())
                .dadosCliente(dadosClienteService.findById(e.getDadosCliente().getId()).getNome())
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
        return repo.save(toEntity(r));
    }

    public void lerArquivo(){
        Path path = Paths.get("documentacao/dados_empresa_tech_solutions.txt");
        try {
            List<String> linhas = Files.readAllLines(path);
            for (String linha : linhas) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
