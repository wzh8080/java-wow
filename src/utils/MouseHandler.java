package utils;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
/**
 * 鼠标 相关
 * 1. 获取鼠标当前坐标位置
 * @author Administrator
 *
 */
public class MouseHandler {
	private Robot robot;
	private int sleep=100;//间隔时间
	public MouseHandler (){
		try {
			robot = new Robot();
			// 设置默认休眠时间
	        robot.setAutoDelay(300);
		} catch (AWTException e) {
			System.out.println("初始化Robot失败...");
			e.printStackTrace();
		}
	}
	public MouseHandler (Robot r){
		robot = r;
	}
	/**
	 * 获取鼠标当前位置的坐标
	 */
	public int[] getMouseXY(){
		//取坐标
		int[] arr = new int[2];
		try {
			Thread.sleep(500);
			PointerInfo pinfo = MouseInfo.getPointerInfo();
			Point p = pinfo.getLocation();
			arr[0] = (int) p.getX();
			arr[1] = (int) p.getY();
			System.out.println(Arrays.toString(arr));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	/**
	 * 鼠标点击事件
	 * @param x
	 * @param y
	 * @throws AWTException
	 */
	public void click(int x, int y){
		robot.delay(500); 
		robot.mouseMove((int)x, (int)y); //移动到 mouseMove
		click();
	}
	public void click(String xy){
		xy = xy.replaceAll("\\s*", "");
		int x = Integer.valueOf(xy.split(",")[0]);
		int y = Integer.valueOf(xy.split(",")[1]);
		click(x, y);
	}
	/**
	 * 双击
	 * @param x
	 * @param y
	 */
	public void doubleClick(int x, int y){
		robot.delay(500); 
		robot.mouseMove((int)x, (int)y); 
		doubleClick();
	}
	public void doubleClick(String xy){
		xy = xy.replaceAll("\\s*", "");
		int x = Integer.valueOf(xy.split(",")[0]);
		int y = Integer.valueOf(xy.split(",")[1]);
		robot.delay(500); 
		robot.mouseMove((int)x, (int)y); 
		doubleClick();
	}
	public void doubleClick(){
		robot.delay(300); //间隔时间
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//按下左键（BUTTON后面的1改成2和3即可，2是按下滚轮，3是按下鼠标右键。）
		robot.delay(200); //间隔时间
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);  //鼠标抬起方法。
		robot.delay(200); //间隔时间
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//按下左键（BUTTON后面的1改成2和3即可，2是按下滚轮，3是按下鼠标右键。）
		robot.delay(200); //间隔时间
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);  //鼠标抬起方法。
	}
	/**
	 * 左键拖动（从当前位置拖动到x,y）
	 */
	public void drag(int x, int y){
		int n = ThreadLocalRandom.current().nextInt(482)+56;
		robot.delay(n);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseMove((int)x, (int)y);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	/**
	 * 拖动（从当前位置拖动到x,y）
	 */
	public void drag(int x1, int y1,int x2, int y2){
		
	}
	public void click(){
		//int sleep = ThreadLocalRandom.current().nextInt(140)+80;  //80~220
		robot.delay(300); //间隔时间
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//按下左键（BUTTON后面的1改成2和3即可，2是按下滚轮，3是按下鼠标右键。）
		robot.delay(200); //间隔时间
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);  //鼠标抬起方法。
	}
	/**
	 * 随机在一定范围内拖动 异形
	 */
	public void drag() {
		//[282, 146] [1076, 628]
//		int x = ThreadLocalRandom.current().nextInt(794)+282;
//		int y = ThreadLocalRandom.current().nextInt(482)+146;
		int x =200;
		int y = 200;
		click(x,y);
		double k = 0.1;//斜率
		for(int i=0;i<10;i++){
			x = x + i;
			y = y + 0;
			drag(x,y);
			k = k+0.05;;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		MouseHandler m = new MouseHandler();
		Thread.sleep(1000);
		m.drag();
		//m.drag(200, 200);
	}
}
