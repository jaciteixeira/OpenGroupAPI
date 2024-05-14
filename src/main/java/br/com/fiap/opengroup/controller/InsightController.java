package br.com.fiap.opengroup.controller;

import br.com.fiap.opengroup.dto.response.InsightResponse;
import br.com.fiap.opengroup.service.ClienteService;
import br.com.fiap.opengroup.service.InsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/insight")
public class InsightController {

    @Autowired
    private InsightService service;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/recomendacoes/{id}")
    public ResponseEntity<InsightResponse> obterRecomendacoes(@PathVariable Long id) {
        if (Objects.isNull(clienteService.findById(id))) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.gerarInsightPorIA(id));
    }
}
