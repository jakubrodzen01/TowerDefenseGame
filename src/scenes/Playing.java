package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import enemies.Enemy;
import helper.LoadAndSave;
import main.Game;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TowerManager;
import managers.WaveManager;
import objects.PathPoint;
import objects.Tower;
import ui.ActionBar;
import static helper.Constants.Tiles.GRASS_TILE;

public class Playing extends GameScene implements IScene {

	private int[][] lvl;

	private ActionBar actionBar;
	private int mouseX, mouseY;
	private EnemyManager enemyManager;
	private TowerManager towerManager;
	private ProjectileManager projManager;
	private WaveManager waveManager;
	private PathPoint start, end;
	private Tower selectedTower;
	private int goldTick;
	private boolean gamePaused;

	public Playing(Game game) {
		super(game);
		loadDefaultLevel();

		actionBar = new ActionBar(0, 640, 640, 160, this);
		enemyManager = new EnemyManager(this, start, end);
		towerManager = new TowerManager(this);
		projManager = new ProjectileManager(this);
		waveManager = new WaveManager(this);
	}

	private void loadDefaultLevel() {
		lvl = LoadAndSave.GetLevelData("new_level");
		ArrayList<PathPoint> points = LoadAndSave.GetLevelPathPoints("new_level");
		start = points.get(0);
		end = points.get(1);
	}

	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}

	public void update() {
		if (!gamePaused) {
			updateTick();
			waveManager.update();

			goldTick++;
			if (goldTick % (60 * 3) == 0)
				actionBar.addGold(1);

			if (isAllEnemiesDead()) {
				if (isThereMoreWaves()) {
					waveManager.startWaveTimer();
					if (isWaveTimerOver()) {
						waveManager.increaseWaveIndex();
						enemyManager.getEnemies().clear();
						waveManager.resetEnemyIndex();
					}
				}
			}

			if (isTimeForNewEnemy()) {
				spawnEnemy();
			}

			enemyManager.update();
			towerManager.update();
			projManager.update();
		}
	}
	private boolean isWaveTimerOver() {
		return waveManager.isWaveTimerOver();
	}

	private boolean isThereMoreWaves() {
		return waveManager.isThereMoreWaves();
	}

	private boolean isAllEnemiesDead() {
		if(waveManager.isThereMoreEnemiesInWave()) {
			return false;
		}
		for(Enemy e : enemyManager.getEnemies()){
			if (e.isAlive()) {
				return false;
			}
		}

		return true;
	}

	private void spawnEnemy() {
		enemyManager.spawnEnemy(waveManager.getNextEnemy());
	}

	private boolean isTimeForNewEnemy() {
		if(waveManager.isTimeForNewEnemy()){
			if(waveManager.isThereMoreEnemiesInWave()){
				return true;
			}
		}
		return false;
	}

	public void setSelectedTower(Tower selectedTower) {
		this.selectedTower = selectedTower;
	}

	@Override
	public void render(Graphics g) {

		drawLevel(g);
		actionBar.draw(g);
		enemyManager.draw(g);
		towerManager.draw(g);
		projManager.draw(g);

		drawSelectedTower(g);
		drawHighlight(g);
	}

	private void drawHighlight(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(mouseX, mouseY, 32, 32);

	}

	private void drawSelectedTower(Graphics g) {
		if (selectedTower != null)
			g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()], mouseX, mouseY, null);
	}

	private void drawLevel(Graphics g) {

		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				if (isAnimation(id)) {
					g.drawImage(getSprite(id, animationIndex), x * 32, y * 32, null);
				} else
					g.drawImage(getSprite(id), x * 32, y * 32, null);
			}
		}
	}

	public int getTileType(int x, int y) {
		int xCord = x / 32;
		int yCord = y / 32;

		if (xCord < 0 || xCord > 19)
			return 0;
		if (yCord < 0 || yCord > 19)
			return 0;

		int id = lvl[y / 32][x / 32];
		return game.getTileManager().getTile(id).getTileType();
	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640)
			actionBar.mouseClicked(x, y);
		else {
			if (selectedTower != null) {
				if (isTileGrass(mouseX, mouseY)) {
					if (getTowerAt(mouseX, mouseY) == null) {
						towerManager.addTower(selectedTower, mouseX, mouseY);
						removeGold(selectedTower.getTowerType());
						selectedTower = null;
					}
				}
			} else {
				Tower t = getTowerAt(mouseX, mouseY);
				actionBar.displayTower(t);
			}
		}
	}

	private void removeGold(int towerType) {
		actionBar.payForTower(towerType);
	}

	private Tower getTowerAt(int x, int y) {
		return towerManager.getTowerAt(x, y);
	}

	private boolean isTileGrass(int x, int y) {
		int id = lvl[y / 32][x / 32];
		int tileType = game.getTileManager().getTile(id).getTileType();
		return tileType == GRASS_TILE;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			selectedTower = null;
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= 640)
			actionBar.mouseMoved(x, y);
		else {
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 640)
			actionBar.mousePressed(x, y);
	}

	@Override
	public void mouseReleased(int x, int y) {
		actionBar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y) {

	}

	public TowerManager getTowerManager() {
		return towerManager;
	}

	public EnemyManager getEnemyManger() {
		return enemyManager;
	}

	public void shootEnemy(Tower t, Enemy e) {
		projManager.newProjectile(t, e);

	}
	public WaveManager getWaveManager() {
		return waveManager;
	}

	public ActionBar getActionBar() {
		return actionBar;
	}

	public void rewardPlayer(int enemyType) {
		actionBar.addGold(helper.Constants.Enemies.GetReward(enemyType));
	}

	public void removeTower(Tower displayedTower) {
		towerManager.removeTower(displayedTower);
	}

	public boolean isGamePaused() {
		return gamePaused;
	}

	public void removeOneLife() {
		actionBar.removeOneLife();
	}

	public void resetEverything() {
		actionBar.resetEverything();

		enemyManager.reset();
		towerManager.reset();
		projManager.reset();
		waveManager.reset();

		mouseX = 0;
		mouseY = 0;
		selectedTower = null;
		goldTick = 0;
		gamePaused = false;
	}
}