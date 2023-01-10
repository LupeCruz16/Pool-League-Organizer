import javax.swing.*;
import javax.swing.SpringLayout.Constraints;

import java.awt.*;

public class controller extends JPanel{

    private static controller instance;

    JPanel cards;

    homePanel mainPanel;
    playerPanel playerPanel;
    tourneyPanel tourneyPanel;
    weeklyPanel weeklyPanel;

    public controller(){
        setLayout(new BorderLayout());
        setSize(500, 500);

        cards = new JPanel(new CardLayout());

        mainPanel = new homePanel();
        playerPanel = new playerPanel();
        tourneyPanel = new tourneyPanel();
        weeklyPanel = new weeklyPanel();
        
        cards.add(mainPanel, "Homescreen");
        cards.add(playerPanel, "Upload Player Info");
        cards.add(tourneyPanel, "View Bracket");
        cards.add(weeklyPanel, "Weekly Matches");
;
        add(cards);
        setVisible(true);

    }//end of public controller class 

    private static void createAndDisplay(){
        JFrame frame = new JFrame("Pool League Helper");

        instance = new controller();

        frame.getContentPane().add(instance);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }//end of create and display 

    public static void main(String[] args){
        createAndDisplay();
    }

    public void changeCard(String card){
        CardLayout c1 = (CardLayout) (cards.getLayout());
        c1.show(cards, card);
    }//end of change card 

    public static controller getInstance(){
        return instance;
    }

}//end of controller class
