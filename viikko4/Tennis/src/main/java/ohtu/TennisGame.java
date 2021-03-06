package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1"))
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return getEvenScore();
        }
        else if (player1Score >= 4 || player2Score >= 4) {
            return declareWinner();
        }
        else {
            return getNonEvenScore();
        }
    }
    
    public String getNonEvenScore() {
        int tempScore = 0;
        String score = "";
        for (int i = 1; i < 3; i++) {
                if (i == 1) tempScore = player1Score;
                else { score += "-"; tempScore = player2Score;}
                switch(tempScore) {
                    case 0:
                        score += "Zero (Love)";
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
    
    public String declareWinner() {
        int minusResult = player1Score - player2Score;
        
        if (minusResult == 1) return "Advantage player1";
        else if (minusResult == -1) return "Advantage player2";
        else if (minusResult >= 2) return "Win for player1";
        else return "Win for player2";
    }
    
    public String getEvenScore() {
        
            switch (player1Score) {
                case 0:
                        return "Love-All";
                case 1:
                        return "Fifteen-All";
                case 2:
                        return "Thirty-All";
                case 3:
                        return "Forty-All";
                default:
                        return "Deuce";
            }
    }
}