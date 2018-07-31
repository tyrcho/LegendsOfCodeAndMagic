import com.codingame.gameengine.runner.MultiplayerGameRunner;

public class Main {
  public static void main(String[] args) throws Exception {

    MultiplayerGameRunner  gameRunner = new MultiplayerGameRunner();
//    gameRunner.addAgent(Player1.class, "java player 1", "no url");
    gameRunner.addAgent("scala.bat C:\\Users\\tyrcho\\git\\codingame-scala-kit\\submitted.jar", "submitted", "no url");
    gameRunner.addAgent("scala.bat C:\\Users\\tyrcho\\git\\codingame-scala-kit\\target\\scala-2.12\\codingame-scala-kit_2.12-0.1.0.jar", "IDE", "no url");
    // gameRunner.addAgent("python3 /home/user/player.py");

    System.setProperty("league.level", "4");
//    startCommandLine(gameRunner);
    gameRunner.start();
  }
}
