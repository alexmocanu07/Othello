package utils;



import javax.swing.*;

public class CoordButton {

    private JButton button;
    private Point coord;
    private String color;



    public CoordButton(JButton button, Point coord) {
        this.button = button;
        this.coord = coord;
        this.color = "none";
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public Point getCoord() {
        return coord;
    }

    public void setCoord(Point coord) {
        this.coord = coord;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
