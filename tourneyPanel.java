import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class tourneyPanel extends JPanel implements ActionListener{

    private JButton back;

    public tourneyPanel(){

        JPanel main = new JPanel();

        //Creating Buttons
        back = new JButton("<-");
        back.addActionListener(this);
        back.setForeground(Color.white);
        back.setBackground(colorPalette.poolBlue);
        back.setOpaque(true);

        main.setBackground(colorPalette.defaultGrey);
        main.add(back);

        add(main);

    }//End of public tourneyPanel

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == back){
            controller.getInstance().changeCard("Homescreen");
        }

    }//End of actionPerformed


}//End of tourneyPanel class