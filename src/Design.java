import javax.swing.*;
import java.awt.*;

/**
 * This class handles the design of the game. It is solely used to craft the GUI, where we add the card images, the buttons, text fields, the menu bar, the message and the background colour.
 * After adding all the GUI components, we add the main panel to the frame and set it to visible.
 *
 * @author Mesbaul
 */
public class Design {
    /**
     * This is the constructor of the class. It creates the layout for the main panel, and calls all the respective private functions which craft the GUI components.
     * It also sets the title and size of the frame and adds the main panel to the frame.
     *
     * @param g An instance of the UserAction class which contains all the JPanel, JLabel, JButton, JMenu as instance variables.
     */
    public Design(UserAction g) {
        g.getMainPanel().setLayout(new GridLayout(5, 1));
        dealerDeck(g);
        playerDeck(g);
        buttons(g);
        bet(g);
        message(g);
        ctrl(g);
        g.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.getFrame().setSize(400, 600);
        g.getFrame().setTitle("A Simple Card Game");
        g.getFrame().add(g.getMainPanel());
        g.getFrame().setVisible(true);
    }

    private void dealerDeck(UserAction g) {
        JPanel dealer = new JPanel();
        for(int i=0;i<3;++i) {
            g.getImage()[i] = new JLabel();
            ImageIcon img = new ImageIcon("Images/card_back.gif");
            g.getImage()[i].setIcon(img);
            dealer.add(g.getImage()[i]);
        }
        dealer.setBackground(Color.green);
        g.getMainPanel().add(dealer);
    }

    private void playerDeck(UserAction g) {
        JPanel player = new JPanel();
        for(int i=3;i<6;++i) {
            g.getImage()[i] = new JLabel();
            ImageIcon img = new ImageIcon("Images/card_back.gif");
            g.getImage()[i].setIcon(img);
            player.add(g.getImage()[i]);
        }
        player.setBackground(Color.green);
        g.getMainPanel().add(player);
    }

    private void buttons(UserAction g) {
        JPanel buttons = new JPanel();
        for(int i=1;i<=3;++i) {
            JButton b = new JButton("Replace Card " +i);
            b.setEnabled(false);
            b.setBackground(Color.GREEN);
            buttons.add(b);
            g.getButton()[i-1] = b;
        }
        buttons.setBackground(Color.green);
        g.getMainPanel().add(buttons);
    }

    private void bet(UserAction g) {
        JPanel bet_panel = new JPanel();
        JLabel bet_text = new JLabel("Bet: $");
        g.getButton()[3] = new JButton("Start");
        g.getButton()[4] = new JButton("Result");
        g.getButton()[4].setEnabled(false);
        bet_panel.add(bet_text);
        bet_panel.add(g.getBet_amount());
        bet_panel.add(g.getButton()[3]);
        bet_panel.add(g.getButton()[4]);
        g.getMainPanel().add(bet_panel);
    }

    private void message(UserAction g) {
        JPanel m = new JPanel(new GridBagLayout()); //Places the JLabel in the centre
        g.setMsg("Please place your bet! Amount of money you have: $100");
        m.add(g.getMsg());
        g.getMainPanel().add(m);
    }

    private void ctrl(UserAction g) {
        JMenuBar items = new JMenuBar();
        JMenu control = new JMenu("Control");
        control.add(g.getExit());
        items.add(control);
        items.add(g.getHelp());
        g.getFrame().setJMenuBar(items);
    }

}
