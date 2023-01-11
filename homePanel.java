
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class homePanel extends JPanel implements ActionListener{
    
    private JButton playerInfo, bracket;
    private JLabel appName, blank, option, tab;

    public homePanel(){

        //Creating Panels
        JPanel main = new JPanel();
        JPanel prompt = new JPanel();
        JPanel space = new JPanel();
        JPanel space2 = new JPanel();
        JPanel title = new JPanel();
        JPanel middle = new JPanel();
        JPanel button = new JPanel();

        //Creating buttoms
        playerInfo = new JButton("Upload Players");//creating uploading photos button 
        playerInfo.addActionListener(this);//monitor if clicked 
        playerInfo.setPreferredSize(new Dimension(150, 35));
        playerInfo.setForeground(colorPalette.poolBlue);

        bracket = new JButton("View Tournament Bracket");
        bracket.addActionListener(this);
        bracket.setPreferredSize(new Dimension(200, 35));
        bracket.setForeground(colorPalette.poolBlue);

        //Creating Lables
        appName = new JLabel("Welcome To The League Manager");
        appName.setFont(new Font("Arial", Font.BOLD, 40));//resizing text within label
        appName.setForeground(Color.white);

        option = new JLabel("Please choose an option");
        option.setFont(new Font("Arial", Font.PLAIN, 15));//resizing text within label
        option.setForeground(Color.white);

        tab = new JLabel("    ");

        blank = new JLabel("");

        //Adding elements to panels 
        space.add(blank);
        space.setPreferredSize(new Dimension(820, 15));
        space.setBackground(colorPalette.poolBlue);

        title.add(appName);
        title.setPreferredSize(new Dimension(820, 65));
        title.setBackground(colorPalette.poolBlue);

        prompt.add(option);
        prompt.setPreferredSize(new Dimension(820, 30));
        prompt.setBackground(colorPalette.poolBlue);

        middle.add(playerInfo);
        middle.add(tab);
        middle.add(bracket); 

        space2.add(blank);

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

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
            
        }

    }//end of action preformed 

}//end of class 
