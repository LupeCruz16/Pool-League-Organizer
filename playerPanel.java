import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class playerPanel extends JPanel implements ActionListener{
    
    private JButton back, upload;
    private JLabel tourneyDay, tourneyWeeks;
    private JTextArea daysField, weeksField;

    public playerPanel(){

        JPanel main = new JPanel();
        JPanel day = new JPanel();
        JPanel week = new JPanel();

        //creating buttons 
        back = new JButton("Back");
        back.addActionListener(this);
        uiDesign.formatButton(back);

        upload = new JButton("Upload PDF of Players");
        upload.addActionListener(this);
        uiDesign.formatButton(upload);

        //creating labels
        tourneyDay = new JLabel("What day will the tournament begin: ");
        tourneyDay.setFont(new Font("Arial", Font.PLAIN, 15));
        tourneyDay.setForeground(Color.white);

        tourneyWeeks = new JLabel("How many weeks will the tournament last: ");
        tourneyWeeks.setFont(new Font("Arial", Font.PLAIN, 15));
        tourneyWeeks.setForeground(Color.white);

        //creating text areas 
        daysField = new JTextArea();
        daysField.setBackground(Color.GRAY);
        daysField.setPreferredSize(new Dimension(100, 20));

        weeksField = new JTextArea();
        weeksField.setBackground(Color.GRAY);
        weeksField.setPreferredSize(new Dimension(100, 20));


        //adding to the panel
        setBackground(colorPalette.defaultGrey);
        day.setBackground(colorPalette.defaultGrey);
        day.add(tourneyDay);
        day.add(daysField);

        week.setBackground(colorPalette.defaultGrey);
        week.add(tourneyWeeks);
        week.add(weeksField);

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        
        main.setBackground(colorPalette.defaultGrey);

        uiDesign.spacer(main,60,60);
        main.add(upload);
        upload.setAlignmentX(Component.CENTER_ALIGNMENT);

        main.add(day);
        main.add(week);

        main.add(back);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(main);
    }

    public void actionPerformed(ActionEvent e){

        homePanel home = controller.getInstance().getHomePanel();
        JPanel mainPanel = home.getMainPanel();

        if(e.getSource() == upload){
            pdfManager.readInFiles();
            
        }
        else if(e.getSource() == back){
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "blank panel");
        }

    }//end of action performed 

}//end of playerPanel class

