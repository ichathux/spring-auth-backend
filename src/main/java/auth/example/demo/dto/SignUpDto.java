package auth.example.demo.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class SignUpDto {
    private String firstName;
    private String lastName;
    private String login;
    private char[] password;


}
