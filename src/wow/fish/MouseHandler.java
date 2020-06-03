package wow.fish;

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
	       // robot.setAutoDelay(300);
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
	public int[] getMouseXY(int[] arr){
		//取坐标
		try {
			//Thread.sleep(500);
			PointerInfo pinfo = MouseInfo.getPointerInfo();
			Point p = pinfo.getLocation();
			arr[0] = (int) p.getX();
			arr[1] = (int) p.getY();
			//System.out.println(Arrays.toString(arr));
		} catch (Exception e) {
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
	public void clickXY(int x, int y){
		robot.delay(1000); 
		robot.mouseMove((int)x, (int)y); //移动到 mouseMove
		robot.delay(500); 
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//按下左键（BUTTON后面的1改成2和3即可，2是按下滚轮，3是按下鼠标右键。）
		robot.delay(200); //间隔时间
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);  //鼠标抬起方法。
		robot.delay(1000);
	}
	public void click(){
		int sleep = ThreadLocalRandom.current().nextInt(140)+80;  //80~220
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//按下左键（BUTTON后面的1改成2和3即可，2是按下滚轮，3是按下鼠标右键。）
		robot.delay(sleep); //间隔时间
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);  //鼠标抬起方法。
	}
	
	public void move(int x,int y){
		robot.mouseMove(x, y);
	}
}
