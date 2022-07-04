package com.tedu.element;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameLoader;
import com.tedu.show.GameJFrame;
import com.tedu.show.GameMainJPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Player extends ElementObj {

    private boolean left = false;
    private boolean down = false;
    private boolean right = false;
    private boolean up = false;

    // 朝向
    private String forward = "up";
    // 开火状态
    private boolean pkType = false;


    public Player(int x, int y, int w, int h, ImageIcon icon) {
        super(x, y, w, h, icon);

    }

    @Override
    public void move() {
        if (left == true && this.getX()>0) {
            this.setX(this.getX() - 5);
        }
        if (up == true && this.getY()>0) {
            this.setY(this.getY() - 5);
        }
        if (right == true && this.getX()<GameJFrame.GameX - this.getW()) {
            this.setX(this.getX() + 5);
        }
        if (down == true && this.getY()<GameJFrame.GameY - this.getH()) {
            this.setY(this.getY() + 5);
        }
    }

    private long shootTime=0L;
    @Override
    protected void fire(long gameTime) {
        if (gameTime - shootTime < 13) {
            // 时间间隔小于0.25s，则禁止发射
            return;
        }

        if (!this.pkType) {
            return;
        }

        shootTime = gameTime;

//        new Bullet();   当实例化对象的时候参数过多，可以使用工厂生产

        ElementObj bullet = new Bullet().createElement(this.toString());
        ElementManager.getManager().addElement(bullet, GameElement.BULLET);

    }

    @Override
    public void keyClick(boolean bl, int key) {
//        System.out.println(key);
        if (bl) {
            switch (key) {
                case 37:
                    up = false;
                    down = false;
                    right = false;
                    left = true;
                    this.forward = "left";
                    break;
                case 38:
                    left = false;
                    right = false;
                    down = false;
                    up = true;
                    this.forward = "up";
                    break;
                case 39:
                    up = false;
                    down = false;
                    left = false;
                    right = true;
                    this.forward = "right";
                    break;
                case 40:
                    left = false;
                    right = false;
                    up = false;
                    down = true;
                    this.forward = "down";
                    break;
                case 32:
                    pkType = true;
                    break;
            }
        } else {
            switch (key) {
                case 37:
                    left = false;
                    break;
                case 38:
                    up = false;
                    break;
                case 39:
                    right = false;
                    break;
                case 40:
                    down = false;
                    break;
                case 32:
                    pkType = false;
                    break;
            }
        }

    }

    @Override
    protected void updateImage() {
        this.setIcon(GameLoader.imgMap.get(forward));
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

    @Override
    public String toString() {
        return "x:" + this.getX() + ",y:" + this.getY() + ",forward:" + this.forward;

    }
}
