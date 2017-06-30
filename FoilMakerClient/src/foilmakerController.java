/**
 * Created by Bruce on 10/22/2016.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.lang.*;
import java.net.SocketTimeoutException;

public class foilmakerController extends Exception {
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private playerModel player;
    private gameModel game;


    public static enum MSG_TYPE {
        //Client messages to server

        CREATENEWUSER, // Tokens: userName  passWord
        LOGIN, // Tokens: userName password
        LOGOUT, // Tokens:  currentLoginToken?
        STARTNEWGAME, // No tokens?
        JOINGAME, // Tokens: currentLoginToken gameKey
        ALLPARTICIPANTSHAVEJOINED, // Send from leader to server; Tokens: currentLoginToken gameKey

        //Client message to server during a game
        PLAYERCHOICE, // Tokens: currentLoginToken gameKey user'sChoice
        PLAYERSUGGESTION, // Tokens: currentLoginToken gameKey user'sChoice

        // Server messages to client
        NEWPARTICIPANT, //From server to leader; Tokens: participantName cummulativeScore
        RESPONSE, // Server response to user request
                    /* Tokens:
                     * clientRequestMsgType -- the MSG_TYPE of the client's request
                     * responseDetail -- the MSG_DETAIL_T of the server's response
                     * <Other optional tokens specific to MSG_DETAIL_T>
                     */


        //Server messages to clients during a game
        NEWGAMEWORD, // From server to players; Tokens: cardFrontText cardBackText
        ROUNDOPTIONS, // From server to players; Tokens: randomized list of user suggestions and true answer
        ROUNDRESULT, //From server to players; Tokens: uName1 score1 message1 uName2 score2 message2 ....
        GAMEOVER // From server to players: Tokens: MSG_DETAIL
    }

    ;

    public static enum MSG_DETAIL_T {
        SUCCESS, // Request was successfull. For LOGIN: currentLoginToken;  For STARTNEWGAME: gameKey; For JOINGAME:
        // gameKey;
        INVALIDUSERNAME,
        INVALIDUSERPASSWORD,
        USERALREADYEXISTS,
        UNKNOWNUSER,
        USERALREADYLOGGEDIN,
        GAMEKEYNOTFOUND,
        NO_CONNECTION_TO_SERVER,
        ERROR_OPENING_NETWORK_CONNECTION,
        USERNOTLOGGEDIN,
        USERNOTGAMELEADER,
        INVALIDGAMETOKEN,
        UNEXPECTEDMESSAGETYPE,
        INVALIDMESSAGEFORMAT, //TODO received msg with tokens EXPECTING: expected format
        FAILURE // optional details of failure cause
    }

    //TODO Create error codes type and values
    public static final String SEPARATOR = "--";
    public static final int LOGIN_TOKEN_LENGTH = 10;
    public static final int GAME_KEY_LENGTH = 3;

    public foilmakerController(playerModel player, gameModel game) {
        try {
            String serverIP = "localhost";
            serverPort = 50000;
            //Connect to server
            this.socket = new Socket(serverIP, serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.player = player;
        this.game = game;
    }


    public String callServer(String message) throws IOException {

        // Create stream writer/reader
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Send message to server
        out.println(message);

        String output = in.readLine();

        // Return server response
        return output;
    }

        public void readServer() throws IOException {
        // Create stream writer/reader
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Set socket timeout to 1 msec before attempting to read response
        socket.setSoTimeout(1);

        String out = null;

        // Loop until the response is received
        while (out == null) {
            if(!foilmakerGUI.buttonNotClick) {
                // Make socket blocking
                socket.setSoTimeout(0);
                return;
            }

            try {
                out = in.readLine();
            } catch (SocketTimeoutException e) {
                // Do nothing
            }

            // Wait 1 msec before retrying
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }

        // Make socket blocking
        socket.setSoTimeout(0);

        // Return server response
        decoding(out, player, game);
    }

    public boolean sentServer(String type) throws IOException {
        String request = "";

        switch (type) {
            case "CREATENEWUSER":
                request = encoding(MSG_TYPE.CREATENEWUSER, player, game);
                break;

            case "LOGIN":
                request = encoding(MSG_TYPE.LOGIN, player, game);
                break;

            case "STARTNEWGAME":
                request = encoding(MSG_TYPE.STARTNEWGAME, player, game);
                break;

            case "ALLPARTICIPANTSHAVEJOINED":
                request = encoding(MSG_TYPE.ALLPARTICIPANTSHAVEJOINED, player, game);
                break;

            case "JOINGAME":
                request = encoding(MSG_TYPE.JOINGAME, player, game);
                break;

            case "PLAYERSUGGESTION":
                request = encoding(MSG_TYPE.PLAYERSUGGESTION, player, game);
                break;

            case "PLAYERCHOICE":
                request = encoding(MSG_TYPE.PLAYERCHOICE, player, game);
                break;

            case "LOGOUT":
                request = encoding(MSG_TYPE.LOGOUT, player, game);
                break;
        }
        String response = callServer(request);
        return decoding(response, player, game);
    }

    public static String encoding(MSG_TYPE messageType, playerModel player, gameModel game) {
        String output = "";
        switch (messageType) {

            case LOGIN: // Tokens: userName  passWord
            case CREATENEWUSER:
                output = messageType.toString() + SEPARATOR + player.getPlayerName() +
                        SEPARATOR + player.getPlayerPassword();
                break;

            case LOGOUT: // Tokens:  currentLoginToken?
                output = messageType.toString() + SEPARATOR;
                break;

            case STARTNEWGAME: // No tokens
                output = messageType.toString() + SEPARATOR + player.getPlayerToken();
                break;

            case ALLPARTICIPANTSHAVEJOINED: // Send from leader to server; Tokens: currentLoginToken gameKey
            case JOINGAME: // Tokens: currentLoginToken gameKey
                output = messageType.toString() + SEPARATOR + player.getPlayerToken() +
                        SEPARATOR + game.getGameKey();
                break;

            //Client message to server during a game
            case PLAYERCHOICE: // Tokens: currentLoginToken gameKey user'sChoice
                output = messageType.toString() + SEPARATOR + player.getPlayerToken() +
                        SEPARATOR + game.getGameKey() + SEPARATOR + player.getPlayerOption();

                break;


            case PLAYERSUGGESTION: // Tokens: currentLoginToken gameKey user'sChoice
                output = messageType.toString() + SEPARATOR + player.getPlayerToken() +
                        SEPARATOR + game.getGameKey() + SEPARATOR + player.getPlayerSuggestion();
                break;
        }
        return output;
    }

    public static boolean decoding(String response, playerModel player, gameModel game) {
        String[] responseArray = response.split(SEPARATOR);
        MSG_TYPE msg_header = MSG_TYPE.valueOf(responseArray[0]);
        boolean errorFlag = false;

        switch (msg_header) {
            case RESPONSE: // Server response to user request
                MSG_TYPE msg_type = MSG_TYPE.valueOf(responseArray[1]);
                MSG_DETAIL_T msg_detail_t = MSG_DETAIL_T.valueOf(responseArray[2]);

                if (msg_type.equals(MSG_TYPE.LOGIN) && msg_detail_t.equals(MSG_DETAIL_T.SUCCESS)) {
                    player.setPlayerToken(responseArray[3]);
                } else if (msg_type.equals(MSG_TYPE.STARTNEWGAME) && msg_detail_t.equals(MSG_DETAIL_T.SUCCESS)) {
                    game.setGameKey(responseArray[3]);
                } else if (msg_detail_t.equals(MSG_DETAIL_T.SUCCESS)) {
                    errorFlag = false;
                } else {
                    game.setGameStatus(getErrorMeaning(msg_detail_t, msg_type));
                    errorFlag = true;
                }
                break;

            case NEWPARTICIPANT: //From server to leader; Tokens: participantName cummulativeScore
                player.setGameParticipants(responseArray[1]);
                player.setParticipantScoe(responseArray[2]);
                break;

            //Server messages to clients during a game
            case NEWGAMEWORD: // From server to players; Tokens: cardFrontText cardBackText
                game.setGameQuestion(responseArray[1]);
                game.setAnswer(responseArray[2]);
                break;

            case ROUNDOPTIONS: // From server to players; Tokens: randomized list of user suggestions and true answer
                String[] candidate = response.split(SEPARATOR);
                for (int i = 1; i < candidate.length; i++) {
                    game.setCandidateAnswer(candidate[i]);
                }
                break;

            case ROUNDRESULT: //From server to players; Tokens: uName1 score1 message1 uName2 score2 message2 ....
                String[] roundResult = response.split(SEPARATOR);
                for (int i = 1; i < roundResult.length; ) {
                    if (roundResult[i].equals(player.getPlayerName())) {
                        player.setRoundResult(roundResult[i + 1]);
                    }
                    String overallResult = roundResult[i] + "=> Score: " + roundResult[i + 2] + " | Fooled : " +
                            roundResult[i + 3] + "player(s) | Fooled by: " + roundResult[i + 4] + " player(s)";
                    game.setGameOverallresults(overallResult);
                    i = i + 5;
                }
                break;

            case GAMEOVER:// From server to players: Tokens: MSG_DETAIL
                game.setIsGameOver(true);
                break;
        }
        return errorFlag;
    }

    public static String getErrorMeaning(MSG_DETAIL_T msg_detail_t, MSG_TYPE msg_type) {
        String message = "";
        switch (msg_detail_t) {
            case SUCCESS: // Request was successfull. For LOGIN: currentLoginToken;  For STARTNEWGAME: gameKey; For JOINGAME:
                // gameKey;
                switch (msg_type) {
                    case CREATENEWUSER:
                        message = "User created in the user store successfully.";
                        break;

                    case LOGIN:
                        message = "User logged into the system successfully.";
                        break;

                    case LOGOUT:
                        message = "User logged out of the system successfully.";
                        break;

                    default:
                        message = "A new game session created successfully.";
                        break;
                }
                break;

            case INVALIDUSERNAME:
                message = "Username empty.";
                break;

            case INVALIDUSERPASSWORD:
                switch (msg_type) {
                    case CREATENEWUSER:
                        message = "Password empty.";
                        break;
                    default:
                        message = "Invalid password (Use not authenticated).";
                        break;
                }
                break;

            case USERALREADYEXISTS:
                message = "User already exists in the user store.";
                break;

            case UNKNOWNUSER:
                message = "Invalid username";
                break;

            case USERALREADYLOGGEDIN:
                message = "User already logged in.";
                break;

            case GAMEKEYNOTFOUND:
                message = "Invalid game token.";
                break;

            case NO_CONNECTION_TO_SERVER:
                break;

            case ERROR_OPENING_NETWORK_CONNECTION:
                break;

            case USERNOTLOGGEDIN:
                switch (msg_type) {
                    case LOGOUT:
                        message = "User currently not logged in.";
                        break;
                    default:
                        message = "Invalid user token.";
                        break;
                }
                break;

            case USERNOTGAMELEADER:
                message = "User already playing the game.";
                break;

            case INVALIDGAMETOKEN:
                message = "Invalid game token.";
                break;

            case UNEXPECTEDMESSAGETYPE:
                message = "A suggestion was sent when a different message was expected" +
                        " by the server.";
                break;

            case INVALIDMESSAGEFORMAT: //TODO received msg with tokens EXPECTING: expected format
                message = "Request does not comply with the format given above.";
                break;

            case FAILURE:// optional details of failure cause
                switch (msg_type) {
                    case STARTNEWGAME:
                        message = "User already playing or server failed to create " +
                                "a new game session due to an internal error.";
                        break;
                    case JOINGAME:
                        message = "User already playing the game.";
                        break;

                    default:
                        //     message = "Invalid game token.";
                        break;
                }
        }
        return message;
    }

}
