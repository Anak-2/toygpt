package api.toygpt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // API 에서 response 받을 때 Default Constructor 필요
public class Message {
    private String role;
    private String content;
}
