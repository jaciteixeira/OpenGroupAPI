package br.com.fiap.opengroup.service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PredicaoService {

//    @Value("${openai.api.key}")
    private String API_KEY = "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";


    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(((request, body, execution) -> {
            request.getHeaders().add("Authorization",
                    "Bearer " + API_KEY);
            return execution.execute(request, body);
        }));

        return restTemplate;
    }


}

