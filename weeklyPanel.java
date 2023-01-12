import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class weeklyPanel extends JPanel implements ActionListener{
   
    private JButton back;

    public weeklyPanel(){

        JPanel main = new JPanel();

        //Creating Buttons
        back = new JButton("<-");
        back.addActionListener(this);
        back.setForeground(Color.white);
        back.setBackground(colorPalette.poolBlue);

        main.setBackground(colorPalette.defaultGrey);
        main.add(back);

        add(main);

    }//End of public tourneyPanel

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == back){
            controller.getInstance().changeCard("Homescreen");
        }

    }//End of actionPerformed

} //End weeklyPanel class