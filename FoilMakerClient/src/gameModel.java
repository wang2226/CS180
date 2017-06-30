import java.util.ArrayList;

/**
 * Created by Bruce on 10/22/2016.
 */
public class gameModel {
    private String gameKey;
    private String gameQuestion;
    private String answer;
    private String gameStatus;
    private ArrayList<String> candidateAnswer;
    private ArrayList<String> gameOverallresults;
    private boolean isGameOver = false;

    public gameModel() {
        gameQuestion = new String();
        candidateAnswer = new ArrayList<>();
        gameOverallresults = new ArrayList<>();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getGameKey() {
        return gameKey;
    }

    public void setGameKey(String gameKey) {
        this.gameKey = gameKey;
    }

    public String getGameQuestion() {
        return gameQuestion;
    }

    public void setGameQuestion(String gameQuestion) {
        this.gameQuestion = gameQuestion;
    }

    public ArrayList<String> getCandidateAnswer() {
        return candidateAnswer;
    }

    public void setCandidateAnswer(String candidate) {
        this.candidateAnswer.add(candidate);
    }

    public ArrayList<String> getGameOverallresults() {
        return gameOverallresults;
    }

    public void setGameOverallresults(String Overallresults) {
        this.gameOverallresults.add(Overallresults);
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
}
