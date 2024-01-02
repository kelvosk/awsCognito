package br.com.aws.cognito.api;

import br.com.aws.cognito.response.UserResponse;
import br.com.aws.cognito.request.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequestMapping(value = "/users",
        produces = MediaType.APPLICATION_JSON_VALUE)
public interface UserApi {

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    UserResponse signUp(@RequestBody UserRequest request) throws NoSuchAlgorithmException, InvalidKeyException;

}
