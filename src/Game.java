import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Game extends JFrame implements ActionListener {
    private JPanel panelContainer;
    private CardLayout cl;
    private JButton buttonNewGame, buttonInstructions,buttonHistory,buttonHighScore, buttonExit, buttonBack, buttonBack_2, buttonBack_3, buttonBack_4;
    private JTextField inputField;
    private JTextArea previousResults,gameInstructions;
    private int[] secretNumber;
    private int countTimes;
    private Border brownBorder = BorderFactory.createLineBorder(new Color(128,82,7), 4);
    private Image bg= getToolkit().getImage(Game.class.getResource("Background.jpg"));
    private Image icon=getToolkit().getImage(Game.class.getResource("gameIcon.png"));
    private BackgroundPanel homePanel= new BackgroundPanel(bg, BackgroundPanel.SCALED,0.0f,0.0f);
    private BackgroundPanel gamePanel= new BackgroundPanel(bg, BackgroundPanel.SCALED,0.0f,0.0f);
    private BackgroundPanel instructionsPanel = new BackgroundPanel(bg, BackgroundPanel.SCALED,0.0f,0.0f);
    private BackgroundPanel historyPanel= new BackgroundPanel(bg, BackgroundPanel.SCALED,0.0f,0.0f);
    private BackgroundPanel highScorePanel = new BackgroundPanel(bg, BackgroundPanel.SCALED,0.0f,0.0f);

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


    Game(){
        //TODO add history for current game session
        //TODO add high score tracker with name to be put on every high score
        //TODO change the way buttons look
        //TODO add instructions(to type the number and press enter
        /* Home Panel */
        buttonNewGame = new JButton("New Game");
        buttonNewGame.setBounds(190,20,140,40);
        buttonNewGame.setFont(new Font("SansSerif", Font.BOLD, 18));
        buttonNewGame.setHorizontalAlignment(SwingConstants.CENTER);
        buttonNewGame.setContentAreaFilled(false);
        buttonNewGame.setBorder(brownBorder);
        buttonNewGame.setVisible(true);
        buttonNewGame.addActionListener(this);

        buttonInstructions = new JButton("Instructions");
        buttonInstructions.setBounds(190,70,140,40);
        buttonInstructions.setFont(new Font("SansSerif",Font.BOLD, 18));
        buttonInstructions.setHorizontalAlignment(SwingConstants.CENTER);
        buttonInstructions.setContentAreaFilled(false);
        buttonInstructions.setBorder(brownBorder);
        buttonInstructions.setVisible(true);
        buttonInstructions.addActionListener(this);

        buttonHighScore= new JButton("High Score");
        buttonHighScore.setBounds(190,120,140,40);
        buttonHighScore.setFont(new Font("SansSerif",Font.BOLD, 18));
        buttonHighScore.setHorizontalAlignment(SwingConstants.CENTER);
        buttonHighScore.setContentAreaFilled(false);
        buttonHighScore.setBorder(brownBorder);
        buttonHighScore.setVisible(true);
        buttonHighScore.addActionListener(this);

        buttonHistory= new JButton("History");
        buttonHistory.setBounds(190,170,140,40);
        buttonHistory.setFont(new Font("SansSerif",Font.BOLD, 18));
        buttonHistory.setHorizontalAlignment(SwingConstants.CENTER);
        buttonHistory.setContentAreaFilled(false);
        buttonHistory.setBorder(brownBorder);
        buttonHistory.setVisible(true);
        buttonHistory.addActionListener(this);

        buttonExit = new JButton("Exit");
        buttonExit.setBounds(190,220,140,40);
        buttonExit.setFont(new Font("SansSerif", Font.BOLD, 18));
        buttonExit.setContentAreaFilled(false);
        buttonExit.setBorder(brownBorder);
        buttonExit.setVisible(true);
        buttonExit.addActionListener(this);

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
        inputField.setForeground(new Color(155,104,13));
        inputField.setBounds(135, 420, 150, 50);
        inputField.setFont(new Font("SansSerif", Font.BOLD , 50));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setDocument(new JTextFieldLimit(4));
        inputField.setBorder(brownBorder);
        inputField.addActionListener(action);

        previousResults = new JTextArea();
        previousResults.setEditable(false);
        previousResults.setForeground(new Color(128,82,7));
        previousResults.setFont(new Font("SansSerif", Font.BOLD, 15));
        JScrollPane scroll = new JScrollPane(previousResults);
        scroll.setBorder(null);
        scroll.setBounds(16, 0, 200, 200);
        scroll.setVisible(true);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        buttonBack= new JButton("Back");
        buttonBack.setForeground(Color.BLACK);
        buttonBack.setContentAreaFilled(false);
        buttonBack.setFont(new Font("SansSerif", Font.BOLD, 20));
        buttonBack.setBounds(392,3,100,30);
        buttonBack.setBorder(brownBorder);
        buttonBack.setVisible(true);
        buttonBack.addActionListener(this);

        /* Cow Labels */
        cowLabel_1.setBounds(325, 90,50,60);
        cowLabel_1.setVisible(false);
        cowLabel_2.setBounds(365, 90,50,60);
        cowLabel_2.setVisible(false);
        cowLabel_3.setBounds(405, 90,50,60);
        cowLabel_3.setVisible(false);
        cowLabel_4.setBounds(445, 90,50,60);
        cowLabel_4.setVisible(false);

        /* Bull Labels */
        bullLabel_1.setBounds(325, 25,50,65);
        bullLabel_1.setVisible(false);
        bullLabel_2.setBounds(365, 25,50,65);
        bullLabel_2.setVisible(false);
        bullLabel_3.setBounds(405, 25,50,65);
        bullLabel_3.setVisible(false);
        bullLabel_4.setBounds(445, 25,50,65);
        bullLabel_4.setVisible(false);

        bubbleInstructions.setBounds(10,270,135,150);
        bubbleInstructions.setVisible(true);
        bubbleInstructionsText.setBounds(30,270,100,100);
        bubbleInstructionsText.setForeground(new Color(128,82,7));
        bubbleInstructionsText.setFont(new Font("SansSerif", Font.BOLD, 15));
        bubbleInstructionsText.setVisible(true);

        gamePanel.setLayout(null);
        gamePanel.setSize(500, 500);
        gamePanel.add(inputField);
        gamePanel.add(buttonBack);
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

        buttonBack_2= new JButton("Back");
        buttonBack_2.setForeground(Color.BLACK);
        buttonBack_2.setContentAreaFilled(false);
        buttonBack_2.setFont(new Font("SansSerif", Font.BOLD, 20));
        buttonBack_2.setBounds(392,3,100,30);
        buttonBack_2.setBorder(brownBorder);
        buttonBack_2.setVisible(true);
        buttonBack_2.addActionListener(this);

        JLabel instrLabel= new JLabel("INSTRUCTIONS");
        instrLabel.setBounds(140,-6,150,50);
        instrLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        instrLabel.setForeground(new Color(128,82,7));
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
        gameInstructions.setForeground(new Color(128,82,7));
        gameInstructions.setFont(new Font("SansSerif", Font.BOLD, 16));
        gameInstructions.setVisible(true);
        gameInstructions.setBounds(10,30,470,470);


        instructionsPanel.add(buttonBack_2);
        instructionsPanel.add(gameInstructions);
        instructionsPanel.add(instrLabel);
        panelContainer.add(instructionsPanel,"Instructions");

        /* High Score Panel */
        highScorePanel.setLayout(null);
        highScorePanel.setSize(500, 500);
        highScorePanel.setVisible(true);

        buttonBack_3= new JButton("Back");
        buttonBack_3.setForeground(Color.BLACK);
        buttonBack_3.setContentAreaFilled(false);
        buttonBack_3.setFont(new Font("SansSerif", Font.BOLD, 20));
        buttonBack_3.setBounds(392,3,100,30);
        buttonBack_3.setBorder(brownBorder);
        buttonBack_3.setVisible(true);
        buttonBack_3.addActionListener(this);

        highScorePanel.add(buttonBack_3);
        panelContainer.add(highScorePanel,"HighScore");

        /* History Panel */
        historyPanel.setLayout(null);
        historyPanel.setSize(500, 500);
        historyPanel.setVisible(true);

        buttonBack_4= new JButton("Back");
        buttonBack_4.setForeground(Color.BLACK);
        buttonBack_4.setContentAreaFilled(false);
        buttonBack_4.setFont(new Font("SansSerif", Font.BOLD, 20));
        buttonBack_4.setBounds(392,3,100,30);
        buttonBack_4.setBorder(brownBorder);
        buttonBack_4.setVisible(true);
        buttonBack_4.addActionListener(this);

        historyPanel.add(buttonBack_4);
        panelContainer.add(historyPanel,"History");

        this.add(panelContainer);
        cl.show(panelContainer,"Home1");
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

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonNewGame) {
            GameMethods.resetLabelVis(bullLabel_1,bullLabel_2,bullLabel_3,bullLabel_4);
            GameMethods.resetLabelVis(cowLabel_1,cowLabel_2,cowLabel_3,cowLabel_4);
            cl.show(panelContainer, "Game");
            secretNumber = GameMethods.numberGen();
            previousResults.setText("");
            countTimes=0;
            bubbleInstructions.setVisible(true);
            bubbleInstructionsText.setVisible(true);
            inputField.setText("");
        }

        if(e.getSource()==buttonInstructions){
            cl.show(panelContainer, "Instructions");
        }

        if(e.getSource()==buttonHighScore){
            cl.show(panelContainer, "HighScore");
        }

        if(e.getSource()==buttonHistory){
            cl.show(panelContainer,"History");
        }

        if (e.getSource() == buttonExit ) {
            System.exit(0);
        }

        if(e.getSource()==buttonBack || e.getSource()==buttonBack_2 || e.getSource()==buttonBack_3 || e.getSource()==buttonBack_4){
            cl.show(panelContainer, "Home");
        }

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
                    JOptionPane.showMessageDialog(gamePanel, "You guessed it right from "+countTimes+" times! The number was "
                            + secretNumber[0] + secretNumber[1] + secretNumber[2] + secretNumber[3] + "!");
                    cl.show(panelContainer, "Home");
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
