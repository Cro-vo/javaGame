package com.tedu.controller;

import com.tedu.element.ElementObj;
import com.tedu.element.GameElement;
import com.tedu.manager.ElementManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 说明 监听类，用于监听用户操作
 */
public class GameListener implements KeyListener {

    private final ElementManager EM = ElementManager.getManager();

    private Set<Integer> pressKeySet = new HashSet<Integer>();


    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 按下 左37 上38 右39 下40
     */
    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("pressed" + e.getKeyCode());

        if (pressKeySet.contains(e.getKeyCode())){
            // 已经按下
            return;
        }
        pressKeySet.add(e.getKeyCode());

        // 按下
        List<ElementObj> players = EM.getElementsByKey(GameElement.PLAYER);
        for (ElementObj player : players) {
            player.keyClick(true, e.getKeyCode());
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (!pressKeySet.contains(e.getKeyCode())){
            return;
        }
        pressKeySet.remove(e.getKeyCode());

        // 松开
        List<ElementObj> players = EM.getElementsByKey(GameElement.PLAYER);
        for (ElementObj player : players) {
            player.keyClick(false, e.getKeyCode());
        }

    }
}
