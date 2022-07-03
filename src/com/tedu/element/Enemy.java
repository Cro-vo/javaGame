package com.tedu.element;

import com.tedu.show.GameJFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy extends ElementObj {


    @Override
    public ElementObj createElement(String str) {

        Random random = new Random();
        int x =  random.nextInt(GameJFrame.GameX);
        int y = random.nextInt(GameJFrame.GameY);
        this.setX(x);
        this.setY(y);
        this.setIcon(new ImageIcon("image/tank/bot/bot_up.png"));
        this.setH(getIcon().getIconHeight());
        this.setW(getIcon().getIconWidth());

        return this;
    }

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(),
                this.getX(),
                this.getY(),
                this.getW(),
                this.getH(),
                null);
    }

}
