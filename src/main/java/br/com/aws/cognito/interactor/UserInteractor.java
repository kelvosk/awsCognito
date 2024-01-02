package br.com.aws.cognito.interactor;

import br.com.aws.cognito.domain.Result;
import br.com.aws.cognito.error.SignUpException;
import br.com.aws.cognito.impl.UserImpl;
import br.com.aws.cognito.mapper.UserMapper;
import br.com.aws.cognito.request.UserRequest;
import br.com.aws.cognito.response.UserResponse;
import br.com.aws.cognito.util.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class UserInteractor {

    @Autowired
    UserImpl userImpl;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordManager passwordManager;

    public UserResponse signUp(UserRequest userRequest) throws NoSuchAlgorithmException, InvalidKeyException {

        try {
            userFieldsTreatment(userRequest);

            var user = userMapper.userRequestToUserDomain(userRequest);

            userImpl.signUp(user);

        } catch (Exception e) {
            throw new SignUpException("Sign Up was Unsuccessful, check your data and try again.");
        }

        return UserResponse.builder()
                .result(Result.builder()
                        .resultCode("200")
                        .resultDesc("Register was successful")
                        .build())
                .build();
    }

    private void userFieldsTreatment(UserRequest userRequest) {
        userRequest.setFullName(userRequest.getFullName().trim());
        userRequest.setCpf(userRequest.getCpf().trim());
        userRequest.setEmail(userRequest.getEmail().trim());
        userRequest.setDateOfBirth(userRequest.getDateOfBirth().trim());
        userRequest.setPhoneNumber(userRequest.getPhoneNumber().trim());
        userRequest.setPassword(userRequest.getPassword().trim());
        userRequest.setRepeatedPassword(userRequest.getRepeatedPassword().trim());

        var bool = passwordManager.checkPassword(userRequest.getPassword(), userRequest.getRepeatedPassword());

        if(!bool) {
            throw new RuntimeException();
        }

    }



}
