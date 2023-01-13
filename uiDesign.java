import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class uiDesign extends JPanel{



    public static void formatButton(JButton button){

        //Sets up a default button layout
        button.setMaximumSize(new Dimension(300, 35));
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setForeground(Color.white);
        button.setBackground(colorPalette.poolBlue);
        button.setOpaque(true);
    }

    public static void spacer(JPanel panel){

        //Adds a spacer to a panel
        panel.add(Box.createRigidArea(new Dimension(20, 20)));
    }
    

}//End of uiDesign class
