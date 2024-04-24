package enemies;

import managers.EnemyManager;

import static helper.Constants.Enemies.BIEDRON;

public class Biedron extends Enemy {

	public Biedron(float x, float y, int ID, EnemyManager enemyManager) {
		super(x, y, ID, BIEDRON, enemyManager);
	}

}
