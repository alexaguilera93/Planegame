package gameSource;

import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Image;
import javax.swing.ImageIcon;

import gameSource.Core;
import java.util.ArrayList;
import java.util.TimerTask;

import java.util.Timer;

public class PlaneKeyControl extends Core implements KeyListener {
	private Sprite a;
	private Image bg;
	private Animation b;
	private boolean fire;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<Sprite> bull = new ArrayList<Sprite>();
	private boolean bullVisible;
	private Animation scroll;
	private int bg1x;
	private int bg2x;
	private long score = 0;
	private Timer t = new Timer();
	private TimerTask tt;

	public void init() {
		super.init();
		Window w = s.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false);
		w.addKeyListener(this);
	}

	// load pics
	public void loadPics() {
		bg = new ImageIcon("gamebg.png").getImage();
		Image plane = new ImageIcon("plane.png").getImage();
		b = new Animation();
		b.addScene(plane, 250);
		a = new Sprite(b);
		scroll = new Animation();
		scroll.addScene(bg, 3000);
		bg1x = -10;
		bg2x = bg.getWidth(null) - 10;

	}

	public void scroll() {
		if (bg1x > -bg.getWidth(null) - 10) {
			bg1x = bg1x - 10;
			bg2x = bg2x - 10;
		}
		if (bg1x <= -bg.getWidth(null)) {
			bg1x = bg2x;
			bg2x = bg.getWidth(null) - 10;
		}
	}

	public PlaneKeyControl() {
		// set positions
		loadPics();

		a.setVelocityX(0f);
		a.setVelocityY(0f);
		a.setX(0f);
		a.setVelocityY(0f);
	}

	// key is pressed
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) {
			stop();
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			if (a.getVelocityX() >= 0) {
				a.setVelocityX(-0.5f);
			}
			if (a.getX() > 0) {
				bg1x = bg1x + 5;
				bg2x = bg2x + 5;
			}
			float z = a.getVelocityX();
			z += z;
			if (z > -15f) {
				a.setVelocityX(z);
				a.setX(a.getX() + z);
			} else {
				a.setVelocityX(-15f);
				a.setX(a.getX() - 15);
			}
			if (a.getX() < 0) {
				a.setX(0);
			}
			e.consume();
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			if (a.getVelocityX() <= 0) {
				a.setVelocityX(0.5f);
			}
			if (a.getY() < s.getWidth() - a.getWidth()) {
				bg1x = bg1x - 5;
				bg2x = bg2x - 5;
			}
			float z = a.getVelocityX();
			z += z;
			if (z < 15) {
				a.setVelocityX(z);
				a.setX(a.getX() + z);
			} else {
				a.setVelocityX(15f);
				a.setX(a.getX() + 15);
			}
			if (a.getX() > s.getWidth() - a.getWidth()) {
				a.setX(s.getWidth() - a.getWidth());
				a.setY(a.getY());
			}
			e.consume();
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			if (a.getVelocityY() >= 0) {
				a.setVelocityY(1f);
			}
			float z = a.getVelocityY();
			z += 2f * z;
			a.setVelocityY(z);
			a.setY(a.getY() + 2f * z);
			if (a.getY() > s.getHeight() - a.getHeight()) {
				a.setY(s.getHeight() - a.getHeight());
				a.setX(a.getX());
			}
			e.consume();
		}
		if (keyCode == KeyEvent.VK_UP) {
			if (a.getVelocityY() <= 0) {
				a.setVelocityY(-1f);
			}
			float z = a.getVelocityY();
			z += 2f * z;
			a.setVelocityY(z);
			a.setY(a.getY() + 2f * z);
			if (a.getY() < 0) {
				a.setY(0);
			}
			e.consume();
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			if(tt!= null){
				return;
			}
			tt = new TimerTask(){
				public void run(){
					while(bullets.size() < 3){
					Bullet k = new Bullet(Math.round(a.getX() + a.getWidth() - 20),
							Math.round(a.getY() + a.getHeight() - 35), 1, true);
					bull.add(k.Bull());
					bullets.add(k);
					}
				}
			};
			
			t.scheduleAtFixedRate(tt, 0, 5000);
			
			e.consume();
			
		}

	}
	

	// move bullet method
	public void moveBul(Sprite m, Bullet r) {

		if (r.visible == true) {

			m.setX(m.getX() + m.getVelocityX());
			if (m.getX() > s.getWidth()) {
				r.visible = false;
				bullets.remove(r);
				bull.remove(m);

			}
		}
	}

	// keyreleased
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
			if (bg1x % 10 != 0) {
				bg1x = bg1x + 5;
				bg2x = bg2x + 5;
			}

			a.setVelocityX(0f);
			e.consume();
		}
		if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
			a.setVelocityY(0f);
			e.consume();
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			tt.cancel();
			tt =null;
			e.consume();
		}
		e.consume();
	}

	// last method from interface
	public void keyTyped(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_SPACE) {

		}
		e.consume();
	}

	public synchronized void draw(Graphics2D g) {
		Window w = s.getFullScreenWindow();
		g.drawImage(scroll.getImage(), bg1x, 0, null);
		g.drawImage(scroll.getImage(), bg2x, 0, null);
		if (System.currentTimeMillis() % 1000 != 0) {
			scroll();
		}
		g.drawImage(a.getImage(), Math.round(a.getX()), Math.round(a.getY()),
				null);
		while (!bull.isEmpty()) {
			for (int i = 0; i < bull.size(); i++) {
				Bullet l = bullets.get(i);
				Sprite n = bull.get(i);
				if (l.visible) {
					g.drawImage(n.getImage(), Math.round(n.getX()),
							Math.round(n.getY()), null);
					moveBul(n, l);
					if (i == bull.size() - 1 && bull.size() != 0) {
						i = 0;
					}
				}
			}
		}

	}
}
