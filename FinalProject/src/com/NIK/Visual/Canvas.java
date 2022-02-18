package com.NIK.Visual;

import com.NIK.Users.CheckableUser;
import com.NIK.Users.InputUser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {
    private BufferedImage bi;
    private ImageIcon canv;
    private CheckableUser user;
    private int ratioW;
    private int ratioH;
    private int width;
    private int height;
    private JLabel img;

    private int dx = 0;
    private int dy = 0;

    public Canvas(int width, int height, CheckableUser user, int uwidth, int uheight) {
        setLayout(new BorderLayout());

        bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        canv = new ImageIcon(bi);
        img = new JLabel(canv);
        img.setVerticalAlignment(SwingConstants.CENTER);
        add(img);

        ratioW = width / uwidth;
        ratioH = height / uheight;
        this.width = width;
        this.height = height;

        this.user = user;
    }

    public JLabel getImg() {
        return img;
    }

    public void rsize(int w, int h) {
        dx = ((w / 2) - width) / 2;
        //30 - is Title panel of window
        dy = (h - height - 30) / 2;
    }

    public void rsize(InputUser user) {
        ratioW = width / user.getWidth();
        ratioH = height / user.getHeight();
        bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        canv = new ImageIcon(bi);
        img.setIcon(canv);
    }

    public void bitMap(char[] map, CheckableUser user) {
        int w = user.getWidth();
        for (int i = 0; i < user.getHeight(); i++) {
            for (int j = 0; j < w; j++) {
                switch (map[i * w + j]) {
                    case 'A': {
                        rectangleDraw(j*ratioH, i*ratioW, ratioW, ratioH, Color.RED);
                        break;
                    }
                    case 'B': {
                        rectangleDraw(j*ratioH, i*ratioW, ratioW, ratioH, Color.YELLOW);
                        break;
                    }
                    case 'C': {
                        rectangleDraw(j*ratioH, i*ratioW, ratioW, ratioH, Color.CYAN);
                        break;
                    }
                }
            }
        }
        revalidate();
        repaint();
    }

    public void setRect(int x, int y, char a, CheckableUser user) {
        int x0 = x - x % ratioW - dx;
        int y0 = y - y % ratioH - dy;

        Color color = Color.BLACK;
        switch (a) {
            case 'A':
                color = Color.RED;
                break;
            case 'B':
                color = Color.YELLOW;
                break;
            case 'C':
                color = Color.CYAN;
                break;
            case '\u0001':
                color = Color.BLACK;
                break;
        }

        rectangleDraw(x0, y0, ratioW, ratioH, color);
        canv.setImage(bi);

        user.setPixel(x0 / ratioW, y0 / ratioH, a);

        revalidate();
        repaint();
    }

    public boolean isRect(int x, int y, CheckableUser user) {
        int x0 = x - x % ratioW - dx;
        x0 /= ratioW;
        int y0 = y - y % ratioH - dy;
        y0 /= ratioH;
        if (user.getBits()[y0 * user.getWidth() + x0] != '\u0001') return true;
        return false;
    }

    public void rectangleDraw(int x, int y, int width, int height, Color color) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixel(x + j, y + i, color);
            }
        }
    }

    public void setPixel(int x, int y, Color color) {
        bi.setRGB(x, y, color.getRGB());
        canv.setImage(bi);
    }
    public void pixel(int x, int y, Color color) {
        bi.setRGB(x, y, color.getRGB());
    }
}
