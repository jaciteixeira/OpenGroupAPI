package br.com.fiap.opengroup.dto.response;

import br.com.fiap.opengroup.entity.PredicaoMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredicaoResponse {

    private List<Choice> choices;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice{
        private int index;
        private PredicaoMessage message;
    }
}
