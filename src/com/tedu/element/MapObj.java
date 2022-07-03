package com.tedu.element;

import javax.swing.*;
import java.awt.*;

public class MapObj extends ElementObj {

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
            case "GRASS":icon = new ImageIcon("image/wall/grass.png");break;
            case "BRICK":icon = new ImageIcon("image/wall/brick.png");break;
            case "IRON":icon = new ImageIcon("image/wall/iron.png");break;
            case "RIVER":icon = new ImageIcon("image/wall/river.png");break;
        }
        this.setX(Integer.parseInt(split[1]));
        this.setY(Integer.parseInt(split[2]));
        this.setW(icon.getIconWidth());
        this.setH(icon.getIconHeight());
        this.setIcon(icon);

        return this;
    }
}
