package api.toygpt.dto.response;

import api.toygpt.dto.Message;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
public class ChatResponse {

    private List<Choice> choices;

    @Getter
    public static class Choice{
        private int index;
        private Message message;
    }
}
