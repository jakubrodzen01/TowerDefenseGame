package main;

public class Main {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.getGameScreen().initInputs();
        game.start();
    }
}
