package scenes;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import main.Game;
import ui.MyButton;

import javax.imageio.ImageIO;

import static main.GameStates.*;

public class Settings extends GameScene implements IScene {

	private MyButton bMenu;
	private Image backgroundImg;

	public Settings(Game game) {
		super(game);
		initButtons();

		try {
			backgroundImg = ImageIO.read(new File("resources/settings.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 2, 100, 30);
	}

	@Override
	public void render(Graphics g) {
		if(backgroundImg != null) {
			g.drawImage(backgroundImg, 0, 0, null);
		}

		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);

	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);

	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bMenu.resetBooleans();

	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
