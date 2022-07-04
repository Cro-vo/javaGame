package com.tedu.element;

import javax.swing.*;
import java.awt.*;

public class MapObj extends ElementObj {

    private int HP;
    private String name;

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(),
                this.getX(),
                this.getY(),
                this.getW(),
                this.getH(),
                null);
    }

    @Override  // str: 墙类型, x, y
    public ElementObj createElement(String str) {
//        System.out.println(str);
        String[] split = str.split(",");
        ImageIcon icon = null;
        switch (split[0]) {
            case "GRASS":icon = new ImageIcon("image/wall/grass.png");
                        this.name="GRASS";break;
            case "BRICK":icon = new ImageIcon("image/wall/brick.png");
                        this.HP=1;this.name="BRICK";break;
            case "IRON":icon = new ImageIcon("image/wall/iron.png");
                        this.HP=3;this.name="IRON";break;
            case "RIVER":icon = new ImageIcon("image/wall/river.png");
                        this.name="RIVER";break;
        }
        this.setX(Integer.parseInt(split[1]));
        this.setY(Integer.parseInt(split[2]));
        this.setW(icon.getIconWidth());
        this.setH(icon.getIconHeight());
        this.setIcon(icon);

        return this;
    }

    @Override
    public void setAlive(boolean alive) {
        if ("IRON".equals(this.name) || "BRICK".equals(this.name)){
            this.HP--;
            if(this.HP > 0) {
                return;
            }
        }
        if ("GRASS".equals(this.name) || "RIVER".equals(this.name)){
            return;
        }
        super.setAlive(alive);
    }
}
