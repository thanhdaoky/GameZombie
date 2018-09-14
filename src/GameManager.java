import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameManager {
	Player player;
	ArrayList<Zombie> a_Zombie;

	void InitGame() {
		player = new Player(MainFrame.WIDTH_FRAME / 2, MainFrame.HEIGHT_FRAME / 2);
		a_Zombie = new ArrayList<>();
		InitZombie();
	}

	void InitZombie() {
		for (int i = 0; i < 20; i++) {
			a_Zombie.add(new Zombie());
		}
	}

	
	void AI(){
        for(int i = a_Zombie.size()-1; i >= 0; i--){
            a_Zombie.get(i).move(player);
        }
    }
	
	
	void draw(Graphics2D g2d) {
		player.draw(g2d);
		
		for (Zombie e: a_Zombie
	             ) {
	            e.draw(g2d);
	        }
	}
}
