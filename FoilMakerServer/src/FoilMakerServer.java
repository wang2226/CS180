/**
 * Created by Bruce on 11/11/2016.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class FoilMakerServer {
    private int port;
    private int counter;
    private String userFile;
    private String deckFile;
    protected HashMap userPool = null;
    protected HashMap instancePool = null;
    private HashMap socketPool = null;
    private ServerSocket listener = null;

    FoilMakerServer() {
        socketPool = new HashMap();
        userPool = new HashMap();
        instancePool = new HashMap();
        userFile = "UserDatabase";
        deckFile = "WordleDeck";
        counter = 0;
    }


    /*
    1:create socket, listen on port
    2:get request from port
    3:initialize game instance
    4:close all socket
    */
    private void initializeServer() {
        CreateGame game = null;

        try {
            listener = new ServerSocket(port);
            listener.setReuseAddress(true);
            System.out.println("Server listening :" + this.port);

            try {
                BufferedReader in = null;

                try {
                    File file = new File(userFile);
                    FileReader fileReader = new FileReader(file);
                    in = new BufferedReader(fileReader);

                    String user = in.readLine();
                    UserInfo userInfo = null;

                    do {
                        if (user != null)
                            userInfo = UserInfo.generateUserInfo(user);

                        if (userInfo != null) {
                            synchronized (userPool) {
                                userPool.put(userInfo.userName, userInfo);
                            }
                        }
                        user = in.readLine();
                    } while (user != null);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                } finally {
                    if (in != null) {
                        in.close();
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }

            while (true) {
                Socket listenerSocket = listener.accept();
                synchronized (this) {
                    socketPool.put(Integer.valueOf(++counter), listenerSocket);

                    try {
                        game = new CreateGame(this, listenerSocket);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    if (game != null) {
                        game.start();
                    }
                }
            }
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            synchronized (this) {
                if (listener != null) {
                    try {
                        listener.close();
                    } catch (IOException e) {

                    }
                }

                Iterator itSocket = socketPool.values().iterator();

                while (true) {
                    if (itSocket.hasNext()) {
                        Socket someSocket = (Socket) itSocket.next();
                        if (someSocket != null && !someSocket.isClosed()) {
                            try {
                                someSocket.close();
                            } catch (IOException e) {
                            }
                        }
                    }
                    break;
                }
            }
        }

    }

    /*add user to user pool,implement a persistence mechanism
    based on  a simple file-based user store where user information is
    written to a file*/
    public boolean addUserInfo(String username, UserInfo userinfo) {
        if (userPool.containsKey(username)) {
            return false;
        } else {
            synchronized (userPool) {
                userPool.put(username, userinfo);
            }

            try {
                File userfile = new File(userFile);
                FileOutputStream outputStream = null;
                PrintWriter out = null;

                try {
                    outputStream = new FileOutputStream(userfile);
                    out = new PrintWriter(outputStream);
                    synchronized (this) {
                        if (this.userPool != null) {
                            Iterator itUserInfo = userPool.values().iterator();

                            while (itUserInfo.hasNext()) {
                                UserInfo user = (UserInfo) itUserInfo.next();
                                out.println(user.userName + UserInfo.SEPARATOR + user.passWord + UserInfo.SEPARATOR + user.cumulativeScore + UserInfo.SEPARATOR + user.fooledTheOthers + UserInfo.SEPARATOR + user.fooledByOthers);
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        out.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
    }

    //make a GameInstance object and return it
    public GameInstance generateGameInstance(UserInfo userinfo) {
        GameInstance gameinstance;

        synchronized (instancePool) {
            char[] gameKey;

            do {
                Random key = new Random(System.currentTimeMillis());
                gameKey = new char[3];

                for (int i = 0; i < 3; ++i) {
                    gameKey[i] = (char) ('a' + key.nextInt(25));
                }
            } while (instancePool.containsKey(gameKey));

            gameinstance = new GameInstance(userinfo, new String(gameKey), deckFile);
            instancePool.put(new String(gameKey), gameinstance);
        }
        return gameinstance;
    }

    /*
   1.new a FoilMakerServer object
   2.read user from file and add to userpool
   3.initialize server
    */
    public static void main(String[] args) {
        FoilMakerServer fmServer = new FoilMakerServer();

        try {
            if (args.length == 0) {
                System.out.println("Use default port: 50000");
                fmServer.port = 50000;
            } else if (args.length == 1) {
                fmServer.port = Integer.parseInt(args[0]);
            } else {
                System.out.println("Usage: java -jar FoilMakerServer.jar <port number>");
                return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        fmServer.initializeServer();
    }
}

