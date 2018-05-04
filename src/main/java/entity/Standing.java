package entity;

// This object represent each the standing of the league
public class Standing {
    private String clubName;
    private Integer matchPlayed;
    private Integer win;
    private Integer draw;
    private Integer lose;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalsDifference;
    private Integer point;

    public Standing(String clubName) {
        this.clubName = clubName;
        this.matchPlayed = 0;
        this.win = 0;
        this.draw = 0;
        this.lose = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
        this.goalsDifference = 0;
        this.point = 0;
    }

    public String getClubName() {
        return clubName;
    }

    public Integer getMatchPlayed() {
        return matchPlayed;
    }

    public Integer getWin() {
        return win;
    }

    public Integer getDraw() {
        return draw;
    }

    public Integer getLose() {
        return lose;
    }

    public Integer getGoalsFor() {
        return goalsFor;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public Integer getGoalsDifference() {
        return goalsDifference;
    }

    public Integer getPoint() {
        return point;
    }

    public void updateScore(Result result, Integer goalsFor, Integer goalsAgainst, Integer point) {
        this.matchPlayed = this.getMatchPlayed() + 1;
        switch (result) {
            case WIN:
                ++this.win;
                break;
            case LOST:
                ++this.lose;
                break;
            case DRAW:
                ++this.draw;
                break;
        }
        this.goalsFor = this.goalsFor + goalsFor;
        this.goalsAgainst = this.goalsAgainst + goalsAgainst;
        this.goalsDifference = this.goalsFor - this.goalsAgainst;
        this.point = this.point + point;
    }
}
