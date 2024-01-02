package br.com.aws.cognito.services;

import br.com.aws.cognito.domain.User;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public interface UserService {

    User signUp(User user) throws NoSuchAlgorithmException, InvalidKeyException;

}
