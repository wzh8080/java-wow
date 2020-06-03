package wow.fish;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import utils.NaturalMouse;
import utils.sound.SoundMonitor;
/**
 * 桌面程序，窗口启动
 * 开始后最小化至任务栏
 * 监听鼠标右键点击后，退出程序
 * 
 * 1.监听声音拉杆，
 * 2.同时截图扫描扫描指定区域浮漂位置范围
 * @author Administrator
 *
 */
public class AutoFishCore {
	

	public void run() {
		//判断是否停止的计数
		int downSum = 0;
		int success = 0;
		//截图位置
		int cut_x = 830;
		int cut_y = 400;
		int cut_w = 350;
		int cut_h = 210;

		try {
			FishHandler fishHandler = new FishHandler();
			MouseHandler mouse = new MouseHandler();
			SoundMonitor sm = new SoundMonitor();
			
			int[] arrXY = new int[2];
			
			while (true) {
				
				//仿人工，等待（缓冲），准备放杆
				int n = ThreadLocalRandom.current().nextInt(400)+1000;
				Thread.sleep(n);
				//放杆  鼠标滑轮向下滑动1
				fishHandler.pushRod();
				System.out.println("-------- 钓鱼开始：放杆 --------");
				
				n = ThreadLocalRandom.current().nextInt(400)+2500;
				Thread.sleep(n);
				
				//截屏扫描,保存截图记录, 获取红点在整个屏幕 中的坐标位置
				int[] point = fishHandler.getPoint(cut_x,cut_y,cut_w,cut_h,"F:\\wzh\\Ai\\wow\\Wow_Photo\\fish");
				//获得 鼠标当前位置的 坐标
				int[] mouseXY = mouse.getMouseXY(arrXY);
				//判断是否在区域内
				if(mouseXY[0]>point[0] && mouseXY[1]>point[1] 
						&& mouseXY[0]<(5+point[0]) && mouseXY[1]<(5+point[1])){
				}else{
					// 移动到区域内随机一点
					NaturalMouse.move(point[0], point[1], 5, 5);
				}
				
				//收集红点坐标为零的截图
				
				//等待0.5秒，放杆初期不会有声音
				//等待声音结束，防止上次余音的存在
				n = ThreadLocalRandom.current().nextInt(200)+100;
				Thread.sleep(n);
				
				//循环判断是否有声音产生
				boolean hasSound = sm.waitSoundIn(20);
				if(hasSound)success++;
				
				//声音传入
				//仿人工，等待（突然有声音，反应时间）
				n = ThreadLocalRandom.current().nextInt(200)+90;
				Thread.sleep(n);
				downSum++;
				
				//点击收杆 
				fishHandler.pullRod();
				
				System.out.println("一次钓鱼完成， 总共 第 "+downSum+" 次；成功第"+success+"次\n");
				//结束条件，判断鼠标右键被按下时 结束循环
//				if(downSum == 3){
//					break;
//				}
				
//				//偶尔移动鼠标
//				int max1=25,min1=0; //max1=72,min1=0;
//				int num1 = (int) (Math.random()*(max1-min1)+min1);  
//				int num2 = ThreadLocalRandom.current().nextInt(max1);  
//				if(num1==num2){
//					System.out.println("触动鼠标");
//					NaturalMouse.move(cut_x, cut_y, cut_w, cut_h);
//				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("\n任务结束");
	}

	
	//测试
	public static void main(String args[]) throws IOException {
		AutoFishCore autoFish = new AutoFishCore();
		autoFish.run();
		
		//测试 找红点
		//GetFishRed.getRed("F:\\wzh\\Ai\\wow\\Wow_Photo\\fish\\20200414102703.png");
		
		//测试 查找目标伪红点处的 像素值为多少
//		GetFishRed.getRed("F:\\wzh\\Ai\\wow\\Wow_Photo\\fish\\Temp_0414105145654.png");
    }

}
