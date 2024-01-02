package br.com.aws.cognito.handler;

import br.com.aws.cognito.error.LoginException;
import br.com.aws.cognito.error.SignUpException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity handleLoginException(LoginException le, WebRequest request) {

        var exceptionResponse =
                ExceptionResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(Arrays.asList(le.getMessage()))
                        .build()
                        .toJson();

        return handleExceptionInternal(le, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(SignUpException.class)
    public ResponseEntity handleSignUpException(SignUpException se, WebRequest request) {

        var exceptionResponse =
                ExceptionResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(Arrays.asList(se.getMessage()))
                        .build()
                        .toJson();

        return handleExceptionInternal(se, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

}
