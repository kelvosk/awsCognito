package br.com.aws.cognito.handler;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExceptionResponse {

    private HttpStatus status;
    private List<String> message;

    public String toJson() {
        return formatError().toString();
    }

    private StringBuilder formatError() {
        return new StringBuilder()
                .append("{")
                .append("\n")
                .append("\"error\":\"").append(status.value()).append("\"")
                .append(",")
                .append("\n")
                .append("\"message\":\"").append(message).append("\"")
                .append("\n")
                .append("}");
    }


}
