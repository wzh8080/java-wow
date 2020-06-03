package utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.github.joonasvali.naturalmouse.api.MouseMotionFactory;

public class NaturalMouse {
	/**
	 * 在一定区域内移动鼠标 
	 * @throws InterruptedException 
	 */
	public static void move(int x,int y,int width,int height) throws InterruptedException{
		//开始移动
		int moveX = ThreadLocalRandom.current().nextInt(width)+x;
		int moveY = ThreadLocalRandom.current().nextInt(height)+y;
		MouseMotionFactory.getDefault().move(moveX,moveY);
	}
	/**
	 * 在一定区域内拖动鼠标左键
	 */
	public static void pressMove(int x,int y,int width,int height){
		
	}
	public static void main(String[] args) throws InterruptedException {
		while(true){
			NaturalMouse.move(200, 200, 100, 100);
		}
	}
}
