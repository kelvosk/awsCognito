package br.com.aws.cognito.error;

public class LoginException extends RuntimeException {

    public LoginException(String message) {
        super(message);
    }

}
