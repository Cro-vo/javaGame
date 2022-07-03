package com.tedu.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tedu.element.ElementObj;
import com.tedu.element.GameElement;

/**
 * 
 * @author 聆风旖旎
 *
 */
public class ElementManager {
	
	// 元素集合
	private Map<GameElement, List<ElementObj>> gameElements;
	public Map<GameElement, List<ElementObj>> getGameElements() {
		return gameElements;
	}
	
	// 单例
	private static ElementManager EM = null;
	public static synchronized ElementManager getManager() {
		if (EM == null) {
			EM = new ElementManager();
		}
		return EM;
	}
	private ElementManager() {
		// 私有化构造方法，防止被其他类构造
		init();
	}
	
	// 可被重写，用于功能拓展
	public void init() {
		gameElements = new HashMap<GameElement, List<ElementObj>>();
		
//		gameElements.put(GameElement.PLAYER, new ArrayList<ElementObj>());
//		gameElements.put(GameElement.MAP, new ArrayList<ElementObj>());
//		gameElements.put(GameElement.ENEMY, new ArrayList<ElementObj>());
//		gameElements.put(GameElement.BOSS, new ArrayList<ElementObj>());
		for (GameElement ge : GameElement.values()){
			gameElements.put(ge, new ArrayList<ElementObj>());
		}

		
	}
	
	/**
	 * 添加元素
	 * @param obj， 元素对象
	 * @param ge，元素类型
	 */
	public void addElement(ElementObj obj, GameElement ge) {
		gameElements.get(ge).add(obj);
		
	}
	

	public List<ElementObj> getElementsByKey(GameElement ge){
		return gameElements.get(ge);
	}
	
	
	
	
	
	

}
