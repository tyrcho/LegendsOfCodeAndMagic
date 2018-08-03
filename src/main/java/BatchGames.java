import com.codingame.gameengine.runner.MultiplayerGameRunner;
import com.codingame.gameengine.runner.dto.GameResult;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class BatchGames {

    public static void main(String[] args) {
        final Optional<Map<Integer, Integer>> totalScores = IntStream
                .range(0, 10)
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

    static void addIde(final MultiplayerGameRunner gameRunner) {
        addScalaAgent(gameRunner, "../codingame-scala-kit/target/scala-2.12/codingame-scala-kit_2.12-0.1.0.jar", "IDE");
    }

    static void addSubmitted(final MultiplayerGameRunner gameRunner) {
        addScalaAgent(gameRunner, "../codingame-scala-kit/submitted.jar", "submitted");
    }

    private static void addScalaAgent(final MultiplayerGameRunner gameRunner, final String jarFile, final String nickname) {
        final String commandLine = String.format("%s %s", scalaCommand(), new File(jarFile).getAbsolutePath());
        gameRunner.addAgent(commandLine, nickname, null);
    }

    private static final String scalaCommand() {
        return System.getProperty("os.name").contains("win")
                ? "scala.bat"
                : "scala";

    }
}
