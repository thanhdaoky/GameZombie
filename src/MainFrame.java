import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {
	public static int WIDTH_FRAME = 800;
	public static int HEIGHT_FRAME = 600;

	public MainFrame() {
		this.setTitle("Zombie");
		this.setSize(WIDTH_FRAME, HEIGHT_FRAME);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		MainPanel panel = new MainPanel();
		this.add(panel);
	}

	public static void main(String[] args) {
		MainFrame m = new MainFrame();
		m.setVisible(true);
	}
}
