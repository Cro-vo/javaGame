package com.tedu.element;

import com.tedu.show.GameJFrame;
import com.tedu.show.GameMainJPanel;

import javax.swing.*;


import java.awt.*;

import static java.awt.Color.red;

public class Bullet extends ElementObj {

    private int attack;
    private int speed = 15;
    private String forward;

    public Bullet(){}
//    private Bullet(int x, int y, int w, int h, ImageIcon icon, String forward) {
//        super(x, y, w, h, icon);
//        this.attack = 1;
//        this.speed = 3;
//    }

    @Override
    public void die() {

    }

    @Override
    protected void move() {

        if (this.getX() < 0 || this.getX() > GameJFrame.GameX
            || this.getY() < 0 || this.getY() > GameJFrame.GameY) {
            this.setAlive(false);
            return;
        }

        switch (forward){
            case "up":this.setY(this.getY() - speed);break;
            case "down":this.setY(this.getY() + speed);break;
            case "left":this.setX(this.getX() - speed);break;
            case "right":this.setX(this.getX() + speed);break;
        }
    }

    @Override
    public ElementObj createElement(String str) {
        // str: x,y,forward
        String[] split = str.split(","); // x:1
        for (String str1 : split) {
            String[] split1 = str1.split(":");
            switch (split1[0]){
                case "x":this.setX(Integer.parseInt(split1[1]));break;
                case "y":this.setY(Integer.parseInt(split1[1]));break;
                case "forward":this.forward = split1[1];break;
            }
        }
        this.setH(6);
        this.setW(6);

        // 修正子弹射出的位置
        switch (this.forward) {
            case "left":this.setY(this.getY()+14);this.setX(this.getX()-7);break;
            case "right":this.setY(this.getY()+14);this.setX(this.getX()+35);break;
            case "up":this.setX(this.getX()+14);this.setY(this.getY()-7);break;
            case "down":this.setY(this.getY()+35);this.setX(this.getX()+14);break;
        }

        return this;
    }

    @Override
    public void showElement(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(this.getX(), this.getY(), this.getW(), this.getH());

    }
}
