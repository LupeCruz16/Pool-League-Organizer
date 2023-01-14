
import javax.swing.*;
import java.awt.*;

public class uiDesign extends JPanel{



    public static void formatButton(JButton button){

        //Sets up a default button layout
        button.setMaximumSize(new Dimension(300, 35));
        button.setAlignmentX(Component.RIGHT_ALIGNMENT);
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setForeground(Color.white);
        button.setBackground(colorPalette.poolBlue);
        button.setOpaque(true);
    }

    public static void spacer(JPanel panel, int x, int y){

        //Adds a spacer to a panel
        panel.add(Box.createRigidArea(new Dimension(x, y)));

    }
    

}//End of uiDesign class
