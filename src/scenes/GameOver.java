package scenes;

import main.Game;
import ui.MyButton;

import java.awt.*;

import static main.GameStates.*;
public class GameOver extends GameScene implements IScene {
    private MyButton bReplay;
    private MyButton bMenu;



    public GameOver(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;

        bMenu = new MyButton("Menu", x, y, w, h);
        bReplay = new MyButton("Replay", x, y + yOffset, w, h);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("LucidaSans", Font.BOLD, 50));
        g.drawString("Game Over!", 160, 80);

        g.setFont(new Font("LucidaSans", Font.BOLD, 15));
        bMenu.draw(g);
        bReplay.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x, y)) {
            game.getPlaying().resetEverything();
            SetGameState(MENU);
        } else if (bReplay.getBounds().contains(x, y)) {
            replayGame();
        }
    }

    private void replayGame() {
        game.getPlaying().resetEverything();
        SetGameState(PLAYING);
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bReplay.setMouseOver(false);

        if(bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        } else if (bReplay.getBounds().contains(x, y)) {
            bReplay.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        } else if (bReplay.getBounds().contains(x, y)) {
            bReplay.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bReplay.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
