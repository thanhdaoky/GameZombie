import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.swing.ImageIcon;

public class Zombie {
	double x, y;
	double rotationX, rotationY;
	double cX, cY;

	Image[] img = { new ImageIcon(getClass().getResource("/zombie.gif")).getImage(),
			new ImageIcon(getClass().getResource("/SLOW_ZOMBIE.gif")).getImage(),
			new ImageIcon(getClass().getResource("/FAST_ZOMBIE.gif")).getImage(),
			new ImageIcon(getClass().getResource("/boss2.png")).getImage() };

	Random random = new Random();
	int type;
	double angle;

	public Zombie() {
		int r = random.nextInt(4);
		switch (r) {
		case 0:
			x = -100;
			y = random.nextInt(MainFrame.HEIGHT_FRAME);
			break;
		case 1:
			y = -100;
			x = random.nextInt(MainFrame.WIDTH_FRAME);
			break;
		case 2:
			x = MainFrame.WIDTH_FRAME + 100;
			y = random.nextInt(MainFrame.HEIGHT_FRAME);
			break;
		case 3:
			y = MainFrame.HEIGHT_FRAME + 100;
			x = random.nextInt(MainFrame.WIDTH_FRAME);
			break;

		}
		type = random.nextInt(4);
	}

	void draw(Graphics2D g2d) {
		/**
		 * Get anchor point coordinates.
		 */
		rotationX = x + img[type].getWidth(null) / 2;
		rotationY = y + img[type].getHeight(null) / 2;

		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(0, 0, 0);

		g2d.rotate(angle, rotationX, rotationY);
		g2d.drawImage(img[type], (int) x, (int) y, null);
		g2d.setTransform(affineTransform);
	}

	void move(Player player) {
		angle = Math.atan2(rotationY - player.rotationY, rotationX - player.rotationX);
		x = x - Math.cos(angle);
		y = y - Math.sin(angle);
	}
}
