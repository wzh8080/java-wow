package wow.pet;

import java.awt.AWTException;
import java.awt.Robot;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import utils.ImageHandler;
import utils.MouseHandler;
import utils.NaturalMouse;
import utils.ai.BaiduAi;


public class PetFighting {
	private MouseHandler mouse = null;
	private Robot robot = null;
	private static ImageHandler image = null;
	private int minute = 0;
	private long ScreenshotsMs = 0;
	private long startMs = 0;
	private boolean ScreenshotsFlag = true;
	public PetFighting() throws AWTException {
		this.image = new ImageHandler(robot,"F:/wzh/Ai/wow/PetFighting/FullScreen");
		this.robot = new Robot();
		this.mouse = new MouseHandler(robot);
	}


	public void run() throws Exception {
		Date startDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = sdf.format(startDate);
		System.out.println("开始 : "+startTime);
		
		//设定结束时间：
//		int flag = 0;//准备退出
//		int endFlag = ThreadLocalRandom.current().nextInt(50)+3; 
//		String endTime=new String("2019-12-14 14:25:52"); 
//		Date endDate = sdf.parse(endTime);
		
		while(true){
			/*Date date = new Date();
			if(date.after(endDate) && flag == 0){
				System.out.println("准备结束 ： "+sdf.format(date));
				flag = 1;
			}
			if(flag>0){
				flag++;
				if(flag>=endFlag){
					System.out.println("程序已退出：" + endFlag);
					break;
				}
			}*/
			startFighting(robot);
		}
	}
	
	public void startFighting(Robot robot) throws Exception{
		//偶尔变慢
		int n1 = ThreadLocalRandom.current().nextInt(20);
		int n2 = ThreadLocalRandom.current().nextInt(23); 
		int sleep = ThreadLocalRandom.current().nextInt(94)+128;  //80~220
		if(n1 == n2){  
			sleep = ThreadLocalRandom.current().nextInt(100)+100;
		}
		//偶尔移动鼠标
		int max1=103,min1=0; //max1=72,min1=0;
		int num1 = (int) (Math.random()*(max1-min1)+min1);  
		int num2 = ThreadLocalRandom.current().nextInt(119);  
		if(num1==num2){
//			moveCount++;
//			System.out.println("移动鼠标A 第 "+ moveCount +" 次");
			NaturalMouse.move(514, 204, 311, 345);
		}
		//向上滑动滚轮
		robot.delay(sleep); //间隔时间
		robot.mouseWheel(1); 
		
		//偶尔移动鼠标
		num1 = ThreadLocalRandom.current().nextInt(112);   //112
		num2 = (int) (Math.random()*(129)+2);   //69
		if(num1==num2){
//			moveCount++;
//			System.out.println("移动鼠标B 第 "+ moveCount +" 次");
			
			NaturalMouse.move(514, 204, 311, 345);
		}
		//偶尔变慢
		sleep = ThreadLocalRandom.current().nextInt(297)+219;  //80~220
		n1 = ThreadLocalRandom.current().nextInt(21);
		n2 = ThreadLocalRandom.current().nextInt(24);
		if(n1 == n2){
			sleep = ThreadLocalRandom.current().nextInt(100)+80;
		}
		//向下滑动滚轮
		robot.delay(sleep); //间隔时间
		robot.mouseWheel(-1);//向下滑动滚轮
		
		//截取全屏
		long nowMs = System.currentTimeMillis();
		if(ScreenshotsFlag){
			startMs = System.currentTimeMillis();
//			minute = (int) (Math.random()*(12)+4); // 4~15分钟截屏一次
			minute = 5; // 5分钟截屏一次
			ScreenshotsMs = startMs+minute*60000;
			ScreenshotsFlag = false; 
		}else{
			if(nowMs>ScreenshotsMs){ //minute分钟后截屏
//				String fileName = image.getScreen(); //截屏(全屏)
				String fileName = image.getScreen(400,255,566,260); // 截取制定区域
				//辨别是否退出   已从服务器断开  确定
				if(checkDown(fileName)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					System.out.println("断开连接，关闭程序  ! 时间："+sdf.format(new Date()));
					return;
				}
				ScreenshotsFlag = true;
			}
	 	}
		
	}
	
	/**
	 * 判断是否断开，并重新连接，如果连接失败进入不了，则发送短信并返回false
	 * return true :  断开le 
	 * @param fileName
	 * @throws Exception
	 */
	public boolean checkDown(String fileName) throws Exception{
		//查看：是否断开连接
		String isDown = BaiduAi.imageRecognition("", fileName);
		Boolean flag = BaiduAi.checkWords(isDown,"已从服务器断开");
		if(flag){
			int count = 0;
			// 打开wow 【1】
			openGame(count); 
			// 等待30秒钟
			Thread.sleep(30*1000);
			// 判断是否存在 进入 按钮 【2】
			String inGame = image.getScreen(1,1,1,1);
			
			if (!BaiduAi.checkWords(inGame,"进入")){ // 如果没有显示“进入游戏” 
				return true;
//				count++;
//				if(count > 2) { // 如果多次没有该按钮，且此时是白天，则发送 短信/微信
//					// 如果是白天，发送短信
//					
//					return false;
//				}
//				openGame(count);
			}else {
				// 在任务选择界面，点击 进入 【3】
				
				// 等待 30 秒
				Thread.sleep(30*1000);
				return false;// 重新连接上了，没有断开
			}
			
		}else {
			return false; // 没有断开
		}
	}
	/**
	 * 显示所有程序，开启，到选择人物阶段
	 * @throws InterruptedException 
	 */
	private void openGame(int count) throws InterruptedException {
		// 关闭wow
		// 点击确定
		
		// 点击退出
		NaturalMouse.move(514, 204, 5, 2);
		
		// 第二次进行，执行显示所有程序
		if(count > 0) {
			// win+D 
			
			// win+D 
		}

		// 最小化Eclipse
		
		// 点击战网
		
	}
	
	public static void main(String[] args) throws Exception {
//		run();
		PetFighting pet = new PetFighting();
//		pet.checkDown("F:\\wzh\\Ai\\wow\\PetFighting\\FullScreen\\05_31 09_03_19.png");
		String fileName = image.getScreen(400,255,566,260);
		System.out.println("fileName === "+fileName);
		pet.checkDown(fileName);
	}
}
