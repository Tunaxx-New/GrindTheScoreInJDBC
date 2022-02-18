package com.NIK;

import com.NIK.Users.CheckableUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static Window window;
    public static void main(String[] args) {
        //Running implementation thread in Swing JFrame classes
        //Main window
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                window = new Window();
            }
        });

        Timer cmd = new Timer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        cmd.schedule(new TimerTask() {
            @Override
            public void run() {
                String command = null;
                try {
                    command = reader.readLine();
                    System.out.println(checkCommand(command));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000);
    }

    public static String checkCommand(String command) {
        if (command.substring(0, 5).equals("/ban ")) {
            return window.banUser(command.substring(5, command.length()));
        }
        return "This command is not found!";
    }
}
