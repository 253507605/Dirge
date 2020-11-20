package com.dirge.utils;

import java.awt.*;
import java.util.Random;

public class VerifyCode {
    private int width = 100;
    private int height = 50;
    private String[] fontNames = {"宋体","楷体","隶书","微软雅黑"};
    //定义背景颜色为白色
    private Color bgColor = new Color(255,255,255);

    private Random random = new Random();
    private String text;

    /**
     * 获取一个随机的颜色
     * @return
     */
    private Color randomColor(){
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        return new Color(red,green,blue);
    }

    /*private Font randomFont(){
        String name =
        return new Font();
    }*/
}
