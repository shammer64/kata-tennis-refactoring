public class TennisGame1 implements TennisGame {

    private final String player1Name;
    private final String player2Name;
    private int m_score1 = 0;
    private int m_score2 = 0;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        String score = "";
        int tempScore = 0;
        if (scoreIsTied(m_score1, m_score2)) {
            score = getTiedScore(m_score1);
        } else if (scoreIsLateInGame(m_score1, m_score2)) {
            score = getLateGameScore(m_score1, m_score2);
        } else {
            score = getEarlyGameScore(m_score1, m_score2);
        }
        return score;
    }

    private String getTiedScore(int score1) {
        String score;
        switch (score1) {
            case 0:
                score = "Love-All";
                break;
            case 1:
                score = "Fifteen-All";
                break;
            case 2:
                score = "Thirty-All";
                break;
            default:
                score = "Deuce";
                break;

        }
        return score;
    }

    private String getEarlyGameScore(int score1, int score2) {
        String score = "";
        int tempScore;
        for (int i = 1; i < 3; i++) {
            if (i == 1) tempScore = score1;
            else {
                score += "-";
                tempScore = score2;
            }
            switch (tempScore) {
                case 0:
                    score += "Love";
                    break;
                case 1:
                    score += "Fifteen";
                    break;
                case 2:
                    score += "Thirty";
                    break;
                case 3:
                    score += "Forty";
                    break;
            }
        }
        return score;
    }

    private String getLateGameScore(int score1, int score2) {
        String score;
        int scoreDiff = score1 - score2;
        if (scoreDiff == 1) score = "Advantage player1";
        else if (scoreDiff == -1) score = "Advantage player2";
        else if (scoreDiff >= 2) score = "Win for player1";
        else score = "Win for player2";
        return score;
    }

    private boolean scoreIsTied(int score1, int score2) {
        return score1 == score2;
    }

    private boolean scoreIsLateInGame(int score1, int score2) {
        return score1 >= 4 || score2 >= 4;
    }

}
