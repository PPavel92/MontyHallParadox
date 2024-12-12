package com.example;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int totalGames = 1000;
        int wins = 0;
        int losses = 0;

        HashMap<Integer, String> gameResults = new HashMap<>();

        for (int i = 1; i <= totalGames; i++) {
            GameResult result = playGame();
            gameResults.put(i, result.isWin() ? "Win" : "Lose");

            if (result.isWin()) {
                wins++;
            } else {
                losses++;
            }
        }

        // Выводим статистику
        System.out.println("Total games: " + totalGames);
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
    }

    public static GameResult playGame() {
        // Инициализация
        Random random = new Random();
        int carPosition = random.nextInt(3);  // Место с машиной (0, 1, 2)
        int playerChoice = random.nextInt(3); // Выбор игрока

        // Открытие одной из дверей, за которой нет машины
        int revealedDoor;
        do {
            revealedDoor = random.nextInt(3);
        } while (revealedDoor == carPosition || revealedDoor == playerChoice);

        // Выбор после того, как одна из дверей была открыта
        int finalChoice = playerChoice == revealedDoor ? playerChoice : 3 - playerChoice - revealedDoor;

        // Проверка, победил ли игрок
        boolean isWin = finalChoice == carPosition;
        return new GameResult(isWin);
    }

    @Getter
    @Setter
    static class GameResult {
        private boolean win;

        public GameResult(boolean win) {
            this.win = win;
        }
    }
}