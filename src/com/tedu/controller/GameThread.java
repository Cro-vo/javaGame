package com.tedu.controller;

import com.tedu.element.*;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameLoader;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * 说明 游戏主线程 控制游戏资源和自动化
 */

public class GameThread extends Thread {
    private ElementManager EM = ElementManager.getManager();

    @Override
    public void run() {
        while(true) { // 扩展 true可以控制游戏结束
            // 游戏开始前  读取进度条，加载游戏资源（场景资源）
            gameLoad();
            // 游戏进行中
            gameRun();
            // 游戏场景结束(切换) 游戏资源的切换（场景资源）
            gameOver();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 游戏开始前
     */
    private void gameLoad() {
        load();
        GameLoader.mapLoad(5);
        GameLoader.loadImg();
    }

    /**
     * 游戏进行中
     * 1. 自动化玩家的移动，碰撞，死亡
     * 2. 新元素的增加
     * 3. 游戏暂停
     */
    private void gameRun() {
        long gameTime = 0L; // 用于记录游戏时间
        while(true) {// 扩展 true可以用于控制关卡结束

            moveAndUpdate(gameTime);

            elementCollision(GameElement.ENEMY, GameElement.BULLET);
            elementCollision(GameElement.MAP, GameElement.BULLET);

            gameTime++;
            try {
                Thread.sleep(20); // 1s 刷新 50次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void elementCollision(GameElement ge1, GameElement ge2) {
        List<ElementObj> ListA = EM.getElementsByKey(ge1);
        List<ElementObj> ListB = EM.getElementsByKey(ge2);
        for (int i = 0;i < ListA.size(); i++){
            ElementObj element1 = ListA.get(i);

            for (int j = 0; j < ListB.size(); j++){
                ElementObj element2 = ListB.get(j);

                if (element1.collision(element2)){
                    element1.setAlive(false);
                    element2.setAlive(false);
                    break;
                }
            }
        }


    }

    private void moveAndUpdate(long gameTime) {
        for (GameElement ge : GameElement.values()) {
            List<ElementObj> list = EM.getElementsByKey(ge);
            // 对集合操作时，如果需要修改数据，尽量不要使用迭代器
            // for (int i = list.size()-1; i>=0; i--)
            for (int i = 0; i < list.size(); i++) {
                ElementObj obj = list.get(i);
                if (!obj.isAlive()) {
                    // 开启 死亡方法
                    obj.die();
                    list.remove(i--);
                    continue;
                }
                obj.model(gameTime);
            }

        }
    }

    /**
     * 游戏场景结束
     */
    private void gameOver() {
    }




    public void load(){

        ImageIcon icon = new ImageIcon("image/tank/play1/player1_up.png");
        ElementObj obj = new Player(100, 100, icon.getIconWidth(), icon.getIconHeight(), icon);
        EM.addElement(obj, GameElement.PLAYER);

        for (int i = 0; i < 10; i++) {
            EM.addElement(new Enemy().createElement(""), GameElement.ENEMY);
        }


    }


}
