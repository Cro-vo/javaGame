package com.tedu.show;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 说明 游戏窗体，实现最大最小化，需要嵌入面板Panel
 * @author 聆风旖旎
 *
 */
public class GameJFrame extends JFrame {
	public static int GameX = 778;
	public static int GameY = 627;
	
	private JPanel jPanel = null;// 面板
	private KeyListener keyListener = null; // 键盘监听
	private MouseListener mouseListener = null;// 鼠标监听
	private MouseMotionListener mouseMotionListener = null;
	private Thread thread = null;// 游戏主线程
	
	
	public void start() {
		if (jPanel != null) {
			this.add(jPanel);
		}
		if (keyListener != null) {
			this.addKeyListener(keyListener);
		}
		if (mouseListener != null) {
			this.addMouseListener(mouseListener);
		}
		if (mouseMotionListener != null) {
			this.addMouseMotionListener(mouseMotionListener);
		}
		if (thread != null) {
			thread.start();
		}
		this.setVisible(true);

		// 界面刷新,启动MainJPanel的线程 来刷新
		if (this.jPanel instanceof Runnable) {
			new Thread((Runnable) this.jPanel).start();

		}


	}
	
	
	// set注入
	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}
	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}




	// 构造函数
	public GameJFrame() {
		init();
	}

	// 初始化
	public void init() {
		this.setSize(GameX, GameY);
		this.setTitle("Game... ...");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLocationRelativeTo(null);
		
	}
	
	
}
