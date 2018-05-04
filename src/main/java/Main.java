import entity.MatchResult;
import entity.Standing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        // Read data from clubs.txt to Array<Club>
        // Read data from results.txt to Array<Result>
        // For 3 points for win, 1 point for draw, 0 point for lost
        // Sorting the Array<Standing> from order Points -> GD -> GF
        // Write Array<Standing> to standings.txt

        File clubsFile = new File("clubs.txt"); // Should not hardcoded file path
        File resultsFile = new File("results.txt"); // Should not hardcoded file path
        List<Standing> result = null;
        try(
                Stream<String> clubsStream = Files.lines(clubsFile.toPath());
                Stream<String> resultStream = Files.lines(resultsFile.toPath());
        ) {
            // Read data from clubs.txt to Array<Club>
            // this represent the standing.txt list
            Map<String, Standing> standingSet = clubsStream
                    .map(Standing::new)
                    .collect(Collectors.toMap(Standing::getClubName, standing -> standing));

            // Read data from results.txt to Array<Result>
            List<MatchResult> matchResultSet = resultStream
                    .filter(s -> s.contains(":")) // get only match results
                    .map(Main::mapLineToMatchResult)
                    .collect(Collectors.toList());

            result = mapMatchResultToStanding(standingSet, matchResultSet);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("standings.txt")))
        {
            writer.write("RANK\tCLUB\t\t\t\tMP\t\tW\t\tD\t\tL\t\tGF\t\tGA\t\tGD\t\tPTS");
            writer.newLine();
            for (int i = 0; i < result.size(); i++) {
                Standing standing = result.get(i);
                writer.write(String.format("%d\t\t%-20s%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d", i+1, standing.getClubName(), standing.getMatchPlayed(), standing.getWin(),standing.getDraw(), standing.getLose(), standing.getGoalsFor(), standing.getGoalsAgainst(), standing.getGoalsDifference(),standing.getPoint()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Standing> mapMatchResultToStanding(Map<String, Standing> standingSet, List<MatchResult> matchResultSet) {
        for (MatchResult matchResult : matchResultSet) {
            Standing firstClub = standingSet.get(matchResult.getFirstClub());
            Standing secondClub = standingSet.get(matchResult.getSecondClub());
            firstClub.updateScore(matchResult.getResultForFirstClub(), matchResult.getFirstClubScore(), matchResult.getSecondClubScore(),matchResult.getFirstClubPoint());
            secondClub.updateScore(matchResult.getResultForSecondClub(), matchResult.getSecondClubScore(), matchResult.getFirstClubScore(), matchResult.getSecondClubPoint());
            standingSet.put(matchResult.getFirstClub(), firstClub);
            standingSet.put(matchResult.getSecondClub(), secondClub);
        }
        List<Standing> tmp = new ArrayList<>(standingSet.values());
        tmp.sort((o1, o2) -> {
            if(!o1.getPoint().equals(o2.getPoint())) {
                return (int) (o2.getPoint() - o1.getPoint());
            }
            if(!o1.getGoalsDifference().equals(o2.getGoalsDifference()))
                return (int) (o2.getGoalsDifference() - o1.getGoalsDifference());
            if(!o1.getGoalsFor().equals(o2.getGoalsFor()))
                return (int) (o2.getGoalsFor() - o1.getGoalsFor());
            return 0;
        });
        return tmp;
    }

    // line format should be "[team 1] 0:0 [team 2]"
    private static MatchResult mapLineToMatchResult(String lines) {
        String[] splittedStr = lines.split(":");
        int rightScore = Character.getNumericValue(splittedStr[0].charAt(splittedStr[0].length() - 1));
        int leftScore = Character.getNumericValue(splittedStr[1].charAt(0));
        String club1 = splittedStr[0].substring(0, splittedStr[0].length() - 1).trim();
        String club2 = splittedStr[1].substring(1, splittedStr[1].length()).trim();
        return new MatchResult(club1, club2, rightScore, leftScore);
    }
}
