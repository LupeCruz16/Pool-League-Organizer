import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper;

import java.util.Collection;
import java.util.HashMap;

public class pdfManager {

    private static HashMap<String, playerObject> players = new HashMap<String, playerObject>();

    //user selecting files from device 
    public static void readInFiles(){

        JFileChooser fileUpload = new JFileChooser();//creating file chooser
        fileUpload = new JFileChooser();//creating file chooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
        fileUpload.setFileFilter(filter);//applying pdf filter to files uploaded

        int response = fileUpload.showOpenDialog(null);//saves users device for file selection

        if(response == JFileChooser.APPROVE_OPTION){//make sure file selecteds path is retrieved
        
            try{
                File file = fileUpload.getSelectedFile();
                
                FileInputStream fis = new FileInputStream(file.getAbsolutePath());//create new input stream
                PDDocument pdfDocument = PDDocument.load(fis);//load in pdf document 
                PDFTextStripper pdfTextStripper = new PDFTextStripper();//obtain text
                String docText = pdfTextStripper.getText(pdfDocument);//turning text into string 

                System.out.println(docText);

                extractInfo(docText);
                militaryConv();

                pdfDocument.close();//closing document
                fis.close();//closing file input stream
                
            } catch(java.io.IOException ex){//catching exception thrown for invalid document inputs
                System.out.println("File cannot be opened: " + ex);//printing error message 
            } 
        }
    }//end of read in files

    //obtains each players information from a pdf of the pool league roster
    private static void extractInfo(String doc){

        String [] lines = doc.split("\n");//obtains every new lines in the document text
        String name, email, mon, tues, wed, thur, fri;//strings for collecting information from each player

        for(String line : lines){//itterates through every line in document 

            String[] parts = line.split(",");//splits the lines based on commas

            //sections everything out
            name = parts[0].substring(1);
            email = parts[1].substring(1);
            mon = parts[2].substring(1);
            tues = parts[3].substring(1);
            wed = parts[4].substring(1);
            thur = parts[5].substring(1);
            fri = parts[6].substring(1);

            //adding all information to players HashMap
            players.put(name, new playerObject(name, email, mon, tues, wed, thur, fri));
            
        }

    }//end of extract info

    //converts all players availaility into military time
    private static void militaryConv(){
        Collection<playerObject> values = players.values();//obtains all players stored within the HashMap of players
        
        for(playerObject player : values){//itterates through each player
            //changes the collected time into military time
            modifyTime(player, 0);
            modifyTime(player, 1);
            modifyTime(player, 2);
            modifyTime(player, 3);
            modifyTime(player, 4);

            System.out.println(player.getAvail(0));
        }
    }//end of military conversion

    //used to change the players availability to military time for easier comparisions and match making
    private static void modifyTime(playerObject player, int day){

        //playerObject player = players.get(id);//creating an instance of playerObject
        String avail = player.getAvail(day);//storing the availability of the day into a string

        String firstTime, secTime;//store the final times of the availabilities given
        int firstDigit, secDigit, finalTime, minutes;//used to store integer values of string
        int dash = avail.indexOf("-");//obtain the dash location for second end time

        if(dash == -1){//if no time available for that given day ex "N/A"
            player.setAvail(day, "0");//setting that days availability to 0 
        }
        else if(Character.isDigit(avail.charAt(0))){//if first character of player availability is an integer
            firstDigit = Integer.parseInt(avail.substring(0, 1));//converts character into an integer

            if(Character.isDigit(avail.charAt(1))){//if there is a second integer character followed 
                secDigit = Integer.parseInt(avail.substring(1, 2));//converting second digit into an integer
                minutes = utility.minutesFinder(avail, dash, true);//if first time has a colon in the time such as "12:30"

                finalTime = (firstDigit * 10) + secDigit;//converts time into double digits
                firstTime = Integer.toString((finalTime * 100) + minutes);//converts time into military time
                
            } else {//only one digit for the first time availability
                minutes = utility.minutesFinder(avail, dash, true);//if this single digit time has a colon such as "2:30"

                firstTime = Integer.toString((firstDigit * 100) + 1200 + minutes);//converts time into military time
            }

            //checking the availability after the dash
            if(Character.isDigit(avail.charAt(dash + 1))){//if first character of player availability is an integer
                firstDigit = Integer.parseInt(avail.substring(dash + 1, dash + 2));//converts character into an integer

                if(Character.isDigit(avail.charAt(dash + 2))){//if there is a second integer character followed 
                    secDigit = Integer.parseInt(avail.substring(dash + 2, dash + 3));//converting second digit into an integer
                    minutes = utility.minutesFinder(avail, dash, false);//if first time has a colon in the time such as "12:30"

                    finalTime = (firstDigit * 10) + secDigit;//converts time into double digits
                    secTime = Integer.toString(finalTime * 100 + minutes);//converts time into military time
                    
                } else {//only one digit for the first time availability
                    minutes = utility.minutesFinder(avail, dash, false);//if first time has a colon in the time such as "12:30"

                    secTime = Integer.toString((firstDigit * 100) + 1200 + minutes);//converts time into military time
                }

                player.setAvail(day, firstTime + "-" + secTime);//setting player availability to new military time converted time

            }else {
                JOptionPane.showMessageDialog(null, "Invalid PDF Format. Please Enter a valid PDF");
            }

        } else{
            JOptionPane.showMessageDialog(null, "Invalid PDF Format. Please Enter a valid PDF");
        }

    }//end of modify time


}//end of pdfManager
