import com.codingame.gameengine.runner.MultiplayerGameRunner;

import java.io.File;


class PrepareAgent {
  private static final String opponent = "silver";

  static void addIdeFirst(final MultiplayerGameRunner gameRunner) {
   addIde(gameRunner);
   addOpponent(gameRunner);
 }

  static void addIdeLast(final MultiplayerGameRunner gameRunner) {
   addOpponent(gameRunner);
   addIde(gameRunner);
 }

  private static void addIde(final MultiplayerGameRunner gameRunner) {
    addScalaAgent(gameRunner, "../codingame-scala-kit/target/scala-2.12/codingame-scala-kit_2.12-0.1.0.jar", "IDE");
  }

  private static void addOpponent(final MultiplayerGameRunner gameRunner) {
    addScalaAgent(gameRunner, String.format("../codingame-scala-kit/%s.jar", opponent), opponent);
  }

  private static void addScalaAgent(final MultiplayerGameRunner gameRunner, final String jarFile, final String nickname) {
    final String commandLine = String.format("%s %s", scalaCommand(), new File(jarFile).getAbsolutePath());
    System.out.println(commandLine);
    gameRunner.addAgent(commandLine, nickname, null);
  }

  private static String scalaCommand() {
    return System.getProperty("os.name").contains("Win")
      ? "scala.bat"
      : "scala";

  }
}
