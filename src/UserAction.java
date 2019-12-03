import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class contains the main function and it calls the constructor of design to craft the GUI. It also calls a method called Interact which creates all the ActionListeners for all the menu, buttons and textfields.
 *
 * @author Mesbaul
 */
public class UserAction {

    private JFrame frame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JLabel image[] = new JLabel[6];
    private JButton button[] = new JButton[5];
    private ArrayList<String>cards = new ArrayList<>();
    private JLabel msg = new JLabel();
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem help = new JMenuItem("Help");
    private JTextField bet_amount = new JTextField(10);
    public static int money = 100, bet = 0;

    private void setCards() {
        for(int i=1;i<=4;++i) {
            for(int j=1;j<=13;++j) {
                cards.add("Images/card_" +i + j+".gif");
            }
        }
    }

    /**
     * Getter for the instance variable frame. It is the main frame storing the main panel.
     *
     * @return frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Getter for the instance variable mainPanel. It is the main panel storing all the other 4 smaller panels.
     *
     * @return mainPanel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Getter for the image array. It stores the 6 JPanels which will represent the card images.
     *
     * @return image
     */
    public JLabel[] getImage() {
        return image;
    }

    /**
     * Getter for the button array. It stores all the 5 buttons used in the GUI.
     *
     * @return button
     */
    public JButton[] getButton() {
        return button;
    }

    /**
     * Getter for the cards ArrayList. The ArrayList contains the image directory path of all the 52 cards as strings. This is done in order to add card images easily to the JPanel.
     *
     * @return cards
     */
    public ArrayList<String> getCards() {
        return cards;
    }

    /**
     * Getter for the instance variable message. It displays messages to the player regarding the game.
     *
     * @return msg
     */
    public JLabel getMsg() {
        return msg;
    }

    /**
     * Getter for the instance variable exit. It is a MenuItem which terminates the game.
     *
     * @return exit
     */
    public JMenuItem getExit() {
        return exit;
    }

    /**
     * Getter for the instance variable help. It is a MenuItem which displays information about the game.
     *
     * @return help
     */
    public JMenuItem getHelp() {
        return help;
    }

    /**
     * Getter for the instance variable bet_amount. It is a TextField where we enter the amount we want to bet.
     *
     * @return bet_amount
     */
    public JTextField getBet_amount() {
        return bet_amount;
    }

    public void setMsg(String s) {
        msg.setText(s);
    }

    /**
     * Creates an instance variable for the UserAction class, passes it on to the constructor of the Design class, which crafts the GUI.
     * Calls the setCards method to arrange the path directories of the images in the ArrayList, and calls interact to create all the ActionListeners.
     *
     * @param args command line parameters
     */
    public static void main(String[] args) {
        UserAction g = new UserAction();
        new Design(g);
        g.setCards();
        g.Interact(g);
    }

    private void Interact(UserAction g) {
        help.addActionListener(new HelpListener());
        exit.addActionListener(new ExitListener());
        button[3].addActionListener(new StartListener(g));
        button[0].addActionListener(new Card1Listener(g));
        button[1].addActionListener(new Card2Listener(g));
        button[2].addActionListener(new Card3Listener(g));
        button[4].addActionListener(new ResultListener(g));
    }

    class HelpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, "Rules to determine who has better cards:\nJ, Q, K are regarded as special cards.\nRule 1: The one with more special cards wins.\nRule 2: If both have the same number of special cards, add the face values of the other card(s) and take the remainder after dividing the sum by 10. The one with a bigger remainder wins. (Note: Ace = 1).\nRule 3: The dealer wins if both rule 1 and rule 2 cannot distinguish the winner.");
        }
    }

    class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    class StartListener implements ActionListener {
        UserAction a;

        public StartListener(UserAction x) {
            a = x;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
                bet = 0;
                String s = getBet_amount().getText();
                if(s.length()==0) {
                    JOptionPane.showMessageDialog(frame, "Please enter your bet!");
                }
                else {
                    double d = Double.valueOf(s);
                    if(d!=Math.floor(d)) {
                        JOptionPane.showMessageDialog(frame, "WARNING: The bet you place must be a positive integer");
                    }
                    else if (d<0) {
                        JOptionPane.showMessageDialog(frame, "WARNING: The bet you place must be a positive integer");
                    }
                    else if (d>money) {
                        JOptionPane.showMessageDialog(frame, "WARNING: You do not have enough money. Please readjust your bet!");
                    }
                    else {
                        for(int i=0;i<=4;++i) {
                            if(i==3) {
                                button[i].setEnabled(false);
                                continue;
                            }
                            button[i].setEnabled(true);
                        }
                        setMsg("Your current bet is: $"+(int) d+" Amount of money you have: $"+money);
                        bet = (int) d;
                        new Game(a, 3);
                    }
                }

        }
    }

    class Card1Listener implements ActionListener {
        UserAction a;

        public Card1Listener(UserAction x) {
            a = x;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            button[0].setEnabled(false);
            if(!button[1].isEnabled()) {
                button[2].setEnabled(false);
            }
            else if(!button[2].isEnabled()) {
                button[1].setEnabled(false);
            }
            new Game(a, image[3], 3);
        }
    }

    class Card2Listener implements ActionListener {
        UserAction a;

        public Card2Listener(UserAction x) {
            a = x;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            button[1].setEnabled(false);
            if(!button[0].isEnabled()) {
                button[2].setEnabled(false);
            }
            else if(!button[2].isEnabled()) {
                button[0].setEnabled(false);
            }
            new Game(a,image[4], 4);
        }
    }

    class Card3Listener implements ActionListener {
        UserAction a;

        public Card3Listener(UserAction x) {
            a = x;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            button[2].setEnabled(false);
            if(!button[1].isEnabled()) {
                button[0].setEnabled(false);
            }
            else if(!button[0].isEnabled()) {
                button[1].setEnabled(false);
            }
            new Game(a, image[5], 5);
        }
    }

    class ResultListener implements ActionListener {
        UserAction a;

        public ResultListener(UserAction x) {
            a = x;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new Game(a, 0);
            if(money<=0) {
                for(JButton b : button) {
                    b.setEnabled(false);
                }
                setMsg("You have no more money! Please start a new game!");
                JOptionPane.showMessageDialog(frame, "Game over!\nYou have no more money!\nPlease start a new game!");

                System.exit(0);
            }
            else{
                for(int i=0;i<6;++i) {
                    getImage()[i].setIcon(new ImageIcon("Images/card_back.gif"));
                }
                for(int i=0;i<button.length;++i) {
                    if(i==3) {
                        button[i].setEnabled(true);
                    }
                    else {
                        button[i].setEnabled(false);
                    }
                }
                setMsg("Your current bet is: $"+(int) bet+" Amount of money you have: $"+money);
                setCards();
            }
        }
    }
}



