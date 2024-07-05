package org.example;
//Emojis used : White square "⬜", black square: "⬛", Bomb: "💣"
public class Main {
    public static void main(String[] args) {

        Game game = new Game(10, 10, 10);
        game.setup();
        game.loop();

    }
}