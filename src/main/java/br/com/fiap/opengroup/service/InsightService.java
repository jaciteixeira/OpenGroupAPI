package br.com.fiap.opengroup.service;

import br.com.fiap.opengroup.dto.response.InsightResponse;
import br.com.fiap.opengroup.entity.Impacto;
import br.com.fiap.opengroup.entity.Insight;
import br.com.fiap.opengroup.entity.Tipo;
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
    private DadosClienteService dadosClienteService;
    @Autowired
    ArquivoService arquivoService;

    public InsightResponse gerarInsightPorIA(Long id){

        var arquivos = arquivoService.findAllByDadosClienteId(id);
        var dadosCliente = dadosClienteService.findById(id);

        String nome = dadosCliente.getNome();
        String segmento = dadosCliente.getSegmento();
        String localizacao = dadosCliente.getLocalizacao();
        Integer tempo = dadosCliente.getTempoAtuacao();
        Integer numFuncionarios = dadosCliente.getNumFuncionarios();
        String faturamento = String.format("%.2f", dadosCliente.getFaturamentoAnual());
        String desafios = dadosCliente.getDesafios();

        // Exemplo de insights criados pela IA
        var recomendacao = InsightResponse.builder()
                .dadosCliente(nome)
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
                .dados(dadosCliente)
                .build());

        return recomendacao;
    }

    public Impacto gerarImpacto() {
        Impacto[] impacto = {Impacto.ALTO, Impacto.MUITO_ALTO, Impacto.EXTREMO};
        return impacto[new Random().nextInt(impacto.length)];
    }


}

