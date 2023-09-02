package org.example.password;

import org.example.password.PasswordGeneratePolicy;

public class WrongPasswordGenerator implements PasswordGeneratePolicy {
    @Override
    public String generatePassword() {
        return "ab";
    }
}
