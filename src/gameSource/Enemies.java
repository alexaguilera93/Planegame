package gameSource;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Enemies {
private int x;
private int y;
private int velocity;
boolean visible;
private Sprite Enemy;


public Enemies(int startingx, int startingy, int v, boolean visible, boolean destroyed){
	this.x = startingx;
	this.y = startingy;
	this.velocity = velocity;
	this.visible = visible;
}
public Sprite en(){
	Animation c = new Animation();
	Image enemy1 = new ImageIcon("missle.jpg").getImage();
	c.addScene(enemy1, 250);
	Enemy = new Sprite(c);
	Enemy.setX(x);
	Enemy.setY(y);
	Enemy.setVelocityX(velocity);
	return Enemy;
	
}
}
