package com.tedu.manager;

import com.tedu.controller.GameThread;
import com.tedu.element.GameElement;
import com.tedu.element.MapObj;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class GameLoader {

    private static ElementManager EM = ElementManager.getManager();

    private static Properties pro = new Properties();

    public static Map<String, ImageIcon> imgMap = new HashMap<>();
    // 用于扩展多动作
    public static Map<String, List<ImageIcon>> imgMaps = new HashMap<>();

    // 地图加载
    public static void mapLoad(int mapID){
        String mapName = "com/tedu/text/" + mapID + ".map";
//        System.out.println(mapName);
        InputStream inputStream = GameThread.class.getClassLoader().getResourceAsStream(mapName);
//        System.out.println(inputStream);
        if (inputStream == null){
            System.out.println("地图读取异常");
            return;
        }

        try {
            pro.clear();
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

    public static void loadImg(){
        String texturl = "com/tedu/text/GameData.pro";
        InputStream inputStream = GameLoader.class.getClassLoader().getResourceAsStream(texturl);

        pro.clear();

        try {
            pro.load(inputStream);
            Set<Object> objects = pro.keySet();
            for (Object o : objects) {
                String value = pro.getProperty(o.toString());
                imgMap.put(o.toString(), new ImageIcon(value));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
