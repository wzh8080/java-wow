package utils;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class ImageHandler {
	private Robot robot = null;
	private Rectangle rectangle = null;
	private String fileName; // 文件的前缀
	private int serialNum = 0;
	private String imageFormat; // 图像文件的格式
	private String defaultImageFormat = "png";// 图像文件的默认格式
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //
	
	public ImageHandler() throws AWTException { 
		robot = new Robot();
		fileName = "C:/Users/Administrator/Desktop/";
		imageFormat = defaultImageFormat;
	}
	public ImageHandler(Robot r) throws AWTException { 
		this.robot = r;
		fileName = "C:/Users/Administrator/Desktop/";
		imageFormat = defaultImageFormat;
	}
	public ImageHandler(Robot r ,String s, String format) {
		this.robot = r;
		fileName = s;
		imageFormat = format;
	}
	public ImageHandler(Robot r ,String s ) {
		this.robot = r;
		fileName = s;
		imageFormat = defaultImageFormat;
		rectangle = new Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight());
	}
	
	/**
	 * 截取整个屏幕
	 * @return 返回成功状态
	 */
	public String getScreen(){
		try {
			//Thread.sleep(1000);
			// 拷贝屏幕到一个BufferedImage对象screenshot
			/*BufferedImage screenshot = 
					robot.createScreenCapture(
							new Rectangle(50, 25, 
									(int) d.getWidth()-100, 
									(int) d.getHeight()-50)
							);*/
			BufferedImage screenshot = robot.createScreenCapture(rectangle);//全屏
			
			//截取：
			String name = getPhoto(screenshot);
			
			return name;
		} catch (Exception e) {
		e.printStackTrace();
		}
		System.out.println("截图成功");
		return "";
	}
	private String getPhoto(BufferedImage screenshot) throws IOException {
//		serialNum++;
		// 根据文件前缀变量和文件格式变量，自动生成文件名
		Date startDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM_dd HH_mm_ss");
		String time = sdf.format(startDate);
		String name = fileName+"/" + time + "."   // "_"+ String.valueOf(serialNum) 
				+ imageFormat;
		File f = new File(name);
//		System.out.print("Save File " + name);
		// 将screenshot对象写入图像文件
		ImageIO.write(screenshot, imageFormat, f);
		return name;
	}
	
	/**
	 * 截取指定大小的屏幕
	 * @param x 左上角点的像素横坐标
	 * @param y 左上角点的像素纵坐标
	 * @param w 截取的宽度
	 * @param h 截取的高度
	 * @return 返回成功状态
	 */
	public String getScreen(int x,int y,int w,int h) {
		try {
			// 拷贝屏幕到一个BufferedImage对象screenshot
			BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(x, y, w, h));
			
			//截取：
			String name = getPhoto(screenshot);
			
			return name;
		}catch (HeadlessException e) {
		e.printStackTrace();
		} catch (AWTException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		System.out.println("截图成功");
		return "";
	}
	
	/**
	 * 截取指定大小的屏幕
	 * @param x 左上角点的像素横坐标
	 * @param y 左上角点的像素纵坐标
	 * @param w 截取的宽度
	 * @param h 截取的高度
	 * @param fileName 存放在文件夹
	 * @return 返回路径
	 */
	public static String getScreen(int x,int y,int w,int h,String fileName) {
		String name = "";
		//int serialNum = 0;
		String num = new SimpleDateFormat("MMddHHmmssSSS").format(new Date());
		String imageFormat = "png";
		try {
			Thread.sleep(1*1500);
			// 拷贝屏幕到一个BufferedImage对象screenshot
			BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(x, y, w, h));
//			serialNum++;
			// 根据文件前缀变量和文件格式变量，自动生成文件名
			name = fileName +"/Temp_"+num+"."
					+ imageFormat;
			File f = new File(name);
			//System.out.print("Save File : " + name);
			// 将screenshot对象写入图像文件
			ImageIO.write(screenshot, imageFormat, f);
			//System.out.print("..Finished!\n");
		}catch (InterruptedException e) {
			e.printStackTrace();//线程异常
		}catch (HeadlessException e) {
		e.printStackTrace();
		} catch (AWTException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
//		System.out.println("截图成功");
		return name;
	}
	
	
	
}
