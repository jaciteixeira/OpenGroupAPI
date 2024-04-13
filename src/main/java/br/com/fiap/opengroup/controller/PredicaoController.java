package br.com.fiap.opengroup.controller;

import br.com.fiap.opengroup.dto.request.PredicaoRequest;
import br.com.fiap.opengroup.dto.response.PredicaoResponse;
import br.com.fiap.opengroup.service.PredicaoService;
import com.theokanning.openai.completion.CompletionChoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/predicao")
public class PredicaoController {

    @Autowired
    PredicaoService predicaoService;



    @PostMapping("/recomendacoes")
    public ResponseEntity<PredicaoResponse> obterRecomendacoes(@RequestBody String prompt) {

        PredicaoRequest request = new PredicaoRequest("gpt-3.5-turbo", prompt);

        PredicaoResponse response = predicaoService.restTemplate().postForObject("https://api.openai.com/v1/chat/completions", request, PredicaoResponse.class);

        response.getChoices().get(0).getMessage().getContent();

        return ResponseEntity.ok(response);
    }
}
