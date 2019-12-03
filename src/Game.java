import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class provides an implementation of the game. It can be described as the class handling the gameplay.
 * It randomly shuffles all the cards and also checks for the winner and displays the end result.
 *
 * @author Mesbaul
 */
public class Game {
    Random rand = new Random();
    private static ArrayList<Integer>winner = new ArrayList<Integer>(6);

    /**
     * One of the constructors of the class. It is called when we start the game. A random number from 0 to 51 is selected. This is done to pick a card randomly.
     * Random card image directory from card ArrayList is selected for displaying image. The face value of the card is added to the winner ArrayList for determining the winner.
     * The card image directory selected is removed from the cards ArrayList to avoid picking duplicate cards. When all 6 cards are added, the Hand() method is called to pick the winner.
     *
     * @param g An instance of the UserAction class
     * @param c An integer which directs the program on whether to add cards in the dealer panel or player panel
     */
    public Game(UserAction g, int c) {
        for(int i=c;i<=c+2;++i) {
            int index = rand.nextInt(g.getCards().size());
            g.getImage()[i].setIcon(new ImageIcon(g.getCards().get((index))));
            int s = Integer.valueOf(g.getCards().get((index)).substring(13,g.getCards().get(index).length()-4));
            winner.add(s);
            g.getCards().remove(index);
            if(i==2) {
                Hand();
            }
        }
    }

    /**
     * Another constructor for the class which is called only when the player wants to change one of their cards. Hence, only one card is changed, the face value of the card added to the winner ArrayList, and the corresponding card image directory removed from card ArrayList.
     *
     * @param g An instance of the UserAction class
     * @param i JLabel representing the corresponding card Image
     * @param j An integer which directs the program on which card out of the 3 is being replaced
     */
    public Game(UserAction g, JLabel i, int j) {
        int index = rand.nextInt(g.getCards().size());
        i.setIcon(new ImageIcon(g.getCards().get(index)));
        Integer s = Integer.valueOf(g.getCards().get((index)).substring(13,g.getCards().get(index).length()-4));
        winner.set(j-3, s);
        g.getCards().remove(index);
    }

    private void Hand() {
        int p=0, d=0, psum=0, dsum=0;
        for(int i=0;i<=2;++i) {
            if(winner.get(i)>10) {
                p++;
            }
            psum+=winner.get(i);
        }
        for(int i=3;i<=5;++i) {
            if(winner.get(i)>10) {
                d++;
            }
            dsum+=winner.get(i);
        }
        if(p>d) {
            JOptionPane.showMessageDialog(null, "Congratulations! You win this round!");
            UserAction.money+= UserAction.bet;
        }
        else if(d>p) {
            JOptionPane.showMessageDialog(null, "Sorry! The Dealer wins this round");
            UserAction.money-=UserAction.bet;
        }
        else if(d==p) {
            if((psum%10)>(dsum%10)) {
                JOptionPane.showMessageDialog(null, "Congratulations! You win this round!");
                UserAction.money+= UserAction.bet;
            }
            else {
                JOptionPane.showMessageDialog(null, "Sorry! The Dealer wins this round");
                UserAction.money-=UserAction.bet;
            }
        }
        winner.clear();
    }
}
