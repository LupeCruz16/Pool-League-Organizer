import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class tourneyPanel extends JPanel implements ActionListener{

    private JButton back;

    public tourneyPanel(){

        JPanel main = new JPanel();
        setBackground(colorPalette.defaultGrey);

        //Creating Buttons
        back = new JButton("Back");
        back.addActionListener(this);
        uiDesign.formatButton(back);

        main.setBackground(colorPalette.defaultGrey);
        main.add(back);

        add(main);

    }//End of public tourneyPanel

    public void actionPerformed(ActionEvent e){

        homePanel home = controller.getInstance().getHomePanel();
        JPanel mainPanel = home.getMainPanel();

        if(e.getSource() == back){
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "blank panel");
        }

    }//End of actionPerformed


}//End of tourneyPanel class