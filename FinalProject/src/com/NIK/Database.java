package com.NIK;

import com.NIK.Users.CheckableUser;
import com.NIK.Users.InputUser;
import com.NIK.Utilities.CheckException;
import com.NIK.Visual.Canvas;

import java.sql.*;

public class Database {
    private Connection con = null;
    private Statement st = null;

    public Database() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tunaxx");
            if (con != null) {
                System.out.println("Connection successful! " + con.getCatalog());
            }
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (con != null) con.close();
            if (st != null) st.close();
            System.out.println("Connection closed!");
        } catch (SQLException e) {}
    }

    public void update(CheckableUser user) {
        if (user.getId() != 0) {
            String state = "UPDATE finalproject SET score = " + user.getScore() + " WHERE id = '" + user.getId() + "'";
            try {
                st = con.createStatement();
                st.executeUpdate(state);
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            state = "UPDATE finalproject SET map = '" + String.valueOf(user.getBits()) + "' WHERE id = '" + user.getId() + "'";
            try {
                st = con.createStatement();
                st.executeUpdate(state);
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            state = "UPDATE finalproject SET count = '" + String.valueOf(user.getCount()) + "' WHERE id = '" + user.getId() + "'";
            try {
                st = con.createStatement();
                st.executeUpdate(state);
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerUser(CheckableUser user, Canvas draw) throws CheckException {
        if (user.getLogin().equals("") || user.getLogin() == null) throw new CheckException("No login!");
        if (user.getPassword().equals("") || user.getPassword() == null) throw new CheckException("No password!");

        if (isLoginExist(user.getLogin())) throw new CheckException("This login is already exist!");

        try {
            int id = calculateNextId();
            if (id == 0) throw new SQLException("SQL connection failed!");
            String state = "INSERT INTO finalproject VALUES (";
            state += Integer.toString(id) + ", '";
            state += user.getLogin() + "', '";
            char[] bits = user.getBits();
            state += user.getPassword() + "', 0, '";
            for (int i = 0; i < bits.length; i++) {
                state += bits[i];
            }
            state += "',";
            state += Integer.toString(user.getWidth()) + ", ";
            state += Integer.toString(user.getHeight()) + ", 10)";
            System.out.println(user.setIdPro(id));

            draw.rsize(user);
            user.setCount(10);

            st = con.createStatement();
            st.executeUpdate(state);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkOP(CheckableUser user) {
        PreparedStatement prepStmt = null;
        try {
            prepStmt = con.prepareStatement("SELECT OPLogin FROM finalprojectops", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()) {
                if (rs.getString("oplogin").equals(user.getLogin())) return true;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String LoginUser(CheckableUser user, Canvas draw) {
        if (!isPasswordCorrect(user.getLogin(), user.getPassword())) return "Password is incorrect!";

        user.setScore(user.getScore());
        update(user);

        PreparedStatement prepStmt = null;
        try {
            prepStmt = con.prepareStatement("SELECT * FROM finalproject WHERE LOGIN = '" + user.getLogin() + "'", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();

            user.setBits(Integer.parseInt(rs.getString("width")), Integer.parseInt(rs.getString("height")));

            user.setBits(rs.getString("map"));
            System.out.println(user.setIdPro(Integer.parseInt(rs.getString("id"))));
            draw.rsize((InputUser)user);
            draw.bitMap(user.getBits(), user);
            user.setScore(Integer.parseInt(rs.getString("score")));
            user.setCount(Integer.parseInt(rs.getString("count")));
            rs.close();
            return "Successful sign in!";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean isLoginExist(String login) {
        PreparedStatement prepStmt = null;
        try {
            prepStmt = con.prepareStatement("SELECT LOGIN FROM finalproject", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()) {
                if (rs.getString("login").equals(login)) return true;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPasswordCorrect(String login, String password) {
        try {
            PreparedStatement prepStmt = null;
            String state = "SELECT password FROM finalproject WHERE login = '" + login + "'";
            prepStmt = con.prepareStatement(state, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            if (rs.getString("password").equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void banUser(String login) {
        String state = "UPDATE finalproject SET id = -id WHERE login = '" + login + "'";
        try {
            st = con.createStatement();
            st.executeUpdate(state);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //If returns 0 - SQL error
    public int calculateNextId() {
        PreparedStatement prepStmt = null;
        try {
            prepStmt = con.prepareStatement("SELECT ID FROM finalproject", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = prepStmt.executeQuery();

            int rowCount = 1;
            while(rs.next()) {
                rowCount++;
            }
            rs.close();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}