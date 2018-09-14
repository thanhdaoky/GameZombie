import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	GameManager gameManager = new GameManager();
	Image imgBackground = new ImageIcon(getClass().getResource("/background.png")).getImage();
	Image imgCrosshair = new ImageIcon(getClass().getResource("/crosshair.png")).getImage();
	boolean[] flag = new boolean[256];

	public MainPanel() {
		this.setBackground(Color.BLACK);

		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(imgCrosshair, new Point(0, 0), "Cursor"));
		gameManager.InitGame();
		addMouseMotionListener(mouseMotionListener);
		setFocusable(true);
        addKeyListener(keyListener);
        Thread thread = new Thread(runnable);
        thread.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(imgBackground, 0, 0, MainFrame.WIDTH_FRAME, MainFrame.HEIGHT_FRAME, null);
		gameManager.draw(g2d);
	}

	MouseMotionListener mouseMotionListener = new MouseMotionListener() {

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			gameManager.player.changeAngle(e.getX(), e.getY());
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	};

	KeyListener keyListener = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// gameManager.player.move(e.getKeyCode());
			// repaint();
			flag[e.getKeyCode()] = true;

		}

		@Override
		public void keyReleased(KeyEvent e) {
			flag[e.getKeyCode()] = false;
		}
	};

	Runnable runnable = new Runnable() {
		@Override
		public void run() {

			while (true) {

				if (flag[KeyEvent.VK_A] == true) {
					gameManager.player.move(KeyEvent.VK_A);
				}

				if (flag[KeyEvent.VK_D] == true) {
					gameManager.player.move(KeyEvent.VK_D);
				}

				if (flag[KeyEvent.VK_W] == true) {
					gameManager.player.move(KeyEvent.VK_W);
				}

				if (flag[KeyEvent.VK_S] == true) {
					gameManager.player.move(KeyEvent.VK_S);
				}
				gameManager.AI();
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
}
