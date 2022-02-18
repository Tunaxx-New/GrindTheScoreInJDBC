package com.NIK.Users;

public class InputUser extends User implements ContactSQL {
    public InputUser(int id, String login, String password, long score, int width, int height) {
        super(id, login, password, score, width, height);
    }

    @Override
    public String setLoginPro(String login) {
        setLogin(login);
        return "";
    }

    @Override
    public String setPasswordPro(String password) {
        setPassword(password);
        return "";
    }

    @Override
    public String setIdPro(int id) {
        setId(id);
        return "";
    }
}
