package com.NIK.Users;

import com.NIK.Utilities.CheckException;
import com.NIK.Utilities.CheckFields;

public class CheckableUser extends InputUser {
    public CheckableUser(int id, String login, String password, long score, int width, int height) {
        super(id, login, password, score, width, height);
    }

    @Override
    public String setLoginPro(String login) {
        try {
            CheckFields.checkLogin(login);
        } catch (CheckException e) {
            return e.getMessage();
        }
        setLogin(login);
        return "Successful login!";
    }

    @Override
    public String setPasswordPro(String password) {
        try {
            CheckFields.checkPassword(password);
        } catch (CheckException e) {
            return e.getMessage();
        }
        setPassword(password);
        return "Successful password!";
    }

    @Override
    public String setIdPro(int id) {
        setId(id);
        return CheckFields.checkId(id);
    }
}

