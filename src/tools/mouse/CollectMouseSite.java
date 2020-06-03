package tools.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;

import utils.MouseHandler;

public class CollectMouseSite extends JFrame{

private static final long serialVersionUID = 1L; 
	
	public CollectMouseSite() {
		
		MouseHandler handler = new MouseHandler();
		setTitle("Hern");
		setBounds(200, 200, 200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JLabel label = new JLabel();
		label.setText("点击此处，开始记录鼠标位置");
		label.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {//鼠标按键被释放时被触发
			}
			
			@Override
			public void mousePressed(MouseEvent e) {//鼠标按键被按下时被触发
			}
			
			@Override
			public void mouseExited(MouseEvent e) {//光标移出组件时被触发
				//记录坐标
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				handler.getMouseXY();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {//光标移入组件时被触发
			}
			int count = 1;
			@Override
			public void mouseClicked(MouseEvent e) {//发生单击事件时被触发
				// TODO Auto-generated method stub
				System.out.print("第  "+count+" 次收集坐标开始：");
				count++;
			}
		});
		add(label);
		setVisible(true);
	}
 
	public static void main(String[] args) {
		CollectMouseSite test = new CollectMouseSite();
 
	}


}
