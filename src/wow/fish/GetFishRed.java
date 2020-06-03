package wow.fish;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class GetFishRed {
	/**
	 * 获取红点位，左上角的坐标
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static int[] getRed(String fileName) throws IOException{
		BufferedImage img = ImageIO.read(new File(fileName));
		int w = img.getWidth();
		int h = img.getHeight();
		// rgb的数组,保存像素，用一维数组表示二位图像像素数组
		int[] rgbArray = new int[h * w];
		img.getRGB(0, 0, w, h, rgbArray, 0, w);
		
		int maxX = 0;
		int maxY = 0;
		
		//MouseHandler mouse = new MouseHandler();
		
		boolean isBreak = false;
		for(int i=0; i<h; i++) {     // 高 y
			for(int j=0; j<w; j++) { // 宽 x
				Color c = new Color(rgbArray[i*w + j]);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				
//				if(j==58 && i==70){
//					System.out.println("位置："+j+" "+i+"；像素值为：red="+red+"；green="+green+"；blue="+blue);
//				}else if(j==57 && i==71){
//					System.out.println("位置："+j+" "+i+"；像素值为：red="+red+"；green="+green+"；blue="+blue);
//				}else if(j==56 && (i==72 || i==73)){
//					System.out.println("位置："+j+" "+i+"；像素值为：red="+red+"；green="+green+"；blue="+blue);
//				}else if(j==57 && i==74){
//					System.out.println("位置："+j+" "+i+"；像素值为：red="+red+"；green="+green+"；blue="+blue);
//				}
				
				//红、绿、蓝
				if(red>180 && green<150 && blue<150){
					maxX = maxX>j?maxX:j;
					maxY = maxY>i?maxY:i;
					System.out.println("红点位置："+j+" "+i+"；像素值为：red="+red+"；green="+green+"；blue="+blue);
					isBreak = true;
					break;
				}
			}
			if(isBreak)break;
		}
		int[] arr = new int[2];
		arr[0] = maxX;
		arr[1] = maxY;
//		System.out.println("红点位置："+Arrays.toString(arr));
		return arr;
	}
}
