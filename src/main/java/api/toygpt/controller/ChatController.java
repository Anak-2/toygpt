package api.toygpt.controller;

import api.toygpt.dto.request.ChatRequest;
import api.toygpt.dto.response.ChatResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ChatController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {

        ChatRequest chatRequest = ChatRequest.chatRequest()
                .model(model)
                .prompt(prompt)
                .n(1)
                .temperature(0.8)
                .max_tokens(10)
                .build();

        ChatResponse response = restTemplate.postForObject(apiUrl, chatRequest, ChatResponse.class);

        if (response == null || response.getChoices() == null ||
                response.getChoices().isEmpty()) {
            return "No response";
        }

        return response.getChoices().get(0).getMessage().getContent();
    }

//    To avoid CORS Error
    @GetMapping("/chat/proxy")
    @ResponseBody
    public String proxyChat(@RequestParam String prompt){
        Map<String, String> uriValues = new HashMap<>();
        uriValues.put("prompt",prompt);
        return restTemplate.getForObject("http://localhost:8080/chat",String.class,uriValues);
    }
}
