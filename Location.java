import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Location {
    private String name, abbv;
    private int x, y;

    public Location(String name, String abbv, int x, int y) {
        this.name = name;
        this.abbv = abbv.toUpperCase();
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public String getAbbv() {
        return abbv;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        char[] charArray = abbv.toCharArray();
        for (int i = 0; i < 3; i++) {
            hashCode += (charArray[i] - 65) * (Math.pow(26, 2 - i));
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        return ((Location) o).getAbbv().equals(abbv);
    }

    public void draw(Graphics g, Color c) {
        g.setColor(c);
        g.drawOval(x - 10, y - 10, 20, 20);
        g.setFont(new Font("Arial", 3, 10));
        g.drawString(name + " (" + abbv + ")", x + 12, y + 3);
    }
}