import com.codingame.gameengine.runner.MultiplayerGameRunner;
import com.codingame.gameengine.runner.dto.GameResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;


public class BatchGames {
  public static void main(String[] args) {
    final Optional<Map<Integer, Integer>> totalScores = IntStream
      .range(0, 20)
      .mapToObj(BatchGames::playOnce)
      .reduce(BatchGames::mergeScores);
    System.out.println("submitted : 0, ide : 1");
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
      PrepareAgent.addIdeLast(gameRunner);
      return gameRunner.simulate().scores;
    } else {
      PrepareAgent.addIdeFirst(gameRunner);
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


}
