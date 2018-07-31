import com.codingame.gameengine.runner.MultiplayerGameRunner;
import com.codingame.gameengine.runner.dto.GameResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class Main {
  public static void main(String[] args) throws Exception {

    final Optional<Map<Integer, Integer>> totalScores = IntStream
      .range(0, 10)
      .mapToObj(Main::playOnce)
      .reduce(Main::mergeScores);
    System.out.println(totalScores);
  }

  private static Map<Integer, Integer> mergeScores(final Map<Integer, Integer> r, final Map<Integer, Integer> s) {
    final HashMap<Integer, Integer> merge = new HashMap<>();
    merge.put(0, r.get(0) + s.get(0));
    merge.put(1, r.get(1) + s.get(1));
    return merge;
  }

  private static Map<Integer, Integer> playOnce(final int i) {
    System.out.println("playing game #" + i);
    MultiplayerGameRunner gameRunner = new MultiplayerGameRunner();
    System.setProperty("league.level", "4");
    if (i % 2 == 0) {
      addSubmitted(gameRunner);
      addIde(gameRunner);
      return gameRunner.simulate().scores;
    } else {
      addIde(gameRunner);
      addSubmitted(gameRunner);
      final GameResult result = gameRunner.simulate();
      return swapScores(result.scores);

    }
  }

  private static Map<Integer, Integer> swapScores(final Map<Integer, Integer> scores) {
    final HashMap<Integer, Integer> swapped = new HashMap<>();
    swapped.put(0, scores.get(1));
    swapped.put(1, scores.get(0));
    return swapped;
  }

  private static void addIde(final MultiplayerGameRunner gameRunner) {
    gameRunner.addAgent("scala.bat C:\\Users\\tyrcho\\git\\codingame-scala-kit\\target\\scala-2.12\\codingame-scala-kit_2.12-0.1.0.jar", "IDE", "no url");
  }

  private static void addSubmitted(final MultiplayerGameRunner gameRunner) {
    gameRunner.addAgent("scala.bat C:\\Users\\tyrcho\\git\\codingame-scala-kit\\submitted.jar", "submitted", "no url");
  }
}
