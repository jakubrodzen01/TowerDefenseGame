package enemies;

import managers.EnemyManager;

import static helper.Constants.Enemies.URBAN;

public class Urban extends Enemy{

	public Urban(float x, float y, int ID, EnemyManager enemyManager) {
		super(x, y, ID, URBAN, enemyManager);
	}

}
