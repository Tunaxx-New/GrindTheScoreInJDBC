package com.NIK;

import com.NIK.Users.CheckableUser;
import com.NIK.Users.InputUser;
import com.NIK.Utilities.CheckException;
import com.NIK.Visual.Canvas;
import com.NIK.Visual.ContentPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Window extends JFrame implements WindowListener {
    private Database db;
    private CheckableUser user;
    private boolean isOp = false;

    private JButton loginButton;
    private JButton registerButton;
    private ContentPanel loginPanel;
    private ContentPanel passwordPanel;
    private ContentPanel scorePanel;

    private char activeChar = 'A';
    private JButton charA;
    private JButton charB;
    private JButton charC;

    private Timer updateScore;
    private Timer reachScore;
    private static int updateMilliseconds = 6000;
    private static int reachMilliseconds = 1000;

    public Window() {
        super("Final Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIcon("\\src\\com\\NIK\\assets\\icon.jpg");

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(size);

        getContentPane().setBackground(Color.BLACK);

        pack();
        setVisible(true);

        //Event listener
        addWindowListener(this);

        //Enable databse
        db = new Database();
        //!!!! НЕ БОЛЬШЕ 500 !!!!
        user = new CheckableUser(0, "", "", 0, 20, 20);

        //Every minute update score to server
        updateScore = new Timer();
        updateScore.schedule(new TimerTask() {
            @Override
            public void run() {
                user.setScore(user.getScore());
                db.update(user);
            }
        }, 0, updateMilliseconds);
        //Every seconds score
        reachScore = new Timer();
        reachScore.schedule(new TimerTask() {
            @Override
            public void run() {
                user.reachScore();
                if (scorePanel != null)
                    scorePanel.setValue(user.getScore());
            }
        }, 0, reachMilliseconds);

        //Creating Content---------
        Container content = getContentPane();
        content.setLayout(new GridLayout(0, 2));

        Canvas draw = new Canvas(500, 500, user, 20, 20);
        draw.getImg().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (user.getId() > 0) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (user.getCount() > 0) {
                            draw.setRect(e.getX(), e.getY(), activeChar, user);
                            user.setCount(user.getCount() - 1);
                            charA.setText(Integer.toString(user.getCount()));
                            charB.setText(Integer.toString(user.getCount()));
                            charC.setText(Integer.toString(user.getCount()));
                        } else {
                            System.out.println("No many squares!");
                        }
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        if (draw.isRect(e.getX(), e.getY(), user)) {
                            draw.setRect(e.getX(), e.getY(), '\u0001', user);
                            user.setCount(user.getCount() + 1);
                            charA.setText(Integer.toString(user.getCount()));
                            charB.setText(Integer.toString(user.getCount()));
                            charC.setText(Integer.toString(user.getCount()));
                        }
                    }
                } else if (user.getId() < 0) {
                    System.out.println("Ты кто? Ты забанен, блин. И нет я не буду тебя разбавнивать, потому что ты забанин блин");
                } else {
                    System.out.println("Please, sign in or register for play!");
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        content.add(draw);

        //Creating active buttons----
        loginButton = new JButton("Sign in");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setLogin(loginPanel.getTextFiled());
                user.setPassword(passwordPanel.getPassField());
                System.out.println(db.LoginUser(user, draw));
                isOp = db.checkOP(user);
            }
        });
        registerButton = new JButton("Sign up");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(user.setLoginPro(loginPanel.getTextFiled()));
                System.out.println(user.setPasswordPro(passwordPanel.getPassField()));
                try {
                    db.registerUser(user, draw);
                    isOp = db.checkOP(user);
                } catch (CheckException ex) {
                    System.out.println(ex);
                }
            }
        });
        //---------------------------

        GridBagConstraints gbc = new GridBagConstraints();

        Box box = Box.createVerticalBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        scorePanel = new ContentPanel(Color.BLACK, Color.WHITE, "Score", 1);
        box.add(scorePanel, gbc);

        loginPanel = new ContentPanel(Color.BLACK, Color.WHITE, "Login", 0);
        box.add(loginPanel, gbc);
        passwordPanel = new ContentPanel(Color.BLACK, Color.WHITE, "Password", 3);
        box.add(passwordPanel, gbc);

        box.add(new ContentPanel(Color.BLACK, Color.WHITE, "Actions", loginButton, registerButton), gbc);

        //Buttons
        charA = new JButton("RED");
        charA.setForeground(Color.BLACK);
        charA.setBackground(Color.RED);
        charA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeChar = 'A';
            }
        });

        charB = new JButton("YELLOW");
        charB.setForeground(Color.BLACK);
        charB.setBackground(Color.YELLOW);
        charB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeChar = 'B';
            }
        });

        charC = new JButton("CYAN");
        charC.setForeground(Color.BLACK);
        charC.setBackground(Color.CYAN);
        charC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeChar = 'C';
            }
        });

        JButton[] btns = new JButton[3];
        btns[0] = charA;
        btns[1] = charB;
        btns[2] = charC;

        box.add(new ContentPanel(Color.BLACK, Color.WHITE, "Square Types", btns), gbc);
        content.add(box);
        //-------------------------

        draw.rsize(getWidth(), getHeight());
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                draw.rsize(getWidth(), getHeight());
            }
        });
    }

    public GraphicsConfiguration createGC() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gs.getDefaultConfiguration();
        return gc;
    }

    public void setIcon(String path) {
        try {
            String filePath = new File("").getAbsolutePath();
            Image icon = ImageIO.read(new File(filePath.concat(path)));
            this.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBorder(JLabel obj, int left, int top, int right, int bottom) {
        Border margin = new EmptyBorder(top,left,bottom,right);
        obj.setBorder(margin);
    }
    public void setBorder(JPanel obj, int left, int top, int right, int bottom) {
        Border margin = new EmptyBorder(top,left,bottom,right);
        obj.setBorder(margin);
    }
    public void setBorder(JTextField obj, int left, int top, int right, int bottom) {
        Border margin = new EmptyBorder(top,left,bottom,right);
        obj.setBorder(margin);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        db.closeConnection();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        user.setScore(user.getScore());
        db.update(user);
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public String banUser(String login) {
        if (isOp) {
            db.banUser(login);
            return "Ban function for " + login + " is applied!";
        } else {
            return "Not enough rights for this command!";
        }
    }
}
