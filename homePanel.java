
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class homePanel extends JPanel implements ActionListener{
    
    private JButton playerInfo, bracket, weekly, exit;
    private JLabel appName, blank, option;

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
        playerInfo = new JButton("Create a Tournament");//creating uploading photos button 
        playerInfo.addActionListener(this);//monitor if clicked 
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
        appName = new JLabel("Welcome To The League Manager");
        appName.setFont(new Font("Arial", Font.BOLD, 40));//resizing text within label
        appName.setForeground(Color.white);

        option = new JLabel("Please choose an option");
        option.setFont(new Font("Arial", Font.PLAIN, 25));//resizing text within label
        option.setForeground(Color.white);

        blank = new JLabel("");

        //Adding elements to panels 
        space.add(blank);
        space.setPreferredSize(new Dimension(820, 35));
        space.setBackground(colorPalette.poolBlue);
        space.setBackground(colorPalette.defaultGrey);

        title.add(appName);
        title.setPreferredSize(new Dimension(820, 65));
        title.setBackground(colorPalette.poolBlue);

        prompt.add(option);
        prompt.setPreferredSize(new Dimension(820, 45));
        prompt.setBackground(colorPalette.poolBlue);

        middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
        middle.setAlignmentX(Component.CENTER_ALIGNMENT);
        middle.setBackground(colorPalette.defaultGrey);

        uiDesign.spacer(middle);
        middle.add(playerInfo);
        uiDesign.spacer(middle);
        middle.add(bracket);
        uiDesign.spacer(middle);
        middle.add(weekly);
        uiDesign.spacer(middle);
        middle.add(exit);

        space2.setBackground(colorPalette.defaultGrey);
        uiDesign.spacer(space2);
        button.setBackground(colorPalette.defaultGrey);
        
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(colorPalette.defaultGrey);

        uiDesign.spacer(main);
        main.add(title);
        main.add(prompt);
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
