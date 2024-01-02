package br.com.aws.cognito.error;

public class SignUpException extends RuntimeException {

    public SignUpException(String message) {
        super(message);
    }

}
