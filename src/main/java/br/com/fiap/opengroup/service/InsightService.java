package br.com.fiap.opengroup.service;

import br.com.fiap.opengroup.dto.ServiceDTO;
import br.com.fiap.opengroup.dto.request.InsightRequest;
import br.com.fiap.opengroup.dto.response.InsightResponse;
import br.com.fiap.opengroup.entity.Insight;
import br.com.fiap.opengroup.repository.InsightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class InsightService {

    @Autowired
    private InsightRepository repo;
    @Autowired
    private DadosClienteService dadosClienteService;
    @Autowired
    ArquivoService arquivoService;

    public InsightResponse gerarInsightPorIA(Long id){

        // Fixando id como 2, para que sempre retorne os dados com  id 2, para representar o exemplo gerado
        id = 2L;
        // A IA analisará os arquivos e os dados do cliente, criando insights personalizados para cada cliente.
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
        return InsightResponse.builder()
                .dadosId(nome)
                .dataGeracao(LocalDate.now())
                .detalhes(String.format("A empresa %s, do segmento de %s, localizada em %s, atuando há %s anos, com %s funcionários e faturamento anual de R$ %s, enfrenta o desafio de %s."
                        , nome, segmento, localizacao, tempo, numFuncionarios, faturamento, desafios))
                .recomendacoes("Para expandir seu mercado internacionalmente, a empresa pode considerar estratégias como a pesquisa de novos mercados potenciais, o estabelecimento de parcerias com empresas locais e o investimento em marketing digital internacional.")
                .impacto(0.8)
                .build();
    }
}

