package org.example.password;

import org.example.password.PasswordGeneratePolicy;

public class CorrectPasswordGenerator implements PasswordGeneratePolicy {
    @Override
    public String generatePassword() {
        return "aaaabbbbcc";
    }
}
