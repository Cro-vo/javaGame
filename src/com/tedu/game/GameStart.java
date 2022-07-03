package com.tedu.game;

import com.tedu.controller.GameListener;
import com.tedu.controller.GameThread;
import com.tedu.show.GameJFrame;
import com.tedu.show.GameMainJPanel;

/**
 * 程序入口
 * @author 聆风旖旎
 *
 */
public class GameStart {
	public static void main(String[] args) {
		GameJFrame gf = new GameJFrame();

		GameMainJPanel gp = new GameMainJPanel();
		gf.setjPanel(gp);

		GameListener listener = new GameListener();
		gf.setKeyListener(listener);

		GameThread thread = new GameThread();
		gf.setThread(thread);

		gf.start();



	}
}
