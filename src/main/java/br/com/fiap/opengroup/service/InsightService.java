package br.com.fiap.opengroup.service;

import br.com.fiap.opengroup.dto.response.InsightResponse;
import br.com.fiap.opengroup.entity.*;
import br.com.fiap.opengroup.exception.ResourceNotFoundException;
import br.com.fiap.opengroup.repository.ClienteRepository;
import br.com.fiap.opengroup.repository.InsightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class InsightService {

    @Autowired
    private InsightRepository repo;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    ArquivoService arquivoService;

    public Cliente lerArquivoESalvarCliente(String filePath, Long id) throws IOException {
        Cliente cliente = clienteService.findById(id);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length < 2) continue;
                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (key) {
                    case "Nome":
                        cliente.setNome(value);
                        break;
                    case "Segmento":
                        cliente.setSegmento(value);
                        break;
                    case "Localização":
                        cliente.setLocalizacao(value);
                        break;
                    case "Tempo de Atuação":
                        cliente.setTempoAtuacao(Integer.parseInt(value.split(" ")[0]));
                        break;
                    case "Número de Funcionários":
                        cliente.setNumFuncionarios(Integer.parseInt(value));
                        break;
                    case "Faturamento Anual":
                        cliente.setFaturamentoAnual(Double.parseDouble(value.replace("R$ ", "").replace(".", "").replace(",", ".")));
                        break;
                    case "Canais de Venda":
                        cliente.setCanaisVenda(value);
                        break;
                    case "Tipo":
                        cliente.setTipo(TipoEmpresa.valueOf(value));
                        break;
                    case "Porte":
                        cliente.setPorte(value);
                        break;
                    case "Desafios":
                        cliente.setDesafios(value);
                        break;
                }
            }
        }

        return clienteRepository.save(cliente);
    }

    public InsightResponse gerarInsightPorIA(Long idCliente) {
        try {
            var arquivos = arquivoService.findAllByCliente(idCliente);
            Cliente cliente = null;
            for (var arquivo : arquivos) {
                if (arquivo.getNome().contains("dados_empresa")) {
                    String filePath = arquivo.getCaminho();
                    cliente = lerArquivoESalvarCliente(filePath, idCliente);
                    break; // Sair do loop assim que encontrar o arquivo desejado
                }
            }

            if (cliente == null) throw new ResourceNotFoundException("Arquivo com nome 'dados_empresa' não encontrado.");

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
                    .dataGeracao(LocalDateTime.now())
                    .detalhes(String.format("A empresa %s, do segmento de %s, localizada em %s, atuando há %s anos, com %s funcionários e faturamento anual de R$ %s, enfrenta o desafio de %s.",
                            nome, segmento, localizacao, tempo, numFuncionarios, faturamento, desafios))
                    .recomendacoes("Para expandir seu mercado internacionalmente, a empresa pode considerar estratégias como a pesquisa de novos mercados potenciais, o estabelecimento de parcerias com empresas locais e o investimento em marketing digital internacional.")
                    .impacto(gerarImpacto())
                    .build();

            repo.save(Insight.builder()
                    .dataGeracao(LocalDateTime.now())
                    .detalhes(recomendacao.detalhes())
                    .recomendacoes(recomendacao.recomendacoes())
                    .impacto(Impacto.valueOf(recomendacao.impacto().toString()))
                    .cliente(cliente)
                    .build());

            return recomendacao;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Impacto gerarImpacto() {
        Impacto[] impacto = {Impacto.ALTO, Impacto.MUITO_ALTO, Impacto.EXTREMO};
        return impacto[new Random().nextInt(impacto.length)];
    }
}
