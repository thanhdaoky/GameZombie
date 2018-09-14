import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Player {
	double x, y;
	double rotationX, rotationY;
	double cX, cY;
	
	Image img = new ImageIcon(getClass().getResource("/player.PNG")).getImage();
	double angle;
	
	public Player(double x, double y) {
		this.x = x - img.getWidth(null)/2;
		this.y = y -img.getHeight(null)/2;
	}
	
	void draw(Graphics2D g2d) {
		rotationX = x + img.getWidth(null)/2;
		rotationY = y + img.getHeight(null)/2;
		
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(0, 0, 0);
		
		g2d.rotate(angle, rotationX, rotationY);
		g2d.drawImage(img, (int)x, (int)y, null);
		g2d.setTransform(affineTransform);
	}
	
	void changeAngle(double cX, double cY) {
		this.cX = cX;
		this.cY = cY;
		angle = Math.atan2(y-cY, x-cX);
	}
	
	void move(int orient){
        int speed = 5;
        /**
         * backup coordinates before change x and y
         */
        double xRaw = x;
        double yRaw = y;
        switch (orient){
            case KeyEvent.VK_A:
                x -= speed;
              break;
            case KeyEvent.VK_D:
                x += speed;
                break;
            case KeyEvent.VK_W:
                y -= speed;
                break;
            case KeyEvent.VK_S:
                y += speed;
                break;

        }

        if (( x < 0 )|| (x >= MainFrame.WIDTH_FRAME - img.getWidth(null))){
            x = xRaw;
        }

        if ((y < 0) || (y >= MainFrame.HEIGHT_FRAME - img.getHeight(null)- 30)){
            y = yRaw;
        }

        changeAngle(cX, cY);
    }
}
