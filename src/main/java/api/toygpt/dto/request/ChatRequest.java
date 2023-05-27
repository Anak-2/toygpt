package api.toygpt.dto.request;

import api.toygpt.dto.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
public class ChatRequest {
    private String model; // 무슨 gpt 모델 사용할지
    private List<Message> messages; // 보낼 메시지 Message(user, prompt)
    private int n; // 몇 개의 대답을 만들지 (default = 1)
    private double temperature; // 랜덤성을 얼마나 줄 지 (default = 1 , range = 0~1)
    private int max_tokens; // 대답의 최대 토큰 개수 (default = infinity)

    @Builder(builderClassName = "a", builderMethodName = "chatRequest")
    public ChatRequest(String model, String prompt, int n, double temperature, int max_tokens){
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user",prompt));
        this.n = n;
        this.temperature = temperature;
        this.max_tokens = max_tokens;
    }

    @Builder(builderClassName = "b", builderMethodName = "chatRequest2")
    public ChatRequest(String model, String prompt){
        this.model = model;
        this.messages = new ArrayList<>();
        messages.add(new Message("user",prompt));
    }
}
