package com.tedu.controller;

import com.tedu.element.*;
import com.tedu.manager.ElementManager;

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
    private static Properties pro = new Properties();

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
        mapLoad(5);
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

            elementCollision();

            gameTime++;
            try {
                Thread.sleep(20); // 1s 刷新 50次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void elementCollision() {
        List<ElementObj> enemys = EM.getElementsByKey(GameElement.ENEMY);
        List<ElementObj> bullets = EM.getElementsByKey(GameElement.BULLET);
        for (int i = 0;i < enemys.size(); i++){
            ElementObj enemy = enemys.get(i);

            for (int j = 0; j < bullets.size(); j++){
                ElementObj bullet = bullets.get(j);

                if (enemy.collision(bullet)){
                    enemy.setAlive(false);
                    bullet.setAlive(false);
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

    // 地图加载
    public void mapLoad(int mapID){
        String mapName = "com/tedu/text/" + mapID + ".map";
        InputStream inputStream = GameThread.class.getClassLoader().getResourceAsStream(mapName);
//        System.out.println(inputStream);
        if (inputStream == null){
            System.out.println("地图读取异常");
            return;
        }

        try {
            pro.load(inputStream);
            Enumeration<?> names = pro.propertyNames();
            while (names.hasMoreElements()) {
                String key = (String) names.nextElement();
//                System.out.println(pro.getProperty(key));
                String[] arrs = pro.getProperty(key).split(";");
                for (int i = 0; i < arrs.length; i++) {
                    EM.addElement(new MapObj().createElement(key + "," + arrs[i]), GameElement.MAP);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

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
