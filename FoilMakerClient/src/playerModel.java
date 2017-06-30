/**
 * Created by Bruce on 10/22/2016.
 */
import java.util.ArrayList;

public class playerModel {
    private String playerName;
    private String playerPassword;
    private String playerToken;
    private String playerSuggestion;
    private String playerOption;
    private String roundResult;
    private ArrayList<String> gameParticipants;
    private ArrayList<String> participantScoe;

    public playerModel() {
        gameParticipants = new ArrayList<>();
        participantScoe = new ArrayList<>();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerPassword() {
        return playerPassword;
    }

    public void setPlayerPassword(String playerPassword) {
        this.playerPassword = playerPassword;
    }

    public String getPlayerToken() {
        return playerToken;
    }

    public void setPlayerToken(String playerToken) {
        this.playerToken = playerToken;
    }

    public String getPlayerSuggestion() {
        return playerSuggestion;
    }

    public void setPlayerSuggestion(String playerSuggestion) {
        this.playerSuggestion = playerSuggestion;
    }

    public String getPlayerOption() {
        return playerOption;
    }

    public void setPlayerOption(String playerOption) {
        this.playerOption = playerOption;
    }

    public String getRoundResult() {
        return roundResult;
    }

    public void setRoundResult(String roundResult) {
        this.roundResult = roundResult;
    }

    public ArrayList<String> getGameParticipants() {
        return gameParticipants;
    }

    public void setGameParticipants(String gameParticipants) {
        this.gameParticipants.add(gameParticipants);
    }

    public void setParticipantScoe(String participantScoe) {
        this.participantScoe.add(participantScoe);
    }
}
