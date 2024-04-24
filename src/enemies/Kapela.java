package enemies;

import managers.EnemyManager;

import static helper.Constants.Enemies.KAPELA;

public class Kapela extends Enemy {

	public Kapela(float x, float y, int ID, EnemyManager enemyManager) {
		super(x, y, ID, KAPELA, enemyManager);
	}

}
