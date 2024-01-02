package br.com.aws.cognito.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CognitoClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(CognitoClient.class);

    private final CognitoIdentityProviderClient client;

    @Value("${aws.cognito.clientId}")
    private String clientId;

    @Value("${aws.cognito.userPool}")
    private String userPool;

    @Value("${aws.cognito.secretHash}")
    private String secretHash;

    public CognitoClient() {this.client = createClient();}

    private CognitoIdentityProviderClient createClient() {

        return CognitoIdentityProviderClient.builder()
                .region(Region.EU_NORTH_1)
                .build();
    }

    public void signUp(String username,
                       String password,
                       String email,
                       String name,
                       String birthDate,
                       String phoneNumber) throws NoSuchAlgorithmException, InvalidKeyException {

        LOGGER.info("L=I C=CognitoClient M=signUp D=Register a user into Cognito AWS");

        String secretHash = calculateSecretHash(
                clientId,
                this.secretHash,
                username
        );

        try {
            SignUpRequest signUpRequest = SignUpRequest.builder()
                    .userAttributes(fillUpFields(email, name, birthDate, phoneNumber))
                    .username(username)
                    .secretHash(secretHash)
                    .clientId(clientId)
                    .password(password)
                    .build();

            client.signUp(signUpRequest);
            client.close();
        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            LOGGER.error(e.getMessage());
        }

    }

    private List<AttributeType> fillUpFields(
            String email,
            String name,
            String birthDate,
            String phoneNumber){

        AttributeType attrEmail = AttributeType.builder()
                .name("email")
                .value(email)
                .build();

        AttributeType attrName = AttributeType.builder()
                .name("name")
                .value(name)
                .build();

        AttributeType attrBirthDate = AttributeType.builder()
                .name("birthdate")
                .value(birthDate)
                .build();

        AttributeType attrPhoneNumber = AttributeType.builder()
                .name("phone_number")
                .value(phoneNumber)
                .build();

        List<AttributeType> attributeTypeList = new ArrayList<>();
        attributeTypeList.add(attrEmail);
        attributeTypeList.add(attrName);
        attributeTypeList.add(attrBirthDate);
        attributeTypeList.add(attrPhoneNumber);

        return attributeTypeList;
    }

    public static String calculateSecretHash(String userPoolClientId, String userPoolClientSecret, String userName) throws InvalidKeyException, NoSuchAlgorithmException {
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

        SecretKeySpec signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);

        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        mac.init(signingKey);
        mac.update(userName.getBytes(StandardCharsets.UTF_8));
        byte[] rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));
        return java.util.Base64.getEncoder().encodeToString(rawHmac);
    }

}
