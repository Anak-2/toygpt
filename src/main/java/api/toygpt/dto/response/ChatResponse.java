package api.toygpt.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatResponse {

    private List<Choice> choices;
}
