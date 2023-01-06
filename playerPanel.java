import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import javax.swing.*;

//testing 
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

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
            try{
                Class<?> c = pdfManager.class;
                Object o = c.getDeclaredConstructor().newInstance();

                Method m = pdfManager.class.getDeclaredMethod("readInFiles");
                m.setAccessible(true);
                m.invoke(o);

            }catch(Exception ex){//catching exception thrown for invalid document inputs
                System.out.println("Exception thrown: " + ex);//printing error message 
            } 
        }
        else if(e.getSource() == back){
            //controller.getInstance().changeCard("Homescreen");

            try {
                URL url = new URL("https://LupeCruz:Lupescross1@api.challonge.com/v1/");

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }//end of action performed 

}//end of playerPanel class

