import java.awt.*;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.*;
import java.io.IOException;
import java.util.HashMap;
/*
 * Created by JFormDesigner on Thu Nov 10 10:20:18 CST 2016
 */

/**
 * @author bruce wang
 */
    public class foilmakerGUI extends JFrame {
        private CardLayout layout;
        private foilmakerController controller;
        public static volatile boolean buttonNotClick;
        public static volatile boolean readGameover;
        private ButtonGroup radiobuttonGroup;
        private DefaultListModel listParticipants;
        private DefaultListModel listResults;
        private playerModel playermodel;
        private gameModel gamemodel;
        private HashMap<String, JRadioButton> dynamicButtons;


        public foilmakerGUI(playerModel player, gameModel game, foilmakerController controller) {
            initComponents();

            this.playermodel = player;
            this.gamemodel = game;
            this.controller = controller;

            buttonNotClick = true;
            readGameover = false;

            listParticipants = new DefaultListModel();
            listResults = new DefaultListModel();
            layout = new CardLayout();
            radiobuttonGroup = new ButtonGroup();
            dynamicButtons = new HashMap<>();

            mainPanel.setLayout(layout);
            mainPanel.add(loginPanel, "login");
            mainPanel.add(playgamePanel, "playgame");
            mainPanel.add(startnewgamePanel, "startnewgame");
            mainPanel.add(joingamePanel, "joingame");
            mainPanel.add(waitgamePanel, "waitgame");
            mainPanel.add(suggestionPanel, "suggestion");
            mainPanel.add(giveanswerPanel, "giveanswer");
            mainPanel.add(showresultPanel, "showresult");

            // Add main panel to GUI frame
            this.setTitle("FoilMaker");

            this.add(mainPanel);

            // Set dimensions
            this.setLocation(200, 200);

            this.setMinimumSize(new Dimension(200, 200));

            // Dispose frame when close button is pressed
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Show login panel initially */
            this.pack();
            layout.show(mainPanel, "login");
            this.setVisible(true);
        }

        // First type parameter is String because the b/g task sends a String (server response) in our case.
        // You may change it to a different type to suit your implementation. Don't worry about the second
        // parameter.
        SwingWorker readParticipantWorker = new SwingWorker<String, Object>() {
            @Override
            public String doInBackground() {
                // TODO : Receive response from the server
                while (buttonNotClick) {
                    //get participants repeatedly
                    try {
                        controller.readServer();
                    } catch (IOException e) {

                    }

                    listParticipants.removeAllElements();
                    for (int i = 0; i < playermodel.getGameParticipants().size(); i++) {
                        listParticipants.addElement(playermodel.getGameParticipants().get(i));
                    }

                    ParticipantsList.setModel(listParticipants);

                    if (!startnewgameButton.isEnabled()) {
                        //Recover start game button
                        startnewgameButton.setEnabled(true);
                        startnewgamePanel.repaint();
                    }
                }
                return "";
            }

            @Override
            public void done() {
            }
        };

        SwingWorker readQuestionWorker = new SwingWorker<String, Object>() {
            @Override
            public String doInBackground() {

                // TODO : Receive response from the server

                //Wait question when leader start game
                while (true) {
                    //get NEWGAMEWORD
                    try {
                        controller.readServer();
                    } catch (IOException e) {

                    }

                    if (!gamemodel.getGameQuestion().isEmpty()) {
                        break;
                    }
                }
                return "";
            }

            @Override
            public void done() {
                try {
                    // TODO : Update UI

                    //initial question
                    givewordField.setText(gamemodel.getGameQuestion());
                    inputsugesstionUser.setText(playermodel.getPlayerName());
                    layout.show(mainPanel, "suggestion");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };


//This method for log out button in every panel

        private void playerLogout(JTextField status) {
            try {
                boolean haveError = controller.sentServer("LOGOUT");
                if (haveError) {
                    status.setText(gamemodel.getGameStatus());
                } else {
                    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                }
            } catch (IOException e) {

            }
        }

        //This method for create radio button dynamic
        private void makeRadioButton(String label) {
            JRadioButton button = new JRadioButton(label);

            radiobuttonPanel.add(button);
            dynamicButtons.put(label, button);

            if (radiobuttonGroup.getButtonCount() == 0) {
                button.setSelected(true);
            }

            radiobuttonGroup.add(button);
            radiobuttonPanel.invalidate();

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    playermodel.setPlayerOption(button.getText());
                }
            });
        }

        //Login panel, Register button
        private void RegisterButtonActionPerformed(ActionEvent e) {
            // TODO add your code here
            try {
                boolean haveError = controller.sentServer("CREATENEWUSER");
                if (haveError) {
                    loginStatus.setText(gamemodel.getGameStatus());
                }
            } catch (IOException ee) {

            }
        }

        //Login panel, Login button
        private void LoginButtonActionPerformed(ActionEvent e) {
            // TODO add your code here
            playermodel.setPlayerName(username.getText());
            playermodel.setPlayerPassword(new String(passwordField.getPassword()));

            try {
                boolean haveError = controller.sentServer("LOGIN");
                if (haveError) {
                    loginStatus.setText(gamemodel.getGameStatus());
                } else {
                    playgameUser.setText(playermodel.getPlayerName());
                    layout.show(mainPanel, "playgame");
                }
            } catch (IOException ee) {

            }
        }

        //Play game panel, Start game button
        private void startgameButtonActionPerformed(ActionEvent e) {
            // TODO add your code here
            try {
                boolean haveError = controller.sentServer("STARTNEWGAME");
                if (haveError) {
                    playgameStatus.setText(gamemodel.getGameStatus());
                } else {
                    //Show start new game panel
                    startgameUser.setText(playermodel.getPlayerName());
                    gamekeyField.setText(gamemodel.getGameKey());
                    layout.show(mainPanel, "startnewgame");

                    readParticipantWorker.execute();
                }
            } catch (IOException ee) {

            }

        }

        //Start new game panel, Logout button
        private void startgameLogoutActionPerformed(ActionEvent e) {
            // TODO add your code here
            playerLogout(startgameStatus);
        }

        //Play game panel, Join button
        private void joinButtonActionPerformed(ActionEvent e) {
            // TODO add your code here

            joingameUser.setText(playermodel.getPlayerName());
            layout.show(mainPanel, "joingame");
        }

        //Play game panel, Logout button
        private void playgameLogoutActionPerformed(ActionEvent e) {
            // TODO add your code here
            playerLogout(playgameStatus);
        }

        //Start new game panel, Start new game button
        private void startnewgameButtonActionPerformed(ActionEvent e) {
            // TODO add your code here

            //stop receive participants
            buttonNotClick = false;
            readParticipantWorker.cancel(true);

            try {
                if (!readParticipantWorker.getState().equals("DONE")) {
                    Thread.sleep(10);
                }
            } catch (Exception ee) {

            }
            buttonNotClick = true;

            //send server ALLPARTICIPANTSHAVEJOINED message
            try {
                boolean haveError = controller.sentServer("ALLPARTICIPANTSHAVEJOINED");
                if (haveError) {
                    startgameStatus.setText(gamemodel.getGameStatus());
                } else {
                    givewordField.setText(gamemodel.getGameQuestion());
                    inputsugesstionUser.setText(playermodel.getPlayerName());
                    layout.show(mainPanel, "suggestion");
                }
            } catch (IOException ee) {

            }
        }

        //Join game panel, Join game button
        private void joingameButtonActionPerformed(ActionEvent e) {
            // TODO add your code here
            gamemodel.setGameKey(joingamekeyField.getText());

            try {
                boolean haveError = controller.sentServer("JOINGAME");
                if (haveError) {
                    joingameStatus.setText(gamemodel.getGameStatus());
                } else {

                    waitgameUser.setText(playermodel.getPlayerName());
                    layout.show(mainPanel, "waitgame");

                    readQuestionWorker.execute();
                }
            } catch (IOException ee) {

            }
        }

        //Wait game panel, Logout button
        private void waitgameLogoutActionPerformed(ActionEvent e) {
            // TODO add your code here
            playerLogout(waitgameStatus);
        }

        //Join game panel, Logout button
        private void joingameLogoutActionPerformed(ActionEvent e) {
            // TODO add your code here
            playerLogout(joingameStatus);
        }

        //Suggestion panel, Submit button
        private void submitButtonActionPerformed(ActionEvent e) {
            // TODO add your code here
            //stop receive participants
            readQuestionWorker.cancel(true);

            try {
                if (!readQuestionWorker.getState().equals("DONE")) {
                    Thread.sleep(10);
                }
            } catch (Exception ee) {

            }

            String suggestion = suggestionField.getText();

            //suggestion can't equal to answer

            if (suggestion.equals(gamemodel.getGameQuestion())) {
                suggestionField.setText("");
            } else {
                playermodel.setPlayerSuggestion(suggestion);

                try {
                    boolean haveError = controller.sentServer("PLAYERSUGGESTION");
                    if (haveError) {
                        inputsuggestionStatus.setText(gamemodel.getGameStatus());
                    } else {

                        for (int i = 0; i < gamemodel.getCandidateAnswer().size(); i++) {
                            String label = gamemodel.getCandidateAnswer().get(i);
                            makeRadioButton(label);
                        }

                        giveanswerUser.setText(playermodel.getPlayerName());
                        layout.show(mainPanel, "giveanswer");
                    }
                } catch (IOException ee) {

                }
            }
        }

        //Suggestion panel, Logout button
        private void suggestionLogoutActionPerformed(ActionEvent e) {
            // TODO add your code here
            playerLogout(startgameStatus);
        }

        //Give answer panel, Logout button
        private void giveanswerLogoutActionPerformed(ActionEvent e) {
            // TODO add your code here
            playerLogout(giveanswerStatus);
        }

        //Give answer panel, Submit button
        private void submitoptionButtonActionPerformed(ActionEvent e) {

            //server send the result to each client, NO response?
            try {
                boolean haveError = controller.sentServer("PLAYERCHOICE");
                if (haveError) {
                    giveanswerUser.setText(gamemodel.getGameStatus());
                } else {

                    showresultUser.setText(playermodel.getPlayerName());
                    roundresultField.setText(playermodel.getRoundResult());

                    for (int i = 0; i < gamemodel.getGameOverallresults().size(); i++) {
                        listResults.addElement(gamemodel.getGameOverallresults().get(i));
                    }
                    OverallresultsList.setModel(listResults);

                    SwingWorker readGameoverWorker = new SwingWorker<String, Object>() {

                        @Override
                        public String doInBackground() {

                            // TODO : Receive response from the server
                            while (true) {
                                //get NEWGAMEWORD or GAMEOVER
                                try {
                                    controller.readServer();
                                } catch (IOException e) {

                                }
                                if (!gamemodel.getGameQuestion().isEmpty() || gamemodel.isGameOver()) {
                                    break;
                                }
                            }
                            return "";
                        }

                        @Override
                        public void done() {
                            // TODO : Update UI

                            if (gamemodel.isGameOver()) {
                                nextroundButton.setEnabled(false);
                                showResultStatus.setText("Game over!");
                            }

                            layout.show(mainPanel, "showresult");
                        }
                    };

                    //read GAMEOVER or NEWGAMEWORD
                    readGameoverWorker.execute();
                }
            } catch (IOException ee) {

            }
        }

        //Show result panel, Logout panel
        private void showresultLogoutActionPerformed(ActionEvent e) {
            // TODO add your code here
            playerLogout(startgameStatus);
        }

        //Show result panel, Next round button
        private void nextroundButtonActionPerformed(ActionEvent e) {
            // TODO add your code here
            //delete dynamic button
            for (HashMap.Entry<String, JRadioButton> entry : dynamicButtons.entrySet()) {
                radiobuttonPanel.remove(entry.getValue());
            }
            radiobuttonPanel.repaint();

            playermodel.setPlayerSuggestion("");
            playermodel.setRoundResult("");

            gamemodel.getCandidateAnswer().clear();
            gamemodel.getGameOverallresults().clear();

            givewordField.setText(gamemodel.getGameQuestion());
            inputsugesstionUser.setText(playermodel.getPlayerName());
            suggestionField.setText("");
            layout.show(mainPanel, "suggestion");
            suggestionPanel.repaint();
        }


        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - bruce wang
            loginPanel = new JPanel();
            usernameLabel = new JLabel();
            passwordLabel = new JLabel();
            username = new JTextField();
            passwordField = new JPasswordField();
            loginStatus = new JTextField();
            LoginButton = new JButton();
            RegisterButton = new JButton();
            loginUser = new JLabel();
            playgamePanel = new JPanel();
            playgameStatus = new JTextField();
            startgameButton = new JButton();
            joinButton = new JButton();
            playgameLogout = new JButton();
            playgameUser = new JLabel();
            startnewgamePanel = new JPanel();
            startgameStatus = new JTextField();
            startnewgameButton = new JButton();
            startgameLogout = new JButton();
            gamekeyLabel = new JLabel();
            participantsPanel = new JPanel();
            ParticipantsList = new JList();
            startgameUser = new JLabel();
            gamekeyField = new JLabel();
            joingamePanel = new JPanel();
            joingameStatus = new JTextField();
            joingameButton = new JButton();
            joingameLogout = new JButton();
            joingamekeyLabel = new JLabel();
            joingamekeyField = new JTextField();
            joingameUser = new JLabel();
            waitgamePanel = new JPanel();
            waitgameStatus = new JTextField();
            waitgameLogout = new JButton();
            waitgameLabel = new JLabel();
            waitgameUser = new JLabel();
            suggestionPanel = new JPanel();
            inputsuggestionStatus = new JTextField();
            suggestionLogout = new JButton();
            wordLabel = new JLabel();
            submitButton = new JButton();
            givewordField = new JTextField();
            inputsuggestionPanel = new JPanel();
            suggestionField = new JTextField();
            inputsugesstionUser = new JLabel();
            giveanswerPanel = new JPanel();
            giveanswerUser = new JTextField();
            giveanswerStatus = new JTextField();
            giveanswerLogout = new JButton();
            pickoptionLabel = new JLabel();
            submitoptionButton = new JButton();
            radiobuttonPanel = new JPanel();
            showresultPanel = new JPanel();
            showResultStatus = new JTextField();
            showResultLogout = new JButton();
            nextroundButton = new JButton();
            roundresultPanel = new JPanel();
            roundresultField = new JTextField();
            overallresultsscrollPane = new JScrollPane();
            OverallresultsList = new JList();
            showresultUser = new JLabel();
            mainPanel = new JPanel();

            //======== loginPanel ========
            {
                loginPanel.setBorder(new TitledBorder(""));


                //---- usernameLabel ----
                usernameLabel.setText("Username");
                usernameLabel.setLabelFor(username);

                //---- passwordLabel ----
                passwordLabel.setText("Password");
                passwordLabel.setLabelFor(passwordField);

                //---- LoginButton ----
                LoginButton.setText("Login");
                LoginButton.addActionListener(e -> LoginButtonActionPerformed(e));

                //---- RegisterButton ----
                RegisterButton.setText("Register");
                RegisterButton.addActionListener(e -> RegisterButtonActionPerformed(e));

                //---- loginUser ----
                loginUser.setText("FoilMaker!");
                loginUser.setHorizontalAlignment(SwingConstants.CENTER);

                GroupLayout loginPanelLayout = new GroupLayout(loginPanel);
                loginPanel.setLayout(loginPanelLayout);
                loginPanelLayout.setHorizontalGroup(
                        loginPanelLayout.createParallelGroup()
                                .addComponent(loginStatus, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(loginPanelLayout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(LoginButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                                        .addComponent(RegisterButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                        .addGap(80, 80, 80))
                                .addGroup(loginPanelLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addGroup(loginPanelLayout.createParallelGroup()
                                                .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                                        .addGroup(loginPanelLayout.createParallelGroup()
                                                .addComponent(passwordField, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(username, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42))
                                .addGroup(loginPanelLayout.createSequentialGroup()
                                        .addComponent(loginUser, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                                        .addContainerGap())
                );
                loginPanelLayout.setVerticalGroup(
                        loginPanelLayout.createParallelGroup()
                                .addGroup(loginPanelLayout.createSequentialGroup()
                                        .addComponent(loginUser, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addGap(64, 64, 64)
                                        .addGroup(loginPanelLayout.createParallelGroup()
                                                .addComponent(usernameLabel)
                                                .addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGap(19, 19, 19)
                                        .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(passwordLabel)
                                                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(LoginButton)
                                                .addComponent(RegisterButton))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(loginStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                );
            }

            //======== playgamePanel ========
            {
                playgamePanel.setBorder(new TitledBorder(""));

                //---- playgameStatus ----
                playgameStatus.setText("Welcome!");

                //---- startgameButton ----
                startgameButton.setText("Start New Game");
                startgameButton.addActionListener(e -> startgameButtonActionPerformed(e));

                //---- joinButton ----
                joinButton.setText("Join a Game");
                joinButton.addActionListener(e -> joinButtonActionPerformed(e));

                //---- playgameLogout ----
                playgameLogout.setText("Logout");
                playgameLogout.addActionListener(e -> playgameLogoutActionPerformed(e));

                //---- playgameUser ----
                playgameUser.setText("text");
                playgameUser.setHorizontalAlignment(SwingConstants.CENTER);

                GroupLayout playgamePanelLayout = new GroupLayout(playgamePanel);
                playgamePanel.setLayout(playgamePanelLayout);
                playgamePanelLayout.setHorizontalGroup(
                        playgamePanelLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, playgamePanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(playgamePanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(playgameUser, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                                                .addGroup(playgamePanelLayout.createSequentialGroup()
                                                        .addComponent(startgameButton)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(joinButton)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(playgameLogout)
                                                        .addGap(0, 55, Short.MAX_VALUE))
                                                .addComponent(playgameStatus, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
                                        .addContainerGap())
                );
                playgamePanelLayout.setVerticalGroup(
                        playgamePanelLayout.createParallelGroup()
                                .addGroup(playgamePanelLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(playgameUser)
                                        .addGap(60, 60, 60)
                                        .addGroup(playgamePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(startgameButton)
                                                .addComponent(joinButton)
                                                .addComponent(playgameLogout))
                                        .addGap(78, 78, 78)
                                        .addComponent(playgameStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(31, Short.MAX_VALUE))
                );
                playgamePanelLayout.linkSize(SwingConstants.VERTICAL, new Component[]{joinButton, playgameLogout, startgameButton});
            }

            //======== startnewgamePanel ========
            {
                startnewgamePanel.setBorder(new TitledBorder(""));

                //---- startgameStatus ----
                startgameStatus.setText("Game Started: You are the leader");

                //---- startnewgameButton ----
                startnewgameButton.setText("Start Game");
                startnewgameButton.setEnabled(false);
                startnewgameButton.addActionListener(e -> startnewgameButtonActionPerformed(e));

                //---- startgameLogout ----
                startgameLogout.setText("Logout");
                startgameLogout.addActionListener(e -> startgameLogoutActionPerformed(e));

                //---- gamekeyLabel ----
                gamekeyLabel.setText("Others should use this key to join your game");
                gamekeyLabel.setHorizontalAlignment(SwingConstants.CENTER);

                //======== participantsPanel ========
                {
                    participantsPanel.setBorder(new TitledBorder("Participants"));

                    //---- ParticipantsList ----
                    ParticipantsList.setBackground(Color.orange);

                    GroupLayout participantsPanelLayout = new GroupLayout(participantsPanel);
                    participantsPanel.setLayout(participantsPanelLayout);
                    participantsPanelLayout.setHorizontalGroup(
                            participantsPanelLayout.createParallelGroup()
                                    .addGroup(participantsPanelLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(ParticipantsList, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                            .addContainerGap())
                    );
                    participantsPanelLayout.setVerticalGroup(
                            participantsPanelLayout.createParallelGroup()
                                    .addGroup(participantsPanelLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(ParticipantsList, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }

                //---- startgameUser ----
                startgameUser.setText("text");
                startgameUser.setHorizontalAlignment(SwingConstants.CENTER);

                //---- gamekeyField ----
                gamekeyField.setText("text");
                gamekeyField.setHorizontalAlignment(SwingConstants.CENTER);

                GroupLayout startnewgamePanelLayout = new GroupLayout(startnewgamePanel);
                startnewgamePanel.setLayout(startnewgamePanelLayout);
                startnewgamePanelLayout.setHorizontalGroup(
                        startnewgamePanelLayout.createParallelGroup()
                                .addGroup(startnewgamePanelLayout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addGroup(startnewgamePanelLayout.createParallelGroup()
                                                .addComponent(gamekeyLabel, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(startnewgamePanelLayout.createSequentialGroup()
                                                        .addComponent(startnewgameButton)
                                                        .addGap(55, 55, 55)
                                                        .addComponent(startgameLogout, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(startnewgamePanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(startgameStatus, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE))
                                .addGroup(startnewgamePanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(startgameUser, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE))
                                .addGroup(startnewgamePanelLayout.createSequentialGroup()
                                        .addGap(164, 164, 164)
                                        .addComponent(gamekeyField, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
                                .addGroup(startnewgamePanelLayout.createSequentialGroup()
                                        .addGap(105, 105, 105)
                                        .addComponent(participantsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                );
                startnewgamePanelLayout.setVerticalGroup(
                        startnewgamePanelLayout.createParallelGroup()
                                .addGroup(startnewgamePanelLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(startgameUser)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(gamekeyLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(gamekeyField)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(participantsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(startnewgamePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(startnewgameButton)
                                                .addComponent(startgameLogout))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(startgameStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                );
            }

            //======== joingamePanel ========
            {
                joingamePanel.setBorder(new TitledBorder(""));

                //---- joingameStatus ----
                joingameStatus.setText("Welcome!");

                //---- joingameButton ----
                joingameButton.setText("Join Game");
                joingameButton.addActionListener(e -> joingameButtonActionPerformed(e));

                //---- joingameLogout ----
                joingameLogout.setText("Logout");
                joingameLogout.addActionListener(e -> joingameLogoutActionPerformed(e));

                //---- joingamekeyLabel ----
                joingamekeyLabel.setText("enter the game key to join a game");
                joingamekeyLabel.setLabelFor(joingamekeyField);
                joingamekeyLabel.setHorizontalAlignment(SwingConstants.CENTER);

                //---- joingamekeyField ----
                joingamekeyField.setHorizontalAlignment(SwingConstants.CENTER);

                //---- joingameUser ----
                joingameUser.setText("text");
                joingameUser.setHorizontalAlignment(SwingConstants.CENTER);

                GroupLayout joingamePanelLayout = new GroupLayout(joingamePanel);
                joingamePanel.setLayout(joingamePanelLayout);
                joingamePanelLayout.setHorizontalGroup(
                        joingamePanelLayout.createParallelGroup()
                                .addGroup(joingamePanelLayout.createSequentialGroup()
                                        .addGroup(joingamePanelLayout.createParallelGroup()
                                                .addGroup(joingamePanelLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(joingameStatus, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(joingamePanelLayout.createSequentialGroup()
                                                        .addGap(161, 161, 161)
                                                        .addComponent(joingamekeyField, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(joingamePanelLayout.createSequentialGroup()
                                                        .addGap(56, 56, 56)
                                                        .addGroup(joingamePanelLayout.createParallelGroup()
                                                                .addComponent(joingamekeyLabel, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(joingamePanelLayout.createSequentialGroup()
                                                                        .addComponent(joingameButton)
                                                                        .addGap(48, 48, 48)
                                                                        .addComponent(joingameLogout, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(joingamePanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(joingameUser, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                joingamePanelLayout.setVerticalGroup(
                        joingamePanelLayout.createParallelGroup()
                                .addGroup(joingamePanelLayout.createSequentialGroup()
                                        .addContainerGap(21, Short.MAX_VALUE)
                                        .addComponent(joingameUser)
                                        .addGap(18, 18, 18)
                                        .addComponent(joingamekeyLabel)
                                        .addGap(26, 26, 26)
                                        .addComponent(joingamekeyField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(joingamePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(joingameButton)
                                                .addComponent(joingameLogout))
                                        .addGap(52, 52, 52)
                                        .addComponent(joingameStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                );
            }

            //======== waitgamePanel ========
            {
                waitgamePanel.setBorder(new TitledBorder(""));

                //---- waitgameStatus ----
                waitgameStatus.setText("Joined game: waiting for leader");

                //---- waitgameLogout ----
                waitgameLogout.setText("Logout");
                waitgameLogout.addActionListener(e -> waitgameLogoutActionPerformed(e));

                //---- waitgameLabel ----
                waitgameLabel.setText("Waiting for leader........");
                waitgameLabel.setHorizontalAlignment(SwingConstants.CENTER);

                //---- waitgameUser ----
                waitgameUser.setText("text");
                waitgameUser.setHorizontalAlignment(SwingConstants.CENTER);

                GroupLayout waitgamePanelLayout = new GroupLayout(waitgamePanel);
                waitgamePanel.setLayout(waitgamePanelLayout);
                waitgamePanelLayout.setHorizontalGroup(
                        waitgamePanelLayout.createParallelGroup()
                                .addGroup(waitgamePanelLayout.createSequentialGroup()
                                        .addGroup(waitgamePanelLayout.createParallelGroup()
                                                .addGroup(waitgamePanelLayout.createSequentialGroup()
                                                        .addGap(56, 56, 56)
                                                        .addComponent(waitgameLabel, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(waitgamePanelLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(waitgameStatus, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(waitgamePanelLayout.createSequentialGroup()
                                                        .addGap(162, 162, 162)
                                                        .addComponent(waitgameLogout, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(waitgamePanelLayout.createSequentialGroup()
                                                        .addGap(21, 21, 21)
                                                        .addComponent(waitgameUser, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                waitgamePanelLayout.setVerticalGroup(
                        waitgamePanelLayout.createParallelGroup()
                                .addGroup(waitgamePanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(waitgameUser)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                        .addComponent(waitgameLabel)
                                        .addGap(68, 68, 68)
                                        .addComponent(waitgameLogout)
                                        .addGap(59, 59, 59)
                                        .addComponent(waitgameStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                );
            }

            //======== suggestionPanel ========
            {
                suggestionPanel.setBorder(new TitledBorder(""));

                //---- inputsuggestionStatus ----
                inputsuggestionStatus.setText("Enter  your suggestion");

                //---- suggestionLogout ----
                suggestionLogout.setText("Logout");
                suggestionLogout.addActionListener(e -> suggestionLogoutActionPerformed(e));

                //---- wordLabel ----
                wordLabel.setText("What is the word for");

                //---- submitButton ----
                submitButton.setText("Submit Suggestion");
                submitButton.addActionListener(e -> submitButtonActionPerformed(e));

                //---- givewordField ----
                givewordField.setBackground(Color.orange);

                //======== inputsuggestionPanel ========
                {
                    inputsuggestionPanel.setBorder(new TitledBorder("Your Suggestion"));

                    GroupLayout inputsuggestionPanelLayout = new GroupLayout(inputsuggestionPanel);
                    inputsuggestionPanel.setLayout(inputsuggestionPanelLayout);
                    inputsuggestionPanelLayout.setHorizontalGroup(
                            inputsuggestionPanelLayout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, inputsuggestionPanelLayout.createSequentialGroup()
                                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(suggestionField, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap())
                    );
                    inputsuggestionPanelLayout.setVerticalGroup(
                            inputsuggestionPanelLayout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, inputsuggestionPanelLayout.createSequentialGroup()
                                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(suggestionField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap())
                    );
                }

                //---- inputsugesstionUser ----
                inputsugesstionUser.setText("text");
                inputsugesstionUser.setHorizontalAlignment(SwingConstants.CENTER);

                GroupLayout suggestionPanelLayout = new GroupLayout(suggestionPanel);
                suggestionPanel.setLayout(suggestionPanelLayout);
                suggestionPanelLayout.setHorizontalGroup(
                        suggestionPanelLayout.createParallelGroup()
                                .addGroup(suggestionPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(inputsuggestionStatus, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.TRAILING, suggestionPanelLayout.createSequentialGroup()
                                        .addGroup(suggestionPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addGroup(suggestionPanelLayout.createSequentialGroup()
                                                        .addGap(65, 65, 65)
                                                        .addGroup(suggestionPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(givewordField, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                                                .addGroup(GroupLayout.Alignment.LEADING, suggestionPanelLayout.createSequentialGroup()
                                                                        .addComponent(submitButton)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(suggestionLogout, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(inputsuggestionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGroup(GroupLayout.Alignment.LEADING, suggestionPanelLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addGroup(suggestionPanelLayout.createParallelGroup()
                                                                .addGroup(suggestionPanelLayout.createSequentialGroup()
                                                                        .addGap(6, 6, 6)
                                                                        .addComponent(wordLabel, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                                .addComponent(inputsugesstionUser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGap(71, 71, 71))
                );
                suggestionPanelLayout.setVerticalGroup(
                        suggestionPanelLayout.createParallelGroup()
                                .addGroup(suggestionPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(inputsugesstionUser)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(wordLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(givewordField, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(inputsuggestionPanel, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addGroup(suggestionPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(suggestionLogout)
                                                .addComponent(submitButton))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(inputsuggestionStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                );
            }

            //======== giveanswerPanel ========
            {
                giveanswerPanel.setBorder(new TitledBorder(""));

                //---- giveanswerUser ----
                giveanswerUser.setHorizontalAlignment(SwingConstants.CENTER);

                //---- giveanswerStatus ----
                giveanswerStatus.setText("Pick your choice");

                //---- giveanswerLogout ----
                giveanswerLogout.setText("Logout");
                giveanswerLogout.addActionListener(e -> giveanswerLogoutActionPerformed(e));

                //---- pickoptionLabel ----
                pickoptionLabel.setText("Pick your option below");
                pickoptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

                //---- submitoptionButton ----
                submitoptionButton.setText("Submit Option");
                submitoptionButton.addActionListener(e -> submitoptionButtonActionPerformed(e));

                //======== radiobuttonPanel ========
                {
                    radiobuttonPanel.setLayout(new BoxLayout(radiobuttonPanel, BoxLayout.Y_AXIS));
                }

                GroupLayout giveanswerPanelLayout = new GroupLayout(giveanswerPanel);
                giveanswerPanel.setLayout(giveanswerPanelLayout);
                giveanswerPanelLayout.setHorizontalGroup(
                        giveanswerPanelLayout.createParallelGroup()
                                .addGroup(giveanswerPanelLayout.createSequentialGroup()
                                        .addGroup(giveanswerPanelLayout.createParallelGroup()
                                                .addComponent(giveanswerUser, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(giveanswerStatus, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.TRAILING, giveanswerPanelLayout.createSequentialGroup()
                                        .addContainerGap(8, Short.MAX_VALUE)
                                        .addGroup(giveanswerPanelLayout.createParallelGroup()
                                                .addComponent(pickoptionLabel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
                                                .addGroup(GroupLayout.Alignment.TRAILING, giveanswerPanelLayout.createSequentialGroup()
                                                        .addComponent(submitoptionButton)
                                                        .addGap(92, 92, 92)
                                                        .addComponent(giveanswerLogout, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(72, 72, 72))
                                                .addGroup(GroupLayout.Alignment.TRAILING, giveanswerPanelLayout.createSequentialGroup()
                                                        .addComponent(radiobuttonPanel, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(153, 153, 153)))
                                        .addContainerGap())
                );
                giveanswerPanelLayout.setVerticalGroup(
                        giveanswerPanelLayout.createParallelGroup()
                                .addGroup(giveanswerPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(giveanswerUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pickoptionLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(radiobuttonPanel, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(giveanswerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(giveanswerLogout)
                                                .addComponent(submitoptionButton))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(giveanswerStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                );
            }

            //======== showresultPanel ========
            {
                showresultPanel.setBorder(new TitledBorder(""));

                //---- showResultStatus ----
                showResultStatus.setText("Click <Next Round> when ready");

                //---- showResultLogout ----
                showResultLogout.setText("Logout");
                showResultLogout.addActionListener(e -> showresultLogoutActionPerformed(e));

                //---- nextroundButton ----
                nextroundButton.setText("Next Round");
                nextroundButton.addActionListener(e -> nextroundButtonActionPerformed(e));

                //======== roundresultPanel ========
                {
                    roundresultPanel.setBackground(Color.white);
                    roundresultPanel.setBorder(new TitledBorder("Round Result"));

                    //---- roundresultField ----
                    roundresultField.setBackground(Color.orange);

                    GroupLayout roundresultPanelLayout = new GroupLayout(roundresultPanel);
                    roundresultPanel.setLayout(roundresultPanelLayout);
                    roundresultPanelLayout.setHorizontalGroup(
                            roundresultPanelLayout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, roundresultPanelLayout.createSequentialGroup()
                                            .addComponent(roundresultField, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                                            .addContainerGap())
                    );
                    roundresultPanelLayout.setVerticalGroup(
                            roundresultPanelLayout.createParallelGroup()
                                    .addComponent(roundresultField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    );
                }

                //======== overallresultsscrollPane ========
                {
                    overallresultsscrollPane.setViewportBorder(new TitledBorder("Overall Results"));

                    //---- OverallresultsList ----
                    OverallresultsList.setBackground(Color.yellow);
                    overallresultsscrollPane.setViewportView(OverallresultsList);
                }

                //---- showresultUser ----
                showresultUser.setText("text");
                showresultUser.setHorizontalAlignment(SwingConstants.CENTER);

                GroupLayout showresultPanelLayout = new GroupLayout(showresultPanel);
                showresultPanel.setLayout(showresultPanelLayout);
                showresultPanelLayout.setHorizontalGroup(
                        showresultPanelLayout.createParallelGroup()
                                .addGroup(showresultPanelLayout.createSequentialGroup()
                                        .addGroup(showresultPanelLayout.createParallelGroup()
                                                .addGroup(showresultPanelLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(showResultStatus, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(showresultPanelLayout.createSequentialGroup()
                                                        .addGap(78, 78, 78)
                                                        .addComponent(nextroundButton)
                                                        .addGap(67, 67, 67)
                                                        .addComponent(showResultLogout, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.TRAILING, showresultPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(showresultPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(showresultUser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(showresultPanelLayout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addGroup(showresultPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(roundresultPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(overallresultsscrollPane, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))))
                                        .addGap(27, 27, 27))
                );
                showresultPanelLayout.setVerticalGroup(
                        showresultPanelLayout.createParallelGroup()
                                .addGroup(showresultPanelLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(showresultUser)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(roundresultPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(overallresultsscrollPane, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(showresultPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(showResultLogout)
                                                .addComponent(nextroundButton))
                                        .addGap(18, 18, 18)
                                        .addComponent(showResultStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                );
            }

            //======== mainPanel ========
            {

                mainPanel.setLayout(new CardLayout());
            }
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - bruce wang
        private JPanel loginPanel;
        private JLabel usernameLabel;
        private JLabel passwordLabel;
        private JTextField username;
        private JPasswordField passwordField;
        private JTextField loginStatus;
        private JButton LoginButton;
        private JButton RegisterButton;
        private JLabel loginUser;
        private JPanel playgamePanel;
        private JTextField playgameStatus;
        private JButton startgameButton;
        private JButton joinButton;
        private JButton playgameLogout;
        private JLabel playgameUser;
        private JPanel startnewgamePanel;
        private JTextField startgameStatus;
        private JButton startnewgameButton;
        private JButton startgameLogout;
        private JLabel gamekeyLabel;
        private JPanel participantsPanel;
        private JList ParticipantsList;
        private JLabel startgameUser;
        private JLabel gamekeyField;
        private JPanel joingamePanel;
        private JTextField joingameStatus;
        private JButton joingameButton;
        private JButton joingameLogout;
        private JLabel joingamekeyLabel;
        private JTextField joingamekeyField;
        private JLabel joingameUser;
        private JPanel waitgamePanel;
        private JTextField waitgameStatus;
        private JButton waitgameLogout;
        private JLabel waitgameLabel;
        private JLabel waitgameUser;
        private JPanel suggestionPanel;
        private JTextField inputsuggestionStatus;
        private JButton suggestionLogout;
        private JLabel wordLabel;
        private JButton submitButton;
        private JTextField givewordField;
        private JPanel inputsuggestionPanel;
        private JTextField suggestionField;
        private JLabel inputsugesstionUser;
        private JPanel giveanswerPanel;
        private JTextField giveanswerUser;
        private JTextField giveanswerStatus;
        private JButton giveanswerLogout;
        private JLabel pickoptionLabel;
        private JButton submitoptionButton;
        private JPanel radiobuttonPanel;
        private JPanel showresultPanel;
        private JTextField showResultStatus;
        private JButton showResultLogout;
        private JButton nextroundButton;
        private JPanel roundresultPanel;
        private JTextField roundresultField;
        private JScrollPane overallresultsscrollPane;
        private JList OverallresultsList;
        private JLabel showresultUser;
        private JPanel mainPanel;

        // JFormDesigner - End of variables declaration  //GEN-END:variables
        public static void main(String args[]) {
            playerModel player = new playerModel();
            gameModel game = new gameModel();
            foilmakerController controller = new foilmakerController(player, game);
            new foilmakerGUI(player, game, controller);
        }

    }
