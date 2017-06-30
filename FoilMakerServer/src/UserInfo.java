/**
 * Created by Bruce on 11/11/2016.
 */
public class UserInfo {
    public static String SEPARATOR = ":";
    int cumulativeScore = 0;
    int fooledTheOthers = 0;
    int fooledByOthers = 0;
    String userName = null;
    String passWord = null;
    String userToken;
    CreateGame createGame;

    public UserInfo(String username, String password) {
        this.userToken = null;
        this.userName = username;
        this.passWord = password;
        this.createGame = null;
    }

    //initialize user information
    public static UserInfo generateUserInfo(String userInfoString) {
        if (userInfoString == null) {
            return null;
        } else {
            String[] userInfoArray;
            if ((userInfoArray = userInfoString.split(SEPARATOR)).length < 5) {
                return null;
            } else {
                UserInfo userInfo = new UserInfo(userInfoArray[0], userInfoArray[1]);

                int score;
                int fool;
                int fooled;
                try {
                    score = Integer.parseInt(userInfoArray[2]);
                    fool = Integer.parseInt(userInfoArray[3]);
                    fooled = Integer.parseInt(userInfoArray[4]);
                } catch (NumberFormatException e) {
                    score = 0;
                    fool = 0;
                    fooled = 0;
                }

                userInfo.cumulativeScore = score;
                userInfo.fooledTheOthers = fool;
                userInfo.fooledByOthers = fooled;
                return userInfo;
            }
        }
    }
}
