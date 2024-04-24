package managers;

import events.Wave;
import scenes.Playing;

import java.util.ArrayList;
import java.util.Arrays;

public class WaveManager {
    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemySpawnTickLimit = 60 * 1;
    private int enemySpawnTick = enemySpawnTickLimit;
    private int enemyIndex, waveIndex;
    private boolean waveStartTimer;
    private int waveTickLimit = 60 * 5;
    private int waveTick = 0;
    private boolean waveTickTimerOver;
    public WaveManager(Playing playing) {
        this.playing = playing;
        createWaves();
    }
    public void update() {
        if(enemySpawnTick < enemySpawnTickLimit) {
            enemySpawnTick++;
        }

        if(waveStartTimer) {
            waveTick++;
            if(waveTick>=waveTickLimit) {
                waveTickTimerOver = true;
            }
        }
    }
    public void increaseWaveIndex() {
        waveIndex++;
        waveTick = 0;
        waveTickTimerOver = false;
        waveStartTimer = false;
    }

    public int getNextEnemy() {
        enemySpawnTick = 0;
        return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
    }

    private void createWaves() {
        //0-tank, 1-fast, 2-normal, 3-nie wiem co napisać
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,2,2,2,2,2,1,1,3,0))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3,1,1,1,1,1,1,0,0,0))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1,2,3,0,0,0,0,0,0,0))));
    }

    public boolean isTimeForNewEnemy() {
        return enemySpawnTick>=enemySpawnTickLimit;
    }
    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public boolean isThereMoreEnemiesInWave() {
        return enemyIndex < waves.get(waveIndex).getEnemyList().size();
    }

    public boolean isThereMoreWaves() {
        return waveIndex+1 < waves.size();
    }

    public void startWaveTimer() {
        waveStartTimer = true;
    }

    public boolean isWaveTimerOver() {
        return waveTickTimerOver;
    }

    public void resetEnemyIndex() {
        enemyIndex = 0;
    }

    public int getWaveIndex() {
        return waveIndex;
    }

    public float getTimeLeft() {
        float ticksLeft = waveTickLimit - waveTick;
        return ticksLeft / 60.0f;
    }

    public boolean isWaveTimerStarted() {
        return waveStartTimer;
    }

    public void reset() {
        waves.clear();
        createWaves();
        enemyIndex = 0;
        waveIndex = 0;
        waveStartTimer = false;
        waveTickTimerOver = false;
        waveTick = 0;
        enemySpawnTick = enemySpawnTickLimit;
    }
}
