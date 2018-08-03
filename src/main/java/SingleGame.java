import com.codingame.gameengine.runner.MultiplayerGameRunner;

import java.util.Random;

public class SingleGame {
  public static void main(String[] args) {
    MultiplayerGameRunner gameRunner = new MultiplayerGameRunner();
    System.setProperty("league.level", "4");
    if ((new Random()).nextBoolean()) {
      BatchGames.addSubmitted(gameRunner);
      BatchGames.addIde(gameRunner);
    } else {
      BatchGames.addIde(gameRunner);
      BatchGames.addSubmitted(gameRunner);
      gameRunner.start();
    }
  }


}
