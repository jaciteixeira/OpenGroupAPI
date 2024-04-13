package br.com.fiap.opengroup.dto.request;

import br.com.fiap.opengroup.entity.PredicaoMessage;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PredicaoRequest {
    private String model;
    private List<PredicaoMessage> messages;

    public PredicaoRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new PredicaoMessage("user", prompt));
    }
}
