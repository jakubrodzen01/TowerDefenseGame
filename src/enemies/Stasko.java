package enemies;

import managers.EnemyManager;

import static helper.Constants.Enemies.STASKO;

public class Stasko extends Enemy {

	public Stasko(float x, float y, int ID, EnemyManager enemyManager) {
		super(x, y, ID, STASKO, enemyManager);
	}

}
