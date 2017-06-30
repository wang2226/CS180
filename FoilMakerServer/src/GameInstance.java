import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Bruce on 11/11/2016.
 */
public class GameInstance extends Thread {
    private static int rightAnswer = 10;
    private static int fooledPlayer = 5;
    private String gameStatus;
    protected String gameKey;
    protected Vector participants = null;
    protected Vector allUsers = null;
    private HashMap userSuggestion;
    private HashMap userChoice;
    protected UserInfo leaderInfo = null;
    protected readDeckFile readDeck = null;
    private WordDeck wordDeck;

    //contructor
    public GameInstance(UserInfo leader, String gamekey, String deckfile) {
        this.leaderInfo = leader;
        this.participants = new Vector();
        this.allUsers = new Vector();
        this.allUsers.add(leader);
        this.gameStatus = "ready";
        this.gameKey = gamekey;
        this.readDeck = new readDeckFile(deckfile);
        this.userSuggestion = new HashMap();
        this.userChoice = new HashMap();
    }

    //thread run()
    //notify all user
    public void run() {

        synchronized (this) {
            this.gameStatus = "playing";

            while (this.gameStatus.equals("playing")) {
                this.gameRound();
            }

            Iterator itAllusers = this.allUsers.iterator();

            while (itAllusers.hasNext()) {
                itAllusers.next();
                this.notify();
            }

        }
    }


    /*
   1:read deck and put it into WordDeck in a random method
   2:get a question-answer pair and sent to all user
   3:get user suggestion and store it
   4:adds the correct answer, scrambles the suggestions and the correct answer
    */
    private void gameRound() {
        Random random = new Random(System.currentTimeMillis());
        String[] questionAnswer = new String[2];
        UserInfo userinfo;

        int deckSize = readDeck.deck.size();

        if (deckSize == 0) {
            gameStatus = "gameover";
        } else {
            wordDeck = (WordDeck) readDeck.deck.remove(readDeck.random.nextInt(deckSize));
            readDeck.usedDeck.add(wordDeck);
            questionAnswer[0] = wordDeck.question;
            questionAnswer[1] = wordDeck.answer;

            Iterator itAllUsers = allUsers.iterator();

            while (itAllUsers.hasNext()) {
                userinfo = (UserInfo) itAllUsers.next();
                userinfo.createGame.writeResponse(MSG_TYPE.NEWGAMEWORD, null, null, questionAnswer);
            }

            userSuggestion.clear();
            itAllUsers = allUsers.iterator();

            while (itAllUsers.hasNext()) {
                userinfo = (UserInfo) itAllUsers.next();
                getUserRequest(userinfo, userSuggestion, MSG_TYPE.PLAYERSUGGESTION);
            }

            Vector suggestion = new Vector();
            suggestion.addAll(userSuggestion.values());
            suggestion.add(wordDeck.answer);
            questionAnswer = new String[suggestion.size()];

            int count = 0;
            while (!suggestion.isEmpty()) {
                questionAnswer[count] = (String) suggestion.remove(random.nextInt(suggestion.size()));
                count++;
            }

            itAllUsers = allUsers.iterator();

            while (itAllUsers.hasNext()) {
                userinfo = (UserInfo) itAllUsers.next();
                userinfo.createGame.writeResponse(MSG_TYPE.ROUNDOPTIONS, null, null, questionAnswer);
            }

            userChoice.clear();
            itAllUsers = allUsers.iterator();

            while (itAllUsers.hasNext()) {
                userinfo = (UserInfo) itAllUsers.next();
                getUserRequest(userinfo, userChoice, MSG_TYPE.PLAYERCHOICE);
            }

            String[] roundResult = new String[5 * userChoice.size()];
            int userNumber = 0;
            itAllUsers = allUsers.iterator();

            while (itAllUsers.hasNext()) {
                UserInfo user = (UserInfo) itAllUsers.next();
                roundResult(user, roundResult, userNumber++);
            }

            itAllUsers = allUsers.iterator();

            while (itAllUsers.hasNext()) {
                userinfo = (UserInfo) itAllUsers.next();
                userinfo.createGame.writeResponse(MSG_TYPE.ROUNDRESULT, null, null, roundResult);
            }
        }
    }

    //Apply Game Logic and Send Results

    private void roundResult(UserInfo userinfo, String[] result, int usernumber) {
        int cumulativeScore = 0;
        int fooledTimes = 0;
        boolean isFooled = false;
        String OverallResults = "";
        String choice = (String) userChoice.get(userinfo);
        String answer = wordDeck.answer;
        String loserName = "";
        String winnerName = "";
        String suggestion = (String) userSuggestion.get(userinfo);

        //get all user exclude self
        Vector users = new Vector();
        users.addAll(allUsers);
        users.remove(userinfo);

        Iterator itUsers;

        if (choice.equals(answer)) {
            cumulativeScore = rightAnswer;
            OverallResults = "You got it right!.";
        } else {
            itUsers = users.iterator();

            while (itUsers.hasNext()) {
                UserInfo user = (UserInfo) itUsers.next();
                if ((userSuggestion.get(user)).equals(choice)) {
                    OverallResults = OverallResults + user.userName + ", ";
                    isFooled = true;
                }
            }
        }

        itUsers = users.iterator();

        while (itUsers.hasNext()) {
            UserInfo user = (UserInfo) itUsers.next();
            if ((userChoice.get(user)).equals(suggestion)) {
                loserName = loserName + user.userName + ", ";
                ++fooledTimes;
            }
        }

        cumulativeScore = cumulativeScore + fooledTimes * fooledPlayer;
        userinfo.cumulativeScore = userinfo.cumulativeScore + cumulativeScore;

        if (isFooled) {
            winnerName = OverallResults.substring(0, OverallResults.length() - 2);
            OverallResults = "You were fooled by " + winnerName + ".";
            ++userinfo.fooledByOthers;
        }

        if (fooledTimes != 0) {
            loserName = loserName.substring(0, loserName.length() - 2);
            OverallResults = OverallResults + "You fooled " + loserName + ".";
            userinfo.fooledTheOthers += fooledTimes;
        }

        result[usernumber * 5] = userinfo.userName;
        result[usernumber * 5 + 1] = OverallResults;
        result[usernumber * 5 + 2] = "" + userinfo.cumulativeScore;
        result[usernumber * 5 + 3] = "" + userinfo.fooledTheOthers;
        result[usernumber * 5 + 4] = "" + userinfo.fooledByOthers;
    }

    //read user's suggetion and store it
    private void getUserRequest(UserInfo userinfo, HashMap pairs, MSG_TYPE msgType) {
        String[] userRequest = new String[2];
        String[] messageArray;
        MSG_TYPE responseType;

        String request = userinfo.createGame.readRequest();

        if (request != null) {
            messageArray = request.split("--");
            responseType = MSG_TYPE.valueOf(messageArray[0]);

            if (responseType != msgType) {
                userRequest[0] = responseType.toString();
                userRequest[1] = msgType.toString();

                userinfo.createGame.writeResponse(MSG_TYPE.RESPONSE, responseType, MSG_DETAIL_T.UNEXPECTEDMESSAGETYPE, userRequest);
            } else if (messageArray.length < 4) {
                userinfo.createGame.writeResponse(MSG_TYPE.RESPONSE, responseType, MSG_DETAIL_T.INVALIDMESSAGEFORMAT, messageArray);
            } else if (!messageArray[1].equals(userinfo.userToken)) {
                userinfo.createGame.writeResponse(MSG_TYPE.RESPONSE, responseType, MSG_DETAIL_T.USERNOTLOGGEDIN, userRequest);
            } else if (!messageArray[2].equals(gameKey)) {
                userinfo.createGame.writeResponse(MSG_TYPE.RESPONSE, responseType, MSG_DETAIL_T.INVALIDGAMETOKEN, userRequest);
            } else {
                pairs.put(userinfo, messageArray[3]);
            }
        }
    }
}
