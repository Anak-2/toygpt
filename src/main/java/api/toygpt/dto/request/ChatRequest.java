package api.toygpt.dto.request;

import api.toygpt.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private int n;
    private double temperature;

    @Builder
    public ChatRequest(String model, List<Message> messages, int n, double temperature){
        this.model = model;
        this.messages = messages;
        this.n = n;
        this.temperature = temperature;
    }
}
