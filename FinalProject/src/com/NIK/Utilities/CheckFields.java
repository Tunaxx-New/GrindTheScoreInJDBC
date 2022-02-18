package com.NIK.Utilities;

public class CheckFields {
    public static void checkLogin(String login) throws CheckException {
        if (login.length() > 32) {
            throw new CheckException("Login field length must be less or equal to 32 character");
        }
        if (login.length() <= 0 || login.equals("")) {
            throw new CheckException("There is no login!");
        }
    }

    public static void checkPassword(String password) throws CheckException {
        boolean haveCapital = true;
        boolean haveDigit = true;
        boolean haveSpecial = true;
        boolean haveLower = true;

        if (password.length() > 64) {
            throw new CheckException("Password field length must be less or equal to 64 character");
        }

        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) haveCapital = false;
            if (Character.isDigit(password.charAt(i))) haveDigit = false;
            if (isSpecial(password.charAt(i))) haveSpecial = false;
            if (Character.isLowerCase(password.charAt(i))) haveLower = false;
            if (password.charAt(i) == ' ') throw new CheckException("No spacing required in password!");
        }

        if (haveCapital) {
            throw new CheckException("Password doesn't contain Uppercase character");
        }
        if (haveDigit) {
            throw new CheckException("Password doesn't contain Digit character");
        }
        if (haveSpecial) {
            throw new CheckException("Password doesn't contain Special character");
        }
        if (haveLower) {
            throw new CheckException("Password doesn't contain Lowercase character");
        }
    }

    public static boolean isSpecial(Character a) {
        boolean isSpec = false;
        String specials = "!\"#$%&'()*+,`/:;<=>?@[\\]^~{|}_-";
        for (int i = 0; i < specials.length(); i++) {
            if (Character.compare(a, specials.charAt(i)) == 0) isSpec = true;
        }
        return isSpec;
    }

    public static String checkId(int id) {
        if (id < 0) return "You are in black list";
        if (id == 0) return "You are not authorized user";
        if (id > 0) return "You are authorized and decent user";
        return "";
    }
}
