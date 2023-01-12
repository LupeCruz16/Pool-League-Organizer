import java.io.File;
import java.io.FileInputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class pdfManager {

    static HashMap<String, playerObject> players = new HashMap<String, playerObject>();//used to store all players
    static ArrayList<matchObject> matches = new ArrayList<>();//used to generate matches

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

                extractInfo(docText);//extracts and stores all player information into their objects

                militaryConv();//converts all stored players availability into military time
                matchPairGen();//generates a unique list of pair players 
                matchDayGen();//generates an array list of playable days in the tournament, excluding weekends

                tourneyManager.matchGeneration();//generate matches for all players

                debug.dislpayWeeklyMatches();

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
            name = parts[0];
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
            utility.modifyTime(player, 0);
            utility.modifyTime(player, 1);
            utility.modifyTime(player, 2);
            utility.modifyTime(player, 3);
            utility.modifyTime(player, 4);

        }
    }//end of military conversion

    //generates a unique array list of match objects that stores players in pairs to verify if a match can be made
    private static void matchPairGen(){
        
        String[] names = players.keySet().toArray(new String[players.size()]);//creates an array of strings with players names

        for(int i = 0; i < players.size(); i++){//itterates through the entire player HashMap
            for(int j = i + 1; j < players.size(); j++){//goes through the following player after i 
                matches.add(new matchObject(names[i], names[j]));//adding the pair into the match object
            }
        }

    }//end of military conversion

    //deletes a match from matches array list to show that a match has been made from the unique list of pairings
    public static void matchDeletion(String player1, String player2){

        //if both names are found within the array list of matches then remove the match
        matches.removeIf(match -> (match.getPlayer1().equals(player1) && match.getPlayer2().equals(player2)) ||
        (match.getPlayer1().equals(player2) && match.getPlayer1().equals(player1)));
    }//end of match deletion

    //will generate all possible days that matches can be held 
    public static void matchDayGen(){//add after LocalDate startDay, LocalDate endDay

        //generating matches for the given length of the tournament 
        LocalDate startDay = enteredInfo.STAR_DATE;
        LocalDate endDay = enteredInfo.END_DATE;

        while(!startDay.isAfter(endDay)){//while not at the final day of the tournament

            //if one of the days from the start day is not on the weekend add to match days array list
            if(startDay.getDayOfWeek() != DayOfWeek.SATURDAY && 
               startDay.getDayOfWeek() != DayOfWeek.SUNDAY){

                //adding the days available for scheduling into the tournament manager schedule with an empty tourneyScheduleObject
                tourneyManager.schedule.put(startDay, new tourneySchduleObject(startDay, new matchObject()));

                startDay = startDay.plusDays(1);//itterates through to next day

            } else {//if it is a weekend then itterate to next day 
                startDay = startDay.plusDays(1);

            }
        }

    }//end of match days generation

}//end of pdfManager
