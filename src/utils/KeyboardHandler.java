package utils;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class KeyboardHandler {
	//Shift组合键
	public static void keyPressWithShift(Robot r,int key){
	    //按下Shift
	    r.keyPress(KeyEvent.VK_SHIFT);
	    //按下某个键
	    r.keyPress(key);

	    //释放某个键
	    r.keyRelease(key);
	    //释放Shift
	    r.keyRelease(KeyEvent.VK_SHIFT);
	    //等待100ms
	    r.delay(100);
	}

	//Ctrl组合键
	public static void keyPressWithCtrl(Robot r,int key){
	    r.keyPress(KeyEvent.VK_CONTROL);
	    r.keyPress(key);

	    r.keyRelease(key);
	    r.keyRelease(KeyEvent.VK_CONTROL);

	    r.delay(100);
	}

	//Alt组合键
	public static void keyPressWithAlt(Robot r ,int key){
	    r.keyPress(KeyEvent.VK_ALT);
	    r.keyPress(key);

	    r.keyRelease(key);
	    r.keyRelease(KeyEvent.VK_ALT);
	    r.delay(100);
	}
	
	//win组合键
		public static void keyPressWithWin(Robot r,int key){
		    r.keyPress(KeyEvent.VK_WINDOWS);
		    r.keyPress(key);

		    r.keyRelease(key);
		    r.keyRelease(KeyEvent.VK_WINDOWS);

		    r.delay(100);
		}

	//输入字符串
	public static void keyPressString(Robot r ,String str){
	    //获取剪切板
	    Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
	    //将传入字符串封装下
	    Transferable tText = new StringSelection(str);
	    //将字符串放入剪切板
	    clip.setContents(tText, null);
	    //按下Ctrl+V实现粘贴文本
	    keyPressWithCtrl(r, KeyEvent.VK_V);
	    r.delay(100);
	}

	//输入数字
	public static void keyPressNumber(Robot r ,int number){
	    //将数字转成字符串
	    String str = Integer.toString(number);
	    //调用字符串的方法
	    keyPressString(r,str);
	}

	//实现按一次某个按键
	public static void keyPress(Robot r,int key){
	    //按下键
		int n = ThreadLocalRandom.current().nextInt(89)+97;
		r.delay(n);
	    r.keyPress(key);
	    //释放键
	    n = ThreadLocalRandom.current().nextInt(124)+66;
	    r.keyRelease(key);

	    r.delay(500);
	}
	
	/**
	 * 键盘输入 多个字符（小写）
	 * @param r
	 * @param key
	 */
	public static void keysInput(Robot r,String keys){
		r.delay(1000);
		for(int i=0;i<keys.length();i++){
			String c = keys.charAt(i)+"";
			KeyboardHandler.keyPress(r, KeyMap.keyMap.get(c.toUpperCase()));
		}
	}

	/*//判断现在是否是大写
	// 设置默认休眠时间20毫秒
    robot.setAutoDelay(20);
    for (String c : "class".split("")) {
        if (Character.isUpperCase(c.charAt(0))) {
            // 如果是大写
            // 输入名字
            // 按下弹起CapsLock键，为了切换大小写
            robot.keyPress(KeyEvent.VK_CAPS_LOCK);
            robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
            // 循环每个字符，根据字符取出map中存储的键值
            robot.keyPress(KeyMap.keyMap.get(c.toUpperCase()));
            robot.keyRelease(KeyMap.keyMap.get(c.toUpperCase()));
            // 按下弹起CapsLock键，切换回原来的大小写
            robot.keyPress(KeyEvent.VK_CAPS_LOCK);
            robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
        } else {
            // 如果是小写
            // 循环每个字符，根据字符取出map中存储的键值
            robot.keyPress(KeyMap.keyMap.get(c.toUpperCase()));
            robot.keyRelease(KeyMap.keyMap.get(c.toUpperCase()));
        }
    }*/
	
}
