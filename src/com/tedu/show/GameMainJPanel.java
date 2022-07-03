package com.tedu.show;

import com.tedu.element.ElementObj;
import com.tedu.element.Player;
import com.tedu.manager.ElementManager;
import com.tedu.element.GameElement;


import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class GameMainJPanel extends JPanel implements Runnable {
    private ElementManager EM = null;



    public GameMainJPanel(){
        init();

    }

    public void init() {
        EM = ElementManager.getManager();
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

//        Map<GameElement, List<ElementObj>> elements = EM.getGameElements();
        // GameElement.values()获取枚举类的顺序为定义的顺序
        for (GameElement ge : GameElement.values()) {
            List<ElementObj> elementsByKey = EM.getElementsByKey(ge);
            for (ElementObj obj : elementsByKey) {
                obj.showElement(g);

            }
        }



    }

    @Override
    public void run() {
        while (true) {
            this.repaint();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
