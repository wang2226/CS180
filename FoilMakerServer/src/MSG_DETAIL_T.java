/**
 * Created by Bruce on 11/10/2016.
 */
public enum MSG_DETAIL_T {
    SUCCESS, //Request was successfull. For LOGIN: currentLoginToken;  For STARTNEWGAME: gameKey; For JOINGAME:
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
    FAILURE //optional details of failure cause
}
