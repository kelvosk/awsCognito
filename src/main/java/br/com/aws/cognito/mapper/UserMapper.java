package br.com.aws.cognito.mapper;

import br.com.aws.cognito.domain.User;
import br.com.aws.cognito.request.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User userRequestToUserDomain(UserRequest userRequest) {
        return User.builder()
                .fullName(userRequest.getFullName())
                .cpf(userRequest.getCpf())
                .email(userRequest.getEmail())
                .dateOfBirth(userRequest.getDateOfBirth())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(userRequest.getPassword())
                .build();
    }


}
