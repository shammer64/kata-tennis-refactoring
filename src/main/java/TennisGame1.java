import java.util.HashMap;
import java.util.Map;

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
        if (scoreIsTied(m_score1, m_score2)) {
            return computeTiedScore(m_score1);
        } else if (scoreIsLateInGame(m_score1, m_score2)) {
            return computeLateGameScore(m_score1, m_score2);
        } else {
            return computeEarlyGameScore(m_score1, m_score2);
        }
    }

    private static final Map<Integer, String> TIE_SCORE_MAP = new HashMap<Integer, String>(){{
        put(0, "Love-All");
        put(1, "Fifteen-All");
        put(2, "Thirty-All");
        put(3, "Deuce");
    }};

    private String computeTiedScore(int score1) {
        if (score1 < 3)
            return TIE_SCORE_MAP.get(score1);
        else
            return TIE_SCORE_MAP.get(3);
    }

    private static final Map<Integer, String> EARLY_SCORE_MAP = new HashMap<Integer, String>(){{
        put(0, "Love");
        put(1, "Fifteen");
        put(2, "Thirty");
        put(3, "Forty");
    }};

    private String computeEarlyGameScore(int score1, int score2) {
        return EARLY_SCORE_MAP.get(score1)
                + "-"
                + EARLY_SCORE_MAP.get(score2);
    }

    private String computeLateGameScore(int score1, int score2) {
        int scoreDiff = score1 - score2;
        String player = scoreDiff > 0 ? player1Name : player2Name;
        String result = Math.abs(scoreDiff) == 1 ? "Advantage " : "Win for ";
        return result + player;
    }

    private boolean scoreIsTied(int score1, int score2) {
        return score1 == score2;
    }

    private boolean scoreIsLateInGame(int score1, int score2) {
        return score1 >= 4 || score2 >= 4;
    }

}
