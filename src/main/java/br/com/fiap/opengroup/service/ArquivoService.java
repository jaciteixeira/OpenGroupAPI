package br.com.fiap.opengroup.service;

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
import java.util.List;

@Service
public class ArquivoService{

    private final String ARQUIVO_FOLDER = System.getProperty("user.dir") + "file_server/arquivos_empresas";

    @Autowired
    private ArquivoRepository repo;
    @Autowired
    private DadosClienteService clienteService;

    public List<Arquivo> findAllByClienteId(Long dadosClienteId) {
        return repo.findAllByDadosClienteId(dadosClienteId);
    }

    public Arquivo save(Arquivo arq, MultipartFile file){
        Arquivo saved = null;
        if (uploadFile(file, arq)) saved = repo.save(arq);
        return saved;
    }

    private boolean uploadFile(MultipartFile file, Arquivo arq) {

        if (file.isEmpty()) throw new RuntimeException( "Arquivo vazio" );

        Path destination = Paths
                .get( ARQUIVO_FOLDER )
                .resolve( arq.getSrc() )
                .normalize()
                .toAbsolutePath();
        try {
             if (!Files.exists(Path.of( ARQUIVO_FOLDER ))) Files.createDirectories(Path.of (ARQUIVO_FOLDER));
             Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println( "[ IOEXCEPTION ][  ARQUIVO - UPLOAD  ] -  ERRO NO UPLOAD DO ARQUIVO:  " + e.getMessage() );
//            log.debug("[ IOEXCEPTION ][  ARQUIVO - UPLOAD  ] -  ERRO NO UPLOAD DO ARQUIVO:  " + e.getMessage());
            return false;
        }
        return true;
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
