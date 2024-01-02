package br.com.aws.cognito.controller;

import br.com.aws.cognito.api.UserApi;
import br.com.aws.cognito.interactor.UserInteractor;
import br.com.aws.cognito.request.UserRequest;
import br.com.aws.cognito.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class UserController implements UserApi {

    @Autowired
    UserInteractor userInteractor;

    @Override
    public UserResponse signUp(UserRequest request) throws NoSuchAlgorithmException, InvalidKeyException {
        return userInteractor.signUp(request);
    }
}
