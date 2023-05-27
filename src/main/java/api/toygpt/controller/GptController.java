package api.toygpt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class GptController {

    @GetMapping("/gptApi")
    public String getGptApi(){
//        결과값을 담을 객체
        HashMap<String, Object> resultMap = new HashMap<>();
        try{
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000); // 타임아웃 5초

            RestTemplate restTemplate = new RestTemplate(factory);
            HttpHeaders header = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(header);

            String url = "api 요청 URL";
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("파라미터명","값").build(false);

            ResponseEntity<Map> response = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);

            resultMap.put("statusCode", response.getStatusCode());
            resultMap.put("header", response.getHeaders());
            resultMap.put("body", response.getBody());

            ObjectMapper mapper = new ObjectMapper();
            JSONPObject jsonpObject = new JSONPObject("JSON.parse",resultMap);
            String jsonStr = mapper.writeValueAsString(jsonpObject);
            return jsonStr;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error";
    }
}
