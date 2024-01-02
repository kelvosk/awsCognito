package br.com.aws.cognito.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class PasswordManager {

    public boolean checkPassword(String password, String repeatedPassword) {
        return password.trim().equalsIgnoreCase(repeatedPassword.trim());
    }

    public String passwordEncrypt(String password) {
        return DigestUtils.sha256Hex(password);
    }


}
