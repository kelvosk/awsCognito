package br.com.aws.cognito.impl;

import br.com.aws.cognito.client.CognitoClient;
import br.com.aws.cognito.domain.User;
import br.com.aws.cognito.repository.UserRepository;
import br.com.aws.cognito.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CognitoClient cognitoClient;

    @Override
    @Transactional
    public User signUp(User user) throws NoSuchAlgorithmException, InvalidKeyException {

        var userResponse = userRepository.save(user);

        cognitoClient.signUp("wallesht", user.getPassword(), user.getEmail(), user.getFullName(), user.getDateOfBirth(), user.getPhoneNumber());

        return User.builder()
                .id(userResponse.getId())
                .email(userResponse.getEmail())
                .build();
    }
}
