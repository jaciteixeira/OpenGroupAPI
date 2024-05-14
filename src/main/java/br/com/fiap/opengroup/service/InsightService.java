package br.com.fiap.opengroup.service;

import br.com.fiap.opengroup.dto.response.InsightResponse;
import br.com.fiap.opengroup.entity.Impacto;
import br.com.fiap.opengroup.entity.Insight;
import br.com.fiap.opengroup.repository.InsightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class InsightService {

    @Autowired
    private InsightRepository repo;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    ArquivoService arquivoService;

    public InsightResponse gerarInsightPorIA(Long id){

        var cliente = clienteService.findById(id);

        String nome = cliente.getNome();
        String segmento = cliente.getSegmento();
        String localizacao = cliente.getLocalizacao();
        Integer tempo = cliente.getTempoAtuacao();
        Integer numFuncionarios = cliente.getNumFuncionarios();
        String faturamento = String.format("%.2f", cliente.getFaturamentoAnual());
        String desafios = cliente.getDesafios();

        // Exemplo de insights criados pela IA
        var recomendacao = InsightResponse.builder()
                .cliente(nome)
                .dataGeracao(LocalDate.now())
                .detalhes(String.format("A empresa %s, do segmento de %s, localizada em %s, atuando há %s anos, com %s funcionários e faturamento anual de R$ %s, enfrenta o desafio de %s."
                        , nome, segmento, localizacao, tempo, numFuncionarios, faturamento, desafios))
                .recomendacoes("Para expandir seu mercado internacionalmente, a empresa pode considerar estratégias como a pesquisa de novos mercados potenciais, o estabelecimento de parcerias com empresas locais e o investimento em marketing digital internacional.")
                .impacto(gerarImpacto())
                .build();

        repo.save(Insight.builder()
                .dataGeracao(LocalDate.now())
                .detalhes(recomendacao.detalhes())
                .recomendacoes(recomendacao.recomendacoes())
                .impacto(Impacto.valueOf(recomendacao.impacto().toString()))
                .cliente(cliente)
                .build());

        return recomendacao;
    }

    public Impacto gerarImpacto() {
        Impacto[] impacto = {Impacto.ALTO, Impacto.MUITO_ALTO, Impacto.EXTREMO};
        return impacto[new Random().nextInt(impacto.length)];
    }


}

