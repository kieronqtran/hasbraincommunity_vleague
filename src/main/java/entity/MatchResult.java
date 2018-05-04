package entity;

import java.util.Objects;

public class MatchResult {
    private String firstClub;
    private String secondClub;
    private Integer firstClubScore;
    private Integer secondClubScore;

    public MatchResult() {
    }

    public MatchResult(String firstClub, String secondClub, Integer firstClubScore, Integer secondClubScore) {
        this.firstClub = firstClub;
        this.secondClub = secondClub;
        this.firstClubScore = firstClubScore;
        this.secondClubScore = secondClubScore;
    }

    public String getFirstClub() {
        return firstClub;
    }

    public void setFirstClub(String firstClub) {
        this.firstClub = firstClub;
    }

    public String getSecondClub() {
        return secondClub;
    }

    public void setSecondClub(String secondClub) {
        this.secondClub = secondClub;
    }

    public Integer getFirstClubScore() {
        return firstClubScore;
    }

    public void setFirstClubScore(Integer firstClubScore) {
        this.firstClubScore = firstClubScore;
    }

    public Integer getSecondClubScore() {
        return secondClubScore;
    }

    public void setSecondClubScore(Integer secondClubScore) {
        this.secondClubScore = secondClubScore;
    }

    public Boolean isDraw() {
        return this.firstClubScore.equals(this.secondClubScore);
    }

    public String getWinnerClub() {
        if(this.firstClubScore > this.secondClubScore) {
            return this.firstClub;
        } else if(this.firstClubScore < this.secondClubScore) {
            return this.secondClub;
        }
        return null;
    }

    public String getLostClub() {
        if(this.firstClubScore < this.secondClubScore) {
            return this.firstClub;
        } else if(this.firstClubScore > this.secondClubScore) {
            return this.secondClub;
        }
        return null;
    }

    public Integer getFirstClubPoint() {
        if(this.isDraw()) {
            return 1;
        }
        if(this.getWinnerClub().equals(this.firstClub)) {
            return 3;
        }
        return 0;
    }

    public Integer getSecondClubPoint() {
        if(this.isDraw()) {
            return 1;
        }
        if(this.getWinnerClub().equals(this.secondClub)) {
            return 3;
        }
        return 0;
    }

    public Integer getPointForClub(String clubName) {
        if(clubName.equals(this.firstClub)) {
            return this.getFirstClubPoint();
        } else if(clubName.equals(this.secondClub)) {
            return this.getSecondClubPoint();
        }
        return -1;
    }

    public Integer getScoreForClub(String clubName) {
        if(clubName.equals(this.firstClub)) {
            return this.getFirstClubScore();
        } else if(clubName.equals(this.secondClub)) {
            return this.getSecondClubScore();
        }
        return -1;
    }

    public Result getResultForClub(String clubName) {
        if(this.isDraw()) {
            return Result.DRAW;
        }

        if(this.getWinnerClub().equals(clubName)) {
            return Result.WIN;
        }

        return Result.LOST;
    }

    public Result getResultForFirstClub() {
        return this.getResultForClub(this.firstClub);
    }


    public Result getResultForSecondClub() {
        return this.getResultForClub(this.secondClub);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchResult)) return false;
        MatchResult that = (MatchResult) o;
        return Objects.equals(firstClub, that.firstClub) &&
                Objects.equals(secondClub, that.secondClub);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstClub, secondClub);
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "firstClub='" + firstClub + '\'' +
                ", secondClub='" + secondClub + '\'' +
                ", firstClubScore=" + firstClubScore +
                ", secondClubScore=" + secondClubScore +
                '}';
    }
}
