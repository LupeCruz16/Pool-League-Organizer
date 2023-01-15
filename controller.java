import javax.swing.*;
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
        mainPanel.setBackground(colorPalette.defaultGrey);
        cards.add(playerPanel, "Upload Player Info");
        playerPanel.setBackground(colorPalette.defaultGrey);
        cards.add(tourneyPanel, "View Bracket");
        tourneyPanel.setBackground(colorPalette.defaultGrey);
        cards.add(weeklyPanel, "Weekly Matches");
        weeklyPanel.setBackground(colorPalette.defaultGrey);
;
        add(cards);
        setVisible(true);

    }//end of public controller class 

    private static void createAndDisplay(){
        JFrame frame = new JFrame("Pool League Helper");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        instance = new controller();

        frame.getContentPane().add(instance);
        frame.setSize(1000, 800);
        frame.setVisible(true);
        frame.setResizable(false);
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

    public homePanel getHomePanel() {
        return mainPanel;
    }

}//end of controller class
