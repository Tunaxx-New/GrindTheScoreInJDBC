package com.NIK.Users;

abstract class User implements ContactSQL {
    private int id;
    private String login;
    private String password;
    private long score;
    private char[] bits;
    private int width;
    private int height;
    private int count;

    public User(int id, String login, String password, long score, int width, int height) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.score = score;
        this.bits = new char[width * height];
        this.width = width;
        this.height = height;
        for (int i = 0; i < this.bits.length; i++) {
            bits[i] = 1;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getScore() {
        return score;
    }

    public String getLogin() {
        return login;
    }

    public void setBits(int width, int height) {
        this.width = width;
        this.height = height;
        this.bits = new char[width * height];
        for (int i = 0; i < this.bits.length; i++) {
            bits[i] = 1;
        }
    }

    public int getHeight() {
        return height;
    }

    public void reachScore() {
        int scorePP = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int index = i * width + j;
                switch (bits[index]) {
                    case 'A':
                    {
                        //LEFT and RIGHT wire
                        scorePP += LeftBonus(index, j);
                        scorePP += RightBonus(index, j, width - 1);
                        break;
                    }
                    case 'B':
                    {
                        // UP and DOWN wire
                        scorePP += UpBonus(index, i, width);
                        scorePP += DownBonus(index, i, width, height - 1);
                        break;
                    }
                    case 'C':
                    {
                        //   ^
                        //   |<-^
                        //      |<-*
                        scorePP += LeftBonusBU(index, j, i, width);
                        scorePP += LeftBonusBD(index, j, i, width);
                        scorePP += RightBonusBU(index, j, i, width);
                        scorePP += RightBonusBD(index, j, i, width);
                        break;
                    }
                }
            }
        }
        setScore(getScore() + scorePP);
    }

    public int LeftBonus(int index, int j) {
        if (j != 0) {
            if (bits[index - 1] != '\u0001') return LeftBonus(index - 1, j - 1) + 1;
        }
        return 1;
    }
    public int RightBonus(int index, int j, int max) {
        if (j != max) {
            if (bits[index + 1] != '\u0001') return RightBonus(index + 1, j + 1, max) + 1;
        }
        return 1;
    }

    public int UpBonus(int index, int i, int decrement) {
        if (i != 0) {
            if (bits[index - decrement] != '\u0001') return UpBonus(index - decrement, i - 1, decrement) + 1;
        }
        return 1;
    }
    public int DownBonus(int index, int i, int decrement, int max) {
        if (i != max) {
            if (bits[index + decrement] != '\u0001') return DownBonus(index + decrement, i + 1, decrement, max) + 1;
        }
        return 1;
    }

    public int LeftBonusBD(int index, int j, int i, int decrement) {
        if (j != 0) {
            if (bits[index - 1] != '\u0001') return DownBonusBL(index - 1, j - 1, i, decrement) * 2;
        }
        return 1;
    }
    public int RightBonusBD(int index, int j, int i, int decrement) {
        if (j != width - 1) {
            if (bits[index + 1] != '\u0001') return DownBonusBR(index + 1, j + 1, i, decrement) * 2;
        }
        return 1;
    }
    public int LeftBonusBU(int index, int j, int i, int decrement) {
        if (j != 0) {
            if (bits[index - 1] != '\u0001') return UpBonusBL(index - 1, j - 1, i, decrement) * 2;
        }
        return 1;
    }
    public int RightBonusBU(int index, int j, int i, int decrement) {
        if (j != width - 1) {
            if (bits[index + 1] != '\u0001') return UpBonusBR(index + 1, j + 1, i, decrement) * 2;
        }
        return 1;
    }
    public int UpBonusBR(int index, int j, int i, int decrement) {
        if (i != 0) {
            if (bits[index - decrement] != '\u0001') return RightBonusBU(index - decrement, j, i - 1, decrement) * 2;
        }
        return 1;
    }
    public int UpBonusBL(int index, int j, int i, int decrement) {
        if (i != 0) {
            if (bits[index - decrement] != '\u0001') return LeftBonusBU(index - decrement, j, i - 1, decrement) * 2;
        }
        return 1;
    }
    public int DownBonusBL(int index, int j, int i, int decrement) {
        if (i != height - 1) {
            if (bits[index + decrement] != '\u0001') return LeftBonusBD(index + decrement, j, i + 1, decrement) * 2;
        }
        return 1;
    }
    public int DownBonusBR(int index, int j, int i, int decrement) {
        if (i != height - 1) {
            if (bits[index + decrement] != '\u0001') return RightBonusBD(index + decrement, j, i + 1, decrement) * 2;
        }
        return 1;
    }

    public void setPixel(int x, int y, char a) {
        bits[y * width + x] = a;
    }

    public int getWidth() {
        return width;
    }

    public String getPassword() {
        return password;
    }

    public char[] getBits() {
        return bits;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBits(String text) {
        for (int i = 0; i < text.length(); i++) {
            this.bits[i] = text.charAt(i);
        }
    }
}
