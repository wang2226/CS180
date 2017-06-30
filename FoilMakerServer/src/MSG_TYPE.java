/**
 * Created by Bruce on 11/10/2016.
 */
public enum  MSG_TYPE {
    //Client messages to server

    CREATENEWUSER, //Tokens: userName  passWord
    LOGIN, //Tokens: userName password
    LOGOUT, //Tokens:  currentLoginToken?
    STARTNEWGAME, //No tokens?
    JOINGAME, //Tokens: currentLoginToken gameKey
    ALLPARTICIPANTSHAVEJOINED, //Send from leader to server; Tokens: currentLoginToken gameKey

    //Client message to server during a game
    PLAYERCHOICE, //Tokens: currentLoginToken gameKey user'sChoice
    PLAYERSUGGESTION, //Tokens: currentLoginToken gameKey user'sChoice

    // Server messages to client
    NEWPARTICIPANT, //From server to leader; Tokens: participantName cummulativeScore
    RESPONSE, //Server response to user request
                    /* Tokens:
                     * clientRequestMsgType -- the MSG_TYPE of the client's request
                     * responseDetail -- the MSG_DETAIL_T of the server's response
                     * <Other optional tokens specific to MSG_DETAIL_T>
                     */


    //Server messages to clients during a game
    NEWGAMEWORD, //From server to players; Tokens: cardFrontText cardBackText
    ROUNDOPTIONS, //From server to players; Tokens: randomized list of user suggestions and true answer
    ROUNDRESULT, //From server to players; Tokens: uName1 score1 message1 uName2 score2 message2 ....
    GAMEOVER //From server to players: Tokens: MSG_DETAIL
}
