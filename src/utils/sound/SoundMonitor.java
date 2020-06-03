package utils.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 * 监听声音
 * @author Administrator
 *
 */
public class SoundMonitor {
	private AudioFormat audioFormat;
	private TargetDataLine targetDataLine;
	public SoundMonitor() {
//		targetDataLine.stop();
//		targetDataLine.close();
		
		// 获得指定的音频格式
		audioFormat = getAudioFormat();
		DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
		try {
			targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	private AudioFormat getAudioFormat() {
		float sampleRate = 16000;
		// 8000,11025,16000,22050,44100
		int sampleSizeInBits = 16;
		// 8,16
		int channels = 1;
		// 1,2
		boolean signed = true;
		// true,false
		boolean bigEndian = false;
		// true,false
		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
	}// end getAudioFormat
	

	//声音录入的权值
	int weight = 18;
	
	/**
	 * 等待声音进入
	 * @param time 等待Time秒结束
	 * @throws LineUnavailableException
	 */
	public boolean waitSoundIn(int time) throws LineUnavailableException{
		boolean hasSound = false;
		byte[] fragment = new byte[1024];
		try {
			targetDataLine.open(audioFormat);
			targetDataLine.start();
			
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		long start = System.currentTimeMillis();
		System.out.println("等待声音进入  ...");
		int count = 0; //防止突然一次的声音传入，只获取持续钓鱼声
		while (true) {
			targetDataLine.read(fragment, 0, fragment.length);
			//当数组末位大于weight时开始存储字节（有声音传入），一旦开始不再需要判断末位
			if (Math.abs(fragment[fragment.length-1]) > weight) {
				count++;
				if(count>=5){
//					System.out.println("有声音传入。。。count="+count);
					hasSound = true;
					break;
				}
			}
			long end = System.currentTimeMillis();
			if((end-start)>(time*1000)){
				System.out.println("超过"+time+"秒，跳出声音监听");
				break;
			}
		}
//		System.out.println("等待声音结束 ---- End");
		fragment = null;
		targetDataLine.stop();
		targetDataLine.close();
		return hasSound;
	}
	
	public static void main(String[] args) {
		SoundMonitor sm = new SoundMonitor();
		try {
			sm.waitSoundIn(10);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
