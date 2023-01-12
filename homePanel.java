
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class homePanel extends JPanel implements ActionListener{
    
    private JButton playerInfo, bracket, weekly, exit;
    private JLabel appName, blank, option, tab, tab2, tab3;

    public homePanel(){

        //Creating Panels
        JPanel main = new JPanel();
        JPanel prompt = new JPanel();
        JPanel space = new JPanel();
        JPanel space2 = new JPanel();
        JPanel title = new JPanel();
        JPanel middle = new JPanel();
        JPanel button = new JPanel();

        //Creating buttons
        playerInfo = new JButton("Upload Players");//creating uploading photos button 
        playerInfo.addActionListener(this);//monitor if clicked 
        playerInfo.setMaximumSize(new Dimension(300, 35));
        playerInfo.setFont(new Font("Arial", Font.PLAIN, 15));
        playerInfo.setForeground(Color.white);
        playerInfo.setBackground(colorPalette.poolBlue);
        playerInfo.setOpaque(true);

        bracket = new JButton("View Tournament Bracket");
        bracket.addActionListener(this);
        bracket.setMaximumSize(new Dimension(300, 35));
        bracket.setFont(new Font("Arial", Font.PLAIN, 15));
        bracket.setForeground(Color.white);
        bracket.setBackground(colorPalette.poolBlue);
        bracket.setOpaque(true);

        weekly = new JButton("View Weekly Matches");
        weekly.addActionListener(this);
        weekly.setMaximumSize(new Dimension(300, 35));
        weekly.setFont(new Font("Arial", Font.PLAIN, 15));
        weekly.setForeground(Color.white);
        weekly.setBackground(colorPalette.poolBlue);
        weekly.setOpaque(true);

        exit = new JButton("Exit");
        exit.addActionListener(this);
        exit.setMaximumSize(new Dimension(300, 35));
        exit.setFont(new Font("Arial", Font.PLAIN, 15));
        exit.setForeground(Color.white);
        exit.setBackground(colorPalette.poolBlue);
        exit.setOpaque(true);

        //Creating Labels
        appName = new JLabel("Welcome To The League Manager");
        appName.setFont(new Font("Arial", Font.BOLD, 40));//resizing text within label
        appName.setForeground(Color.white);

        option = new JLabel("Please choose an option");
        option.setFont(new Font("Arial", Font.PLAIN, 25));//resizing text within label
        option.setForeground(Color.white);

        tab = new JLabel("    ");
        tab2 = new JLabel("    ");
        tab3 = new JLabel("    ");

        blank = new JLabel("");

        //Adding elements to panels 
        space.add(blank);
        space.setPreferredSize(new Dimension(820, 15));
        space.setBackground(colorPalette.poolBlue);
        space.setBackground(colorPalette.defaultGrey);

        title.add(appName);
        title.setPreferredSize(new Dimension(820, 65));
        title.setBackground(colorPalette.poolBlue);

        prompt.add(option);
        prompt.setPreferredSize(new Dimension(820, 30));
        prompt.setBackground(colorPalette.poolBlue);

        middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
        middle.setAlignmentX(Component.CENTER_ALIGNMENT);
        middle.setBackground(colorPalette.defaultGrey);

        middle.add(playerInfo);
        middle.add(tab);
        middle.add(bracket);
        middle.add(tab2);
        middle.add(weekly);
        middle.add(tab3);
        middle.add(exit);

        space2.setBackground(colorPalette.defaultGrey);
        space2.add(blank);
        button.setBackground(colorPalette.defaultGrey);
        
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(colorPalette.defaultGrey);

        main.add(space);
        main.add(title);
        main.add(prompt);
        main.add(space2);
        main.add(middle);
        main.add(button);

        //adding for display 
        add(main);

        //displaying panel
        setVisible(true);
        setSize(500, 500);

    }//end of main class
 
    //if button is clicked preform an action
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == playerInfo){
            controller.getInstance().changeCard("Upload Player Info");
        }
        else if(e.getSource() == bracket){
           controller.getInstance().changeCard("View Bracket");
        }
        else if(e.getSource() == weekly){
            controller.getInstance().changeCard("Weekly Matches");
        }
        else if(e.getSource() == exit){
            System.exit(0);
        }

    }//end of action preformed 

}//end of class 
