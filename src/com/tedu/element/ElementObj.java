package com.tedu.element;

import javax.swing.*;
import java.awt.*;

/**
 * 元素基类
 * @author 聆风旖旎
 *
 */

public abstract class ElementObj {
    private int x;
    private int y;
    private int w;
    private int h;
    private ImageIcon icon;
    private boolean isAlive = true;



    public ElementObj() {
    }



    public ElementObj(int x, int y, int w, int h, ImageIcon icon) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.icon = icon;
    }

    public abstract void showElement(Graphics g);

    /**
     * @说明 使用父类定义的键盘接收事件的方法，需要此功能的子类重写此方法。(也可以使用接口，但是需要在监听类进行类型转化)
     * @param bl 按下或松开，true代表按下，false代表松开 ; key 按下的键
     */
    public void keyClick(boolean bl, int key) {

    }

    /**
     * 模板模式 有先后顺序,定义为final后子类无法重写本方法
     * 包含 1.换装 2.移动 3.发射
     * 线程控制功能函数，至于功能是否开启，需要对象自行判断
     * @param gameTime
     */
    public final void model(long gameTime) {
        updateImage();
        move();
        fire(gameTime);
    }

    public Rectangle getRectangle(){
        return new Rectangle(x, y, w, h);
    }

    /**
     * 检测是否发生碰撞
     */
    public boolean collision(ElementObj obj){
        return this.getRectangle().intersects(obj.getRectangle());

    }


    /**
     * 死亡方法， 例如死后生成掉落、死亡特效等
     */
    public void die(){

    }

    protected void updateImage(){}

    /**
     * 移动
     */
    protected void move() {}

    protected void fire(long gameTime){}

    public ElementObj createElement(String str) {

        return null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
