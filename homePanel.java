
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class homePanel extends JPanel implements ActionListener{
    
    private JButton playerInfo, bracket, weekly, exit;
    private JLabel appName, option;
    private JPanel blankPanel, sidePanel, mainPanel, sideAndMainPanel;
    private JSeparator separator;

    public homePanel(){

        //Creating Panels
        sidePanel = new JPanel();
        mainPanel = new JPanel();
        sideAndMainPanel = new JPanel();
        blankPanel = new JPanel();

        //Creating a JButton for each new Panel to be displayed
        playerInfo = new JButton("Create a Tournament"); //creating uploading photos button 
        playerInfo.addActionListener(this); //monitor if clicked 
        uiDesign.formatButton(playerInfo);

        bracket = new JButton("View Tournament Bracket");
        bracket.addActionListener(this);
        uiDesign.formatButton(bracket);

        weekly = new JButton("View Weekly Matches");
        weekly.addActionListener(this);
        uiDesign.formatButton(weekly);

        exit = new JButton("Exit");
        exit.addActionListener(this);
        uiDesign.formatButton(exit);

        //Creating Labels
        appName = new JLabel("League Manager");
        appName.setFont(new Font("Arial", Font.BOLD,30));//resizing text within label
        appName.setPreferredSize(new Dimension(300, 45));
        appName.setHorizontalAlignment(JLabel.CENTER);
        appName.setForeground(Color.white);

        option = new JLabel("Please choose an option");
        option.setFont(new Font("Arial", Font.PLAIN, 25));//resizing text within label
        option.setForeground(Color.gray);

        //Creating a line separator
        separator = new JSeparator();
        separator.setOrientation(JSeparator.HORIZONTAL);
        separator.setPreferredSize(new Dimension(240, 2));

        //Adding elements to panels
        sidePanel.setLayout(new FlowLayout()); 
        sidePanel.setPreferredSize(new Dimension(300,800));
        sidePanel.setBackground(colorPalette.poolBlue);
        uiDesign.spacer(sidePanel,20,80);
        sidePanel.add(appName);
        sidePanel.add(separator);
        uiDesign.spacer(sidePanel,60,60);
        sidePanel.add(playerInfo);
        uiDesign.spacer(sidePanel,20,25);
        sidePanel.add(bracket);
        uiDesign.spacer(sidePanel,20,25);
        sidePanel.add(weekly);
        uiDesign.spacer(sidePanel,20,25);
        sidePanel.add(exit);

        mainPanel.setLayout(new CardLayout());
        mainPanel.setPreferredSize(new Dimension(700, 800));

        blankPanel.setLayout(new BoxLayout(blankPanel, BoxLayout.Y_AXIS));
        uiDesign.spacer(blankPanel,10,400);
        blankPanel.add(option);
        option.setAlignmentX(Component.CENTER_ALIGNMENT);
        blankPanel.setBackground(colorPalette.defaultGrey);
       
        //Instantiate the panel Classes
        playerPanel playerPanel = new playerPanel();
        tourneyPanel tourneyPanel = new tourneyPanel();
        weeklyPanel weeklyPanel = new weeklyPanel();

        mainPanel.add(blankPanel, "blank panel");
        mainPanel.add(playerPanel, "player panel");
        mainPanel.add(tourneyPanel, "tourney panel");
        mainPanel.add(weeklyPanel, "weekly panel");

        sideAndMainPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        sideAndMainPanel.setBackground(colorPalette.defaultGrey);
        sideAndMainPanel.add(sidePanel);
        sideAndMainPanel.add(mainPanel);

        //Keeping the header panel and the main panel vertical from eachother
        Box mainBox = Box.createVerticalBox();
        sideAndMainPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
        mainBox.add(sideAndMainPanel);
        add(mainBox);
       
    }//end of main class

    //If a button is clicked then perform this action
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playerInfo) {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "player panel");
        } else if (e.getSource() == bracket) {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "tourney panel");
        } else if (e.getSource() == weekly) {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "weekly panel");
        } else if(e.getSource() == exit) {
            System.exit(0);
        }
    }

    //Get access to mainpanel
    public JPanel getMainPanel() {
        return mainPanel;
    }

}//end of class 

