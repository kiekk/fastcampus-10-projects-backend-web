package org.example;

public class User {
    private String password;

    public void initPassword(PasswordGeneratePolicy passwordGenerate) {
        String randomPassword = passwordGenerate.generatePassword();
        /**
         * 비밀번호는 최소 8자 이상 12자 이하여야 한다.
         */
        if (randomPassword.length() >= 8 && randomPassword.length() <= 12) {
            this.password = randomPassword;
        }
    }

    public String getPassword() {
        return password;
    }
}
