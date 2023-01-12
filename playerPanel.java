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
        back = new JButton("<-");
        back.addActionListener(this);
        back.setForeground(colorPalette.poolBlue);

        upload = new JButton("Upload PDF of Players");
        upload.addActionListener(this);
        upload.setForeground(colorPalette.poolBlue);

        //creating labels
        tourneyDay = new JLabel("What day will the tournament begin: ");
        tourneyDay.setFont(new Font("Arial", Font.PLAIN, 15));
        tourneyDay.setForeground(colorPalette.poolBlue);

        tourneyWeeks = new JLabel("How many weeks will the tournament last: ");
        tourneyWeeks.setFont(new Font("Arial", Font.PLAIN, 15));
        tourneyWeeks.setForeground(colorPalette.poolBlue);

        //creating text areas 
        daysField = new JTextArea();
        daysField.setPreferredSize(new Dimension(100, 20));

        weeksField = new JTextArea();
        weeksField.setPreferredSize(new Dimension(100, 20));

        day.add(tourneyDay);
        day.add(daysField);

        week.add(tourneyWeeks);
        week.add(weeksField);

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(upload);

        main.add(day);
        main.add(week);

        main.add(back);
        add(main);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == upload){
            pdfManager.readInFiles();
            
        }
        else if(e.getSource() == back){
            controller.getInstance().changeCard("Homescreen");

        }

    }//end of action performed 

}//end of playerPanel class

