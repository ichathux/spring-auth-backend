package auth.example.demo.dto;

import lombok.Data;

@Data
public class CredentialDto {

    private String login;
    private char[] password;

}
