import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Game extends JFrame implements ActionListener {
    private JPanel panelContainer;
    private CardLayout cl;
    private JButton buttonNewGame, buttonExit, buttonBack;
    private JTextField inputField;
    private JTextArea previousResults;
    private int[] secretNumber;
    private int countTimes;
    private Border brownBorder = BorderFactory.createLineBorder(new Color(128,82,7), 4);
    private Image bg= getToolkit().getImage(Game.class.getResource("Background.jpg"));
    private Image icon=getToolkit().getImage(Game.class.getResource("gameIcon.png"));
    private BackgroundPanel homePanel= new BackgroundPanel(bg, BackgroundPanel.SCALED,0.0f,0.0f);
    private BackgroundPanel gamePanel= new BackgroundPanel(bg, BackgroundPanel.SCALED,0.0f,0.0f);
    private JLabel cowLabel_1 = new JLabel(new ImageIcon(Game.class.getResource("cow.png")));
    private JLabel cowLabel_2 = new JLabel(new ImageIcon(Game.class.getResource("cow.png")));
    private JLabel cowLabel_3 = new JLabel(new ImageIcon(Game.class.getResource("cow.png")));
    private JLabel cowLabel_4 = new JLabel(new ImageIcon(Game.class.getResource("cow.png")));
    private JLabel bullLabel_1 = new JLabel(new ImageIcon(Game.class.getResource("bull.png")));
    private JLabel bullLabel_2 = new JLabel(new ImageIcon(Game.class.getResource("bull.png")));
    private JLabel bullLabel_3 = new JLabel(new ImageIcon(Game.class.getResource("bull.png")));
    private JLabel bullLabel_4 = new JLabel(new ImageIcon(Game.class.getResource("bull.png")));

    Game(){
        /* Home Panel */
        buttonNewGame = new JButton("New Game");
        buttonNewGame.setLocation(190, 50);
        buttonNewGame.setFont(new Font("SansSerif", Font.BOLD, 18));
        buttonNewGame.setHorizontalAlignment(SwingConstants.CENTER);
        buttonNewGame.setContentAreaFilled(false);
        buttonNewGame.setSize(140, 40);
        buttonNewGame.setBorder(brownBorder);
        buttonNewGame.setVisible(true);
        buttonNewGame.addActionListener(this);

        buttonExit = new JButton("Exit");
        buttonExit.setLocation(190, 100);
        buttonExit.setFont(new Font("SansSerif", Font.BOLD, 18));
        buttonExit.setContentAreaFilled(false);
        buttonExit.setSize(140, 40);
        buttonExit.setBorder(brownBorder);
        buttonExit.setVisible(true);
        buttonExit.addActionListener(this);

        cl = new CardLayout(0, 0);
        panelContainer = new JPanel(cl);


        homePanel.setLayout(null);
        homePanel.add(buttonNewGame);
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
        gamePanel.add(scroll);
        gamePanel.setVisible(true);
        panelContainer.add(gamePanel, "Game");

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
        }

        if (e.getSource() == buttonExit) {
            System.exit(0);
        }
        if(e.getSource()==buttonBack){
            cl.show(panelContainer, "Home");
        }
    }

    private Action action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean isNumeric = inputField.getText().chars().allMatch( Character::isDigit );
            if (inputField.getText().length() == 4 && isNumeric) {
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
