import com.codingame.gameengine.runner.MultiplayerGameRunner;

public class SingleGame {

  public static void main(String[] args) {
    MultiplayerGameRunner gameRunner = new MultiplayerGameRunner();
    System.setProperty("league.level", "4");
//    if ((new Random()).nextBoolean()) {
    PrepareAgent.addIdeLast(gameRunner);
//    } else {
//      PrepareAgent.addIdeFirst(gameRunner);
    gameRunner.start();

  }


}
