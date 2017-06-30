/**
 * Created by Bruce on 11/10/2016.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public  class CreateGame extends Thread {
    private PrintWriter out = null;
    private BufferedReader in = null;
    private UserInfo userInfo = null;
    private GameInstance gameInstance = null;
    private Socket socket;
    private FoilMakerServer fmServer = null;

    //add it to server, build PrintWriter and BufferedReader for this game
    public CreateGame(FoilMakerServer fmServer, Socket socket) throws IOException {
        this.fmServer = fmServer;
        this.socket = socket;

        try {
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("create PrintWriter or BufferedReader error");
        }
    }

    //response to various client message, and call relevant method
    public void run() {
        String request = readRequest();

        if (request != null) {
            do {
                String[] messageArray = request.split("--");
                MSG_TYPE msgType = MSG_TYPE.valueOf(messageArray[0]);

                switch (msgType) {
                    case CREATENEWUSER:
                        responseCreatenewuser(messageArray);
                        break;
                    case LOGIN:
                        responseLogin(messageArray);
                        break;
                    case LOGOUT:
                        this.responseLogout(messageArray);
                        break;
                    case STARTNEWGAME:
                        responseStartnewgame(messageArray);
                        break;
                    case JOINGAME:
                        this.responseJoinGame(messageArray);
                        break;
                    case ALLPARTICIPANTSHAVEJOINED:
                        this.responseAllHaveJoined(messageArray);
                        break;
                    default:
                        System.out.println("received unknown request");
                }
                request = readRequest();
            } while (request != null);
        }

        try {
            if (out != null) {
                out.close();
            }

            if (in != null) {
                in.close();
            }

            if (socket != null) {
                socket.close();
            }

        } catch (IOException e) {
            System.out.println("when close socket, there is error");
        }
    }

    private boolean illegalUserName(String username) {
        String reg = "^[A-Za-z0-9_]{1,9}$";
        if (!username.matches(reg)) {
            return true;
        }

        return false;
    }

    private boolean illegalPassWord(String password) {
        String reg = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[A-Za-z0-9#$&*]).{1,9}$";
        if (!password.matches(reg)) {
            return true;
        }

        return false;
    }

    //reponse to CREATENEWUSER
    private void responseCreatenewuser(String[] messageArray) {
        if (messageArray.length >= 3 && messageArray[1] != null && messageArray[2] != null) {
            String userName = messageArray[1];
            String passWord = messageArray[2];
            boolean illegalUserName = illegalUserName(userName);
            boolean illegalPassWord = illegalPassWord(passWord);
            boolean haveUser;

            synchronized (fmServer.userPool) {
                haveUser = fmServer.userPool.containsKey(userName);
            }

            if (illegalUserName) {
                writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.CREATENEWUSER, MSG_DETAIL_T.INVALIDUSERNAME, messageArray);
            } else if (haveUser) {
                writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.CREATENEWUSER, MSG_DETAIL_T.USERALREADYEXISTS, messageArray);
            } else if (illegalPassWord) {
                writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.CREATENEWUSER, MSG_DETAIL_T.INVALIDUSERPASSWORD, messageArray);
            } else {
                userInfo = new UserInfo(userName, passWord);
                fmServer.addUserInfo(userName, userInfo);
                String[] response = {""};
                writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.CREATENEWUSER, MSG_DETAIL_T.SUCCESS, response);
            }
        } else {
            writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.CREATENEWUSER, MSG_DETAIL_T.INVALIDMESSAGEFORMAT, messageArray);
        }
    }

    //reponse to LOGIN
    private void responseLogin(String[] messageArray) {
        if (messageArray.length >= 3 && messageArray[1] != null && messageArray[2] != null) {
            String userName = messageArray[1];
            String passWord = messageArray[2];

            synchronized (fmServer.userPool) {
                userInfo = (UserInfo) fmServer.userPool.get(userName);
            }

            if (userInfo == null) {
                writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.LOGIN, MSG_DETAIL_T.UNKNOWNUSER, messageArray);
            } else if (userInfo.userToken != null) {
                writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.LOGIN, MSG_DETAIL_T.USERALREADYLOGGEDIN, messageArray);
            } else if (!passWord.equals(userInfo.passWord)) {
                writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.LOGIN, MSG_DETAIL_T.INVALIDUSERPASSWORD, messageArray);
            } else {
                char[] token = new char[10];
                Random random = new Random(System.currentTimeMillis());

                for (int i = 0; i < 10; ++i) {
                    token[i] = (char) ('A' + random.nextInt(57));
                }

                userInfo.userToken = new String(token);
                userInfo.createGame = this;
                String[] response = {userInfo.userToken};
                writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.LOGIN, MSG_DETAIL_T.SUCCESS, response);
            }
        } else {
            writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.LOGIN, MSG_DETAIL_T.INVALIDMESSAGEFORMAT, messageArray);
        }
    }

    //reponse to STARTNEWGAME
    private void responseStartnewgame(String[] messageArray) {
        String userToken = messageArray[1];
        String[] response = new String[]{""};
        boolean tokenIsNull = !checkToken(userToken);

        //this.userInfo initialize when login
        if (!tokenIsNull && userInfo != null) {
            gameInstance = fmServer.generateGameInstance(userInfo);

            if (gameInstance != null) {
                response = new String[]{gameInstance.gameKey};
                writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.STARTNEWGAME, MSG_DETAIL_T.SUCCESS, response);
            }
        } else if (tokenIsNull) {
            writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.STARTNEWGAME, MSG_DETAIL_T.USERNOTLOGGEDIN, messageArray);
        } else {
            writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.STARTNEWGAME, MSG_DETAIL_T.FAILURE, response);
        }
    }

    //response to JOINGAME
    private void responseJoinGame(String[] messageArray) {
        String userToken = messageArray[1];
        String gameKey = messageArray[2];
        String[] nameScore = new String[2];
        boolean tokenIsNull = !checkToken(userToken);

        if (tokenIsNull) {
            writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.JOINGAME, MSG_DETAIL_T.USERNOTLOGGEDIN, messageArray);
            return;
            //leader create this.gameInstance when start new game, but participant's not initilized
        } else if (gameInstance != null) {
            String[] response = new String[]{""};
            writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.JOINGAME, MSG_DETAIL_T.FAILURE, response);
            return;
        } else {
            //initilized participant's gameinstance

            if (fmServer.instancePool != null && gameKey != null) {
                synchronized (fmServer.instancePool) {
                    gameInstance = (GameInstance) fmServer.instancePool.get(gameKey);
                }
            }

            if (gameInstance == null) {
                writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.JOINGAME, MSG_DETAIL_T.GAMEKEYNOTFOUND, messageArray);
                return;
            }

            //add participant
            synchronized (gameInstance.participants) {
                if (userInfo != null && gameInstance.participants != null) {
                    gameInstance.participants.add(userInfo);
                    gameInstance.allUsers.add(userInfo);
                }
            }

            nameScore[0] = userInfo.userName;
            nameScore[1] = String.valueOf(userInfo.cumulativeScore);

            CreateGame leaderCreateGame = gameInstance.leaderInfo.createGame;
            if (leaderCreateGame != null) {
                leaderCreateGame.writeResponse(MSG_TYPE.NEWPARTICIPANT, null, null, nameScore);
            }


            String[] response = {gameInstance.gameKey};
            writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.JOINGAME, MSG_DETAIL_T.SUCCESS, response);

            synchronized (gameInstance) {
                try {
                    gameInstance.wait();
                } catch (InterruptedException e) {
                    System.out.println("wait gameInstance error");
                }
            }

            String[] res = new String[]{""};
            writeResponse(MSG_TYPE.GAMEOVER, null, null, res);
            gameInstance = null;
        }
    }

    //response to ALLPARTICIPANTSHAVEJOINED
    private void responseAllHaveJoined(String[] messageArray) {
        String userToken = messageArray[1];
        String gameKey = messageArray[2];

        if (!checkToken(userToken)) {
            writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.ALLPARTICIPANTSHAVEJOINED, MSG_DETAIL_T.USERNOTLOGGEDIN, messageArray);
        } else if (!gameKey.equals(gameInstance.gameKey)) {
            writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.ALLPARTICIPANTSHAVEJOINED, MSG_DETAIL_T.INVALIDGAMETOKEN, messageArray);
        } else if (userInfo != gameInstance.leaderInfo) {
            writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.ALLPARTICIPANTSHAVEJOINED, MSG_DETAIL_T.USERNOTGAMELEADER, messageArray);
        } else {
            gameInstance.start();

            synchronized (gameInstance) {
                try {
                    gameInstance.wait();
                } catch (InterruptedException e) {
                    System.out.println("when wait gameInstance, it is interruped");
                }
            }

            String[] res = new String[]{""};
            writeResponse(MSG_TYPE.GAMEOVER, null, null, res);
            gameInstance = null;
        }
    }

    //response to LOGOUT
    private void responseLogout(String[] messageArray) {
        if (userInfo != null) {
            userInfo.userToken = null;
            userInfo.createGame = null;
            userInfo = null;
            gameInstance = null;
            writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.LOGOUT, MSG_DETAIL_T.SUCCESS, messageArray);
        } else {
            writeResponse(MSG_TYPE.RESPONSE, MSG_TYPE.LOGOUT, MSG_DETAIL_T.USERNOTLOGGEDIN, messageArray);
        }
    }

    //sent response to client
    public void writeResponse(MSG_TYPE msgType1, MSG_TYPE msgType2, MSG_DETAIL_T msgDetailT, String[] message) {
        if (out != null) {
            String response = new String("" + msgType1);
            if (msgType2 != null) {
                response += ("--" + msgType2);
            }

            if (msgDetailT != null) {
                response += ("--" + msgDetailT);
            }

            if (message != null) {
                String[] words = message;

                for (int i = 0; i < message.length; ++i) {
                    if (words[i] != null) {
                        response += ("--" + words[i]);
                    }
                }
            }

            synchronized (out) {
                this.out.println(response);
            }

            System.out.println("Response Message :" + response);
        }
    }

    //read request from client
    public String readRequest() {
        String request = "";

        if (in != null) {
            try {
                synchronized (in) {
                    request = in.readLine();
                }

                System.out.println("User Request :" + request);

            } catch (IOException e) {
                request = null;
            }

            if (request == null) {
                responseLogout(null);
            }
            return request;
        } else {
            return null;
        }
    }

    //check user token is same as server stored
    private boolean checkToken(String token) {
        if (token == null || userInfo.userToken == null) {
            return false;
        }
        return userInfo.userToken.equals(token);
    }
}
