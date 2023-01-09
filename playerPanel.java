import java.awt.event.*;
import javax.swing.*;

public class playerPanel extends JPanel implements ActionListener{
    
    private JButton back, upload;

    public playerPanel(){

        JPanel main = new JPanel();

        //creating buttons 
        back = new JButton("<-");
        back.addActionListener(this);
        back.setForeground(colorPalette.poolBlue);

        upload = new JButton("Upload PDF of Players");
        upload.addActionListener(this);
        upload.setForeground(colorPalette.poolBlue);

        main.add(back);
        main.add(upload);

        add(main);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == upload){
            pdfManager.readInFiles();
            
        }
        else if(e.getSource() == back){
            controller.getInstance().changeCard("Homescreen");

            //tourneyManager.createTournament();
        }

    }//end of action performed 

}//end of playerPanel class

