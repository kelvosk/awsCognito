package br.com.aws.cognito.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String fullName;
    private String cpf;
    private String email;
    private String dateOfBirth;
    private String phoneNumber;
    private String password;
    private String repeatedPassword;

}
