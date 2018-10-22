import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Game extends JFrame{
    private JPanel panelContainer;
    private CardLayout cl;
    private JTextField inputField;
    private JTextArea previousResults, gameInstructions, historyResults,hs;
    private int[] secretNumber;
    private int countTimes;
    File hsfile= new File("src/Highscore.txt");
    private Border brownBorder = BorderFactory.createLineBorder(new Color(128, 82, 7), 4);
    private Image bg = getToolkit().getImage(Game.class.getResource("Background.jpg"));
    private Image icon = getToolkit().getImage(Game.class.getResource("gameIcon.png"));
    private BackgroundPanel homePanel = new BackgroundPanel(bg, BackgroundPanel.SCALED, 0.0f, 0.0f);
    private BackgroundPanel gamePanel = new BackgroundPanel(bg, BackgroundPanel.SCALED, 0.0f, 0.0f);
    private BackgroundPanel instructionsPanel = new BackgroundPanel(bg, BackgroundPanel.SCALED, 0.0f, 0.0f);
    private BackgroundPanel historyPanel = new BackgroundPanel(bg, BackgroundPanel.SCALED, 0.0f, 0.0f);
    private BackgroundPanel highScorePanel = new BackgroundPanel(bg, BackgroundPanel.SCALED, 0.0f, 0.0f);

    private JLabel buttonNewGame = new JLabel(new ImageIcon(Game.class.getResource("buttonNewGame.png")));
    private JLabel buttonInstructions = new JLabel(new ImageIcon(Game.class.getResource("buttonInstructions.png")));
    private JLabel buttonHighScore = new JLabel(new ImageIcon(Game.class.getResource("buttonHighScore.png")));
    private JLabel buttonHistory = new JLabel(new ImageIcon(Game.class.getResource("buttonHistory.png")));
    private JLabel buttonExit = new JLabel(new ImageIcon(Game.class.getResource("buttonExit.png")));
    private JLabel buttonBack_1 = new JLabel(new ImageIcon(Game.class.getResource("buttonBack.png")));
    private JLabel buttonBack_2 = new JLabel(new ImageIcon(Game.class.getResource("buttonBack.png")));
    private JLabel buttonBack_3 = new JLabel(new ImageIcon(Game.class.getResource("buttonBack.png")));
    private JLabel buttonBack_4 = new JLabel(new ImageIcon(Game.class.getResource("buttonBack.png")));

    private JLabel cowLabel_1 = new JLabel(new ImageIcon(Game.class.getResource("cow.png")));
    private JLabel cowLabel_2 = new JLabel(new ImageIcon(Game.class.getResource("cow.png")));
    private JLabel cowLabel_3 = new JLabel(new ImageIcon(Game.class.getResource("cow.png")));
    private JLabel cowLabel_4 = new JLabel(new ImageIcon(Game.class.getResource("cow.png")));
    private JLabel bullLabel_1 = new JLabel(new ImageIcon(Game.class.getResource("bull.png")));
    private JLabel bullLabel_2 = new JLabel(new ImageIcon(Game.class.getResource("bull.png")));
    private JLabel bullLabel_3 = new JLabel(new ImageIcon(Game.class.getResource("bull.png")));
    private JLabel bullLabel_4 = new JLabel(new ImageIcon(Game.class.getResource("bull.png")));
    private JLabel bubbleInstructions = new JLabel(new ImageIcon(Game.class.getResource("bubble.png")));
    private JLabel bubbleInstructionsText = new JLabel("<html> <div style='text-align: center;'>Type your<br>number here</html>");


    Game() {
        /* Home Panel */

        buttonNewGame.setBounds(190, 20, 140, 60);
        buttonNewGame.setVisible(true);
        buttonNewGame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                GameMethods.resetLabelVis(bullLabel_1, bullLabel_2, bullLabel_3, bullLabel_4);
                GameMethods.resetLabelVis(cowLabel_1, cowLabel_2, cowLabel_3, cowLabel_4);
                cl.show(panelContainer, "Game");
                secretNumber = GameMethods.numberGen();
                previousResults.setText("");
                countTimes = 0;
                bubbleInstructions.setVisible(true);
                bubbleInstructionsText.setVisible(true);
                inputField.setText("");
            }
        });

        buttonInstructions.setBounds(190, 80, 140, 60);
        buttonInstructions.setVisible(true);
        buttonInstructions.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cl.show(panelContainer, "Instructions");
            }
        });

        buttonHighScore.setBounds(190, 140, 140, 60);
        buttonHighScore.setVisible(true);
        buttonHighScore.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cl.show(panelContainer, "HighScore");
            }
        });

        buttonHistory.setBounds(190, 200, 140, 60);
        buttonHistory.setVisible(true);
        buttonHistory.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cl.show(panelContainer, "History");
            }
        });

        buttonExit.setBounds(190, 260, 140, 60);
        buttonExit.setVisible(true);
        buttonExit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        cl = new CardLayout(0, 0);
        panelContainer = new JPanel(cl);


        homePanel.setLayout(null);
        homePanel.add(buttonNewGame);
        homePanel.add(buttonInstructions);
        homePanel.add(buttonHighScore);
        homePanel.add(buttonHistory);
        homePanel.add(buttonExit);
        homePanel.setSize(500, 500);
        homePanel.setVisible(true);

        panelContainer.add(homePanel, "Home");

        /* Game Panel */
        inputField = new JTextField();
        inputField.setForeground(new Color(155, 104, 13));
        inputField.setBounds(135, 420, 150, 50);
        inputField.setFont(new Font("SansSerif", Font.BOLD, 50));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setDocument(new JTextFieldLimit(4));
        inputField.setBorder(brownBorder);
        inputField.addActionListener(action);

        previousResults = new JTextArea();
        previousResults.setEditable(false);
        previousResults.setForeground(new Color(128, 82, 7));
        previousResults.setFont(new Font("SansSerif", Font.BOLD, 15));
        JScrollPane scroll = new JScrollPane(previousResults);
        scroll.setBorder(null);
        scroll.setBounds(16, 0, 200, 200);
        scroll.setVisible(true);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        buttonBack_1.setBounds(405, -5, 100, 50);
        buttonBack_1.setVisible(true);
        buttonBack_1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cl.show(panelContainer, "Home");
            }
        });


        /* Cow Labels */
        cowLabel_1.setBounds(325, 90, 50, 60);
        cowLabel_1.setVisible(false);
        cowLabel_2.setBounds(365, 90, 50, 60);
        cowLabel_2.setVisible(false);
        cowLabel_3.setBounds(405, 90, 50, 60);
        cowLabel_3.setVisible(false);
        cowLabel_4.setBounds(445, 90, 50, 60);
        cowLabel_4.setVisible(false);

        /* Bull Labels */
        bullLabel_1.setBounds(325, 25, 50, 65);
        bullLabel_1.setVisible(false);
        bullLabel_2.setBounds(365, 25, 50, 65);
        bullLabel_2.setVisible(false);
        bullLabel_3.setBounds(405, 25, 50, 65);
        bullLabel_3.setVisible(false);
        bullLabel_4.setBounds(445, 25, 50, 65);
        bullLabel_4.setVisible(false);

        bubbleInstructions.setBounds(10, 270, 135, 150);
        bubbleInstructions.setVisible(true);
        bubbleInstructionsText.setBounds(30, 270, 100, 100);
        bubbleInstructionsText.setForeground(new Color(128, 82, 7));
        bubbleInstructionsText.setFont(new Font("SansSerif", Font.BOLD, 15));
        bubbleInstructionsText.setVisible(true);

        gamePanel.setLayout(null);
        gamePanel.setSize(500, 500);
        gamePanel.add(inputField);
        gamePanel.add(buttonBack_1);
        gamePanel.add(cowLabel_1);
        gamePanel.add(cowLabel_2);
        gamePanel.add(cowLabel_3);
        gamePanel.add(cowLabel_4);
        gamePanel.add(bullLabel_1);
        gamePanel.add(bullLabel_2);
        gamePanel.add(bullLabel_3);
        gamePanel.add(bullLabel_4);
        gamePanel.add(bubbleInstructionsText);
        gamePanel.add(bubbleInstructions);
        gamePanel.add(scroll);
        gamePanel.setVisible(true);
        panelContainer.add(gamePanel, "Game");

        /* Instruction Panel */
        instructionsPanel.setLayout(null);
        instructionsPanel.setSize(500, 500);
        instructionsPanel.setVisible(true);

        JLabel instrLabel = new JLabel("INSTRUCTIONS");
        instrLabel.setBounds(140, -6, 150, 50);
        instrLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        instrLabel.setForeground(new Color(128, 82, 7));
        instrLabel.setVisible(true);
        gameInstructions = new JTextArea(
                "\n The number of Bulls - correct digits in the right position." +
                        "\n The number of Cows - correct digits but in the wrong position." +
                        "\n For example if the correct number is 2745:" +
                        "\n Your guess: 1389 - 0 Bulls, 0 Cows." +
                        "\n Your guess: 1234 - 0 Bulls, 2 Cows." +
                        "\n Your guess: 1785 - 2 Bulls, 0 Cows." +
                        "\n Your guess: 2745 - 4 Bulls!");
        gameInstructions.setEditable(false);
        gameInstructions.setForeground(new Color(128, 82, 7));
        gameInstructions.setFont(new Font("SansSerif", Font.BOLD, 16));
        gameInstructions.setVisible(true);
        gameInstructions.setBounds(10, 30, 470, 470);

        buttonBack_2.setBounds(405, -5, 100, 50);
        buttonBack_2.setVisible(true);
        buttonBack_2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cl.show(panelContainer, "Home");
            }
        });

        instructionsPanel.add(buttonBack_2);
        instructionsPanel.add(gameInstructions);
        instructionsPanel.add(instrLabel);
        panelContainer.add(instructionsPanel, "Instructions");

        /*High Score Panel */
        highScorePanel.setLayout(null);
        highScorePanel.setSize(500, 500);
        highScorePanel.setVisible(true);

        hs= new JTextArea();
        hs.setBounds(150,30,300,300);
        hs.setText(GameMethods.returnHs());
        hs.setForeground(new Color(128, 82, 7));
        hs.setFont(new Font("SansSerif", Font.BOLD, 20));
        hs.setEditable(false);
        hs.setVisible(true);

        buttonBack_3.setBounds(405, -5, 100, 50);
        buttonBack_3.setVisible(true);
        buttonBack_3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cl.show(panelContainer, "Home");
            }
        });

        highScorePanel.add(buttonBack_3);
        highScorePanel.add(hs);
        panelContainer.add(highScorePanel, "HighScore");

        /* History Panel */
        historyPanel.setLayout(null);
        historyPanel.setSize(500, 500);
        historyPanel.setVisible(true);


        historyResults = new JTextArea();
        historyResults.setEditable(false);
        historyResults.setForeground(new Color(128, 82, 7));
        historyResults.setFont(new Font("SansSerif", Font.BOLD, 13));
        JScrollPane scroll_2 = new JScrollPane(historyResults);
        scroll_2.setBorder(null);
        scroll_2.setBounds(10, 0, 370, 200);
        scroll_2.setVisible(true);
        scroll_2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        buttonBack_4.setBounds(405, -5, 100, 50);
        buttonBack_4.setVisible(true);
        buttonBack_4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cl.show(panelContainer, "Home");
            }
        });

        historyPanel.add(buttonBack_4);
        historyPanel.add(scroll_2);
        panelContainer.add(historyPanel, "History");

        this.add(panelContainer);
        cl.show(panelContainer, "Home1");
        cl.show(panelContainer, "Home");
        this.setTitle("bulls and cows");
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();
        this.setSize(500, 500);
        this.setResizable(false);
        this.setIconImage(icon);
    }

    private Action action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean isNumeric = inputField.getText().chars().allMatch( Character::isDigit );
            if (inputField.getText().length() == 4 && isNumeric) {
                bubbleInstructions.setVisible(false);
                bubbleInstructionsText.setVisible(false);
                countTimes++;
                int bulls = GameMethods.checkBull(secretNumber, inputField.getText());
                int cows = GameMethods.checkCow(secretNumber, inputField.getText());
                if (bulls == 4) {
                    GameMethods.changeLabelVis(bullLabel_1,bullLabel_2,bullLabel_3,bullLabel_4,bulls);
                    if(GameMethods.checkHS(countTimes)){
                        String name = JOptionPane.showInputDialog("You got a high score! Type in your name:");
                        if(name.length()!=0){
                            GameMethods.highScoreWriter(countTimes,name);
                            cl.show(panelContainer, "Home");
                            hs.setText(GameMethods.returnHs());
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(gamePanel, "You guessed it right from " + countTimes + " times! The number was "
                                + secretNumber[0] + secretNumber[1] + secretNumber[2] + secretNumber[3] + "!");
                        cl.show(panelContainer, "Home");
                    }
                    historyResults.setText("You guessed it right from "+countTimes+" times! The number was "
                            + secretNumber[0] + secretNumber[1] + secretNumber[2] + secretNumber[3] + "!\n" +historyResults.getText());

                } else {
                    GameMethods.resetLabelVis(bullLabel_1,bullLabel_2,bullLabel_3,bullLabel_4);
                    GameMethods.changeLabelVis(bullLabel_1,bullLabel_2,bullLabel_3,bullLabel_4,bulls);
                    GameMethods.resetLabelVis(cowLabel_1,cowLabel_2,cowLabel_3,cowLabel_4);
                    GameMethods.changeLabelVis(cowLabel_1,cowLabel_2,cowLabel_3,cowLabel_4,cows);
                }
                previousResults.setText(previousResults.getText() + "\n" + inputField.getText() + " == Bulls: " + bulls + " | Cows: " + cows);
                inputField.setText("");
            }
        }
    };
}
