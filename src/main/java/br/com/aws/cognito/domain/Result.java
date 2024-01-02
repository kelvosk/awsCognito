package br.com.aws.cognito.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private String resultCode;
    private String resultDesc;

}
