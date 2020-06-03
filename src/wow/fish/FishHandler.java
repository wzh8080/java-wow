package wow.fish;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import utils.ImageHandler;



public class FishHandler {
	private MouseHandler mouseHandler;
	private Robot robot;
	private int sleep=50;
	public FishHandler() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		this.mouseHandler= new MouseHandler(robot);
	}
	
	/**
	 * 收杆
	 * @param x
	 * @param y
	 */
	public void pullRod(){
		sleep = ThreadLocalRandom.current().nextInt(140)+50;  //80~220
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);//按下左键（BUTTON后面的1改成2和3即可，2是按下滚轮，3是按下鼠标右键。）
		robot.delay(sleep); //间隔时间
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);  //鼠标抬起方法。
	}
	/**
	 * 放杆
	 */
	public void pushRod(){
//		sleep = ThreadLocalRandom.current().nextInt(161)+45;  //45~106
//	    robot.keyPress(KeyEvent.VK_8);
//	    robot.delay(sleep); //间隔时间
//	    robot.keyRelease(KeyEvent.VK_8);
		//鼠标滑轮向下滑动1
		robot.mouseWheel(1);
	}
	
	/**
	 * 获得浮漂位置范围
	 * @param imageHander 
	 * @param path 文件夹目录 
	 * @return
	 * @throws IOException 
	 */
	public int[] getPoint(int x,int y,int width,int height,String path) throws IOException{
		//截取屏幕
		String fileName = ImageHandler.getScreen(x, y, width, height,path);
		//获得位置区域
		int[] red = GetFishRed.getRed(fileName);
//		//如果红点位置为[0, 0]，则是没有找到红点
//		if(red[0]==0&&red[1]==0){
//			return 
//		}
		red[0] = x + red[0];
		red[1] = y + red[1];
		//仿人工优化
		return red;
	}
}
