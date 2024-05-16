package br.com.fiap.opengroup.service;

import br.com.fiap.opengroup.dto.response.ArquivoResponse;
import br.com.fiap.opengroup.entity.Arquivo;
import br.com.fiap.opengroup.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class ArquivoService {

    private static final String BASE_FOLDER = "file_server/arquivos_empresas";
    private final String ARQUIVO_FOLDER = System.getProperty("user.dir") + "/" + BASE_FOLDER;

    @Autowired
    private ArquivoRepository repo;
    @Autowired
    private ClienteService clienteService;

    public Arquivo save(Arquivo arq, MultipartFile file) {
//        Arquivo saved = null;
//        arq.setCaminho(BASE_FOLDER+arq.getSrc());
        Arquivo saved = null;
        String nomeArquivo = arq.getSrc();
        String caminhoCompleto = Paths.get(BASE_FOLDER, nomeArquivo).toString();
        arq.setCaminho(caminhoCompleto);
        if (uploadFile(file, arq)) saved = repo.save(arq);
        return saved;
    }

    public boolean uploadFile(MultipartFile file, Arquivo arq) {
        if (file.isEmpty()) throw new RuntimeException("Arquivo vazio");

        Path destination = Paths
                .get(ARQUIVO_FOLDER)
                .resolve(arq.getSrc())
                .normalize()
                .toAbsolutePath();
        try {
            if (!Files.exists(Path.of( ARQUIVO_FOLDER ))) Files.createDirectories(Path.of ( ARQUIVO_FOLDER ));
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println( "[ IOEXCEPTION ][  ARQUIVO - UPLOAD  ] -  ERRO NO UPLOAD DO ARQUIVO:  " + e.getMessage() );
//            log.debug("[ IOEXCEPTION ][  ARQUIVO - UPLOAD  ] -  ERRO NO UPLOAD DO ARQUIVO:  " + e.getMessage());
            return false;
        }
        return true;
    }

//    public Arquivo findByCliente(Long idCliente) {
//        return repo.findByClienteId(idCliente);
//    }

    public void lerArquivo(){
        Path path = Paths.get("documentacao/cliente_empresa_tech_solutions.txt");
        try {
            List<String> linhas = Files.readAllLines(path);
            for (String linha : linhas) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArquivoResponse toResponse(Arquivo e) {
        if (Objects.isNull(e)) return null;

        return ArquivoResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .src(e.getSrc())
                .caminho(e.getCaminho())
                .extensao(e.getExtensao())
                .tamanho(e.getTamanho())
                .resumo(e.getResumo())
                .dataUpload(LocalDateTime.now())
                .build();
    }

    public Collection<Arquivo> findAllByCliente(Long id) {
        return repo.findAllByClienteId(id);
    }
}
