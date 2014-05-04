package gameSource;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet {
	private int x;
	private int y;
	private int velocity;
	boolean visible = true;
	private Sprite Bull;

	public Bullet(int startingx, int startingy, int v, boolean visible){
		this.x = startingx;
		this.y =startingy;
		this.velocity = v;
		this.visible = visible;
		
	}
	public Sprite Bull(){
		Animation a = new Animation();
		Image bullet = new ImageIcon("C:\\Users\\Alex\\Pictures\\myGameImages\\planeGame\\bullet.png").getImage();
		a.addScene(bullet, 250);
		Bull = new Sprite(a);
		Bull.setX(x);
		Bull.setY(y);
		Bull.setVelocityX(velocity);
		return Bull;
	}
	public void makeMove(){
		int xposition = x;
		xposition += velocity;
	}
	
}
