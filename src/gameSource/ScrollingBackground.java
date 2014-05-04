package gameSource;

import java.awt.Image;
import javax.swing.ImageIcon;
public class ScrollingBackground {
	private Image l;
	private int width;
	
	
	public ScrollingBackground(Image a){
		this.l = a;
		this.width = l.getWidth(null);
		
	}
	public Animation scroll(){
		Animation scroll = new Animation();
		scroll.addScene(l, 5000);
		scroll.addScene(l, 5000);
		
		
		return scroll;
	}
}
