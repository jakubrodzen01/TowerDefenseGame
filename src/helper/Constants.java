package helper;

public class Constants {

	public static class Projectiles {
		public static final int ARROW = 0;
		public static final int SPELL = 1;
		public static final int BOMB = 2;

		public static float GetSpeed(int type) {
			switch (type) {
				case ARROW:
					return 8f;
				case BOMB:
					return 4f;
				case SPELL:
					return 6f;
			}
			return 0f;
		}
	}

	public static class Towers {
		public static final int CANNON = 0;
		public static final int ARCHER = 1;
		public static final int WIZARD = 2;

		public static int GetTowerCost(int towerType) {
			switch (towerType) {
				case CANNON:
					return 70;
				case ARCHER:
					return 30;
				case WIZARD:
					return 50;
			}
			return 0;
		}
		public static String GetName(int towerType) {
			switch (towerType) {
				case CANNON:
					return "Cannon";
				case ARCHER:
					return "Archer";
				case WIZARD:
					return "Wizard";
			}
			return "";
		}

		public static int GetStartDmg(int towerType) {
			switch (towerType) {
				case CANNON:
					return 15;
				case ARCHER:
					return 6;
				case WIZARD:
					return 2;
			}

			return 0;
		}

		public static float GetDefaultRange(int towerType) {
			switch (towerType) {
				case CANNON:
					return 100;
				case ARCHER:
					return 100;
				case WIZARD:
					return 100;
			}

			return 0;
		}

		public static float GetDefaultCooldown(int towerType) {
			switch (towerType) {
				case CANNON:
					return 100;
				case ARCHER:
					return 25;
				case WIZARD:
					return 40;
			}

			return 0;
		}
	}

	public static class Direction {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class Enemies {

		public static final int URBAN = 0;
		public static final int KAPELA = 1;
		public static final int BIEDRON = 2;
		public static final int STASKO = 3;

		public static float GetSpeed(int enemyType) {
			switch (enemyType) {
				case URBAN:
					return 0.35f;
				case KAPELA:
					return 0.75f;
				case BIEDRON:
					return 0.5f;
				case STASKO:
					return 0.6f;
			}
			return 0;
		}

		public static int GetStartHealth(int enemyType) {
			switch (enemyType) {
				case URBAN:
					return 250;
				case KAPELA:
					return 50;
				case BIEDRON:
					return 100;
				case STASKO:
					return 85;
			}
			return 0;
		}

		public static int GetReward(int enemyType) {
			switch (enemyType) {
				case URBAN:
					return 20;
				case KAPELA:
					return 5;
				case BIEDRON:
					return 5;
				case STASKO:
					return 10;
			}
			return 0;
		}
	}

	public static class Tiles {
		public static final int WATER_TILE = 0;
		public static final int GRASS_TILE = 1;
		public static final int ROAD_TILE = 2;
	}

}
