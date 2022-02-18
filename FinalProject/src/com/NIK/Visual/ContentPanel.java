package com.NIK.Visual;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ContentPanel extends JPanel {
    private JLabel value;
    private JTextField valueField;
    private JPasswordField valueFieldPassword;
    public ContentPanel(Color backgroundColor, Color foregroundColor, String title, int type) {
        setLayout(new GridLayout(2, 1));
        setBackground(backgroundColor);
        setPreferredSize(new Dimension(100, 50));
        JLabel titleText = new JLabel(title);
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        titleText.setForeground(foregroundColor);
        add(titleText);

        setAlignmentX(Component.RIGHT_ALIGNMENT);

        switch (type) {
            case 0: {
                JPanel valueFieldPanel = new JPanel();
                valueFieldPanel.setBackground(Color.BLACK);
                valueFieldPanel.setLayout(new BoxLayout(valueFieldPanel, BoxLayout.X_AXIS));
                valueFieldPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

                setBorder(valueFieldPanel, 128, 8, 128, 8);

                valueField = new JTextField();
                valueField.setPreferredSize(new Dimension(50, 20));
                valueField.setMinimumSize(new Dimension(10, 20));
                valueField.setHorizontalAlignment(JTextField.CENTER);

                valueFieldPanel.add(valueField);
                add(valueFieldPanel);
                break;
            }
            case 1: {
                value = new JLabel("-----");
                value.setForeground(foregroundColor);
                value.setHorizontalAlignment(SwingConstants.CENTER);
                add(value);
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                JPanel valueFieldPanel = new JPanel();
                valueFieldPanel.setBackground(Color.BLACK);
                valueFieldPanel.setLayout(new BoxLayout(valueFieldPanel, BoxLayout.X_AXIS));
                valueFieldPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

                setBorder(valueFieldPanel, 128, 8, 128, 8);

                valueFieldPassword = new JPasswordField();
                valueFieldPassword.setPreferredSize(new Dimension(50, 20));
                valueFieldPassword.setMinimumSize(new Dimension(10, 20));
                valueFieldPassword.setHorizontalAlignment(JTextField.CENTER);

                valueFieldPanel.add(valueFieldPassword);
                add(valueFieldPanel);
                break;
            }
        }
    }

    public ContentPanel(Color backgroundColor, Color foregroundColor, String title, JButton login, JButton register) {
        setLayout(new GridLayout(2, 1));
        setBackground(backgroundColor);
        setPreferredSize(new Dimension(100, 50));
        JLabel titleText = new JLabel(title);
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        titleText.setForeground(foregroundColor);
        add(titleText);

        setAlignmentX(Component.RIGHT_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(login);
        buttonPanel.add(register);
        add(buttonPanel);
    }

    public ContentPanel(Color backgroundColor, Color foregroundColor, String title, JButton[] btn) {
        setLayout(new GridLayout(2, 1));
        setBackground(backgroundColor);
        setPreferredSize(new Dimension(100, 50));
        JLabel titleText = new JLabel(title);
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        titleText.setForeground(foregroundColor);
        add(titleText);

        setAlignmentX(Component.RIGHT_ALIGNMENT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setLayout(new GridLayout(1, btn.length));

        for (int i = 0; i < btn.length; i++) {
            buttonPanel.add(btn[i]);
        }

        add(buttonPanel);
    }

    public void setValue(long score) {
        value.setText("Score: " + Long.toString(score));
        revalidate();
        repaint();
    }

    public void setBorder(JPanel obj, int left, int top, int right, int bottom) {
        Border margin = new EmptyBorder(top, left, bottom, right);
        obj.setBorder(margin);
    }

    public String getTextFiled() {
        return valueField.getText();
    }

    public String getPassField() {
        return valueFieldPassword.getText();
    }
}
