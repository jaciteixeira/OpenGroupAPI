package br.com.fiap.opengroup.controller;

import br.com.fiap.opengroup.dto.ControllerDTO;
import br.com.fiap.opengroup.dto.request.ClienteRequest;
import br.com.fiap.opengroup.dto.response.ArquivoResponse;
import br.com.fiap.opengroup.dto.response.ClienteResponse;
import br.com.fiap.opengroup.entity.Arquivo;
import br.com.fiap.opengroup.entity.Cliente;
import br.com.fiap.opengroup.entity.TipoEmpresa;
import br.com.fiap.opengroup.service.ArquivoService;
import br.com.fiap.opengroup.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController implements ControllerDTO<ClienteRequest, ClienteResponse> {

    @Autowired
    private ClienteService service;
    @Autowired
    private ArquivoService arquivoService;

    @GetMapping
    public ResponseEntity<Collection<ClienteResponse>> findAll(
            @RequestParam(name = "segmento", required = false) String segmento,
            @RequestParam(name = "tipo", required = false) String tipo,
            @RequestParam(name = "nome", required = false) String nome

    ){
        TipoEmpresa tipoEnum = null;
        if (tipo != null) {
            try {
                tipoEnum = TipoEmpresa.valueOf(tipo.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().build();
            }
        }
        var cliente = Cliente.builder()
                .nome(nome)
                .segmento(segmento)
                .tipo(tipoEnum != null ? TipoEmpresa.valueOf(tipoEnum.getSigla()) : null)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withMatcher("segmento", match -> match.contains())
                .withMatcher("nome", match -> match.contains())
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Cliente> example = Example.of(cliente, matcher);

        List<Cliente> clientes = service.findAll(example);
        return ResponseEntity.ok(clientes.stream().map(service::toResponse).toList());
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ClienteResponse> findById(@PathVariable Long id) {
        ClienteResponse cliente = service.toResponse(service.findById(id));
        if (cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cliente);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<ClienteResponse> save(@RequestBody @Valid ClienteRequest r) {
        var saved = service.save(r);
        if (saved == null) return ResponseEntity.badRequest().build();

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created(uri).body(service.toResponse(saved));
    }

    @Transactional
    @PostMapping(value = "/{id}/arquivo/upload")
    public ResponseEntity<?> upload(@RequestPart("arquivo")MultipartFile file, @RequestParam("palavra") String palavra, @PathVariable("id") Long id){
        var entity = service.findById( id );
        if (Objects.isNull( entity )) return ResponseEntity.notFound().build();

        String extension = StringUtils.getFilenameExtension( file.getOriginalFilename() );

        List<String> textExtensions = Arrays.asList("txt", "doc", "docx", "rtf", "odt", "csv", "tsv", "json", "xml", "yaml", "yml", "java", "py", "js", "html", "css", "md", "ini", "conf", "properties");

        // Verifica se o tipo de conteúdo é texto ou se a extensão está na lista de extensões permitidas
        if ((file.getContentType() != null && (extension != null && textExtensions.contains(extension.toLowerCase())))) {
            Arquivo arquivo = Arquivo.builder()
                    .nome(file.getOriginalFilename())
                    .src(file.getOriginalFilename().toLowerCase().replace( " ", "__" ) + "_" + entity.getId() + "_" + UUID.randomUUID() + "." + extension )
                    .extensao(extension)
                    .dataUpload(LocalDateTime.now())
                    .palavrasChave(palavra)
                    .tamanho(file.getSize())
                    .cliente(service.findById(id))
                    .build();
            var saved = arquivoService.save( arquivo, file);
            if (Objects.isNull(saved)) return ResponseEntity.badRequest().build();

            var response = arquivoService.toResponse(saved);
            var uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path( "/{id}" )
                    .buildAndExpand( saved.getId() ).toUri();

            return ResponseEntity.created( uri ).body( response ); //201
        } else {
            return ResponseEntity.badRequest().body("Apenas arquivos de texto são permitidos.");
        }
    }

    @GetMapping(value = "/{id}/arquivo")
    public ResponseEntity<Collection<ArquivoResponse>> findAllArquivos( @PathVariable("id") Long id){
        var arquivo = arquivoService.findAllByCliente(id);
        if (Objects.isNull(arquivo)) return ResponseEntity.badRequest().build();
        var response = arquivo.stream().map( arquivoService::toResponse ).toList();
        return ResponseEntity.ok(response);
    }
}
