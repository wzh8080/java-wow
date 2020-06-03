package utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerManager implements MouseListener{

	@Override
	public void mouseReleased(MouseEvent e) {//鼠标按键被释放时被触发
		// TODO Auto-generated method stub
		System.out.println("鼠标按键被释放，");
		int i = e.getButton(); //通过该值可以判断释放的是哪个键
		if (i == MouseEvent.BUTTON1) {
			System.out.print("释放了鼠标左键");
		}else if(i == MouseEvent.BUTTON2) {
			System.out.println("释放了鼠标滚轮");
		}else if(i == MouseEvent.BUTTON3){
			System.out.println("释放了鼠标右键");
		}
			
	}
	
	@Override
	public void mousePressed(MouseEvent e) {//鼠标按键被按下时被触发
		// TODO Auto-generated method stub
		System.out.print("鼠标按键被按下，");
		int i = e.getButton(); // 通过该值可以判断按下的是哪个键
		if(i == MouseEvent.BUTTON1) {
			System.out.print("按下了鼠标左键");
		}else if(i == MouseEvent.BUTTON2) {
			System.out.print("按下了鼠标滚轮");
		}else if(i == MouseEvent.BUTTON3) {
			System.out.print("按下了鼠标右键");
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {//光标移出组件时被触发
		// TODO Auto-generated method stub
		System.out.println("光标移除组件");
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {//光标移入组件时被触发
		// TODO Auto-generated method stub
		System.out.println("光标移入组件");

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {//发生单击事件时被触发
		// TODO Auto-generated method stub
		System.out.print("单击了鼠标按键，");
		int i = e.getButton(); // 通过该值可以判断单击的是哪个键
		if (i == MouseEvent.BUTTON1) {
			System.out.print("单击的是鼠标左键，");
		}else if (i == MouseEvent.BUTTON2) {
			System.out.print("单击的是鼠标滚轮，");
		}else if (i == MouseEvent.BUTTON3) {
			System.out.print("单击的是鼠标右键，");
		}
		int clickCount = e.getClickCount();//获取单击按键的次数
		System.out.println("单击次数为" + clickCount + "下");
	}
}
