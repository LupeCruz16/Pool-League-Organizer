import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.time.Duration;
import java.time.LocalTime;

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

                extractInfo(docText);//extracts and stores all player information into their objects
                militaryConv();//converts all stored players availability into military time

                matchGeneration();//generate matches for all players

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
            utility.modifyTime(player, 0);
            utility.modifyTime(player, 1);
            utility.modifyTime(player, 2);
            utility.modifyTime(player, 3);
            utility.modifyTime(player, 4);

        }
    }//end of military conversion

    //will find possible matches and add them into the Challonge API as found
    private static void matchGeneration(){

        for(Entry<String, playerObject> entry1 : players.entrySet()){//itterates through HashMap with one player
            playerObject player1 = entry1.getValue();//getting one player first
            String name1 = player1.getName();//obtaining player1s name

            for(Entry<String, playerObject> entry2 : players.entrySet()){//itterates with following player
                playerObject player2 = entry2.getValue();//getting second player
                String name2 = player2.getName();//obtaining player2s name

                if(!player1.equals(player2)){//aoiding comparing the same player
                    
                    for(int i = 0; i < playerObject.DAYS; i++){//itterates through all of the players availability

                        //p1a1 stands for player 1 time availability 1 or start time
                        int p1a1 = utility.getAvailTime(player1.getAvail(i), true);
                        int p1a2 = utility.getAvailTime(player1.getAvail(i), false);

                        int p2a1 = utility.getAvailTime(player2.getAvail(i), true);
                        int p2a2 = utility.getAvailTime(player2.getAvail(i), false);

                        if(p1a1 > 0 && p2a1 > 0){//if either player is available that day try to generate a match
                            //if a match was generated between the players move to next player
                            if(matchValidity(i, name1, p1a1, p1a2, name2, p2a1, p2a2)){
                                i = playerObject.DAYS;//break out of for loop
                            }

                        }

                    }
                }
            }
        }
    }//end of match generation

    public static boolean matchValidity(int day, String name1, int p1a1, int p1a2, String name2, int p2a1, int p2a2){

        //seperating the military time conversion into hours and minutes for easier comparision 
        LocalTime player1Start = LocalTime.of(p1a1 / 100, p1a1 % 100);
        LocalTime player1End = LocalTime.of(p1a2 / 100, p1a2 % 100);

        LocalTime player2Start = LocalTime.of(p2a1 / 100, p2a1 % 100);
        LocalTime player2End = LocalTime.of(p2a2 / 100, p2a2 % 100);

        //adding the duration of each match into a duration variable also accounting for a grace period
        Duration matchDuration = Duration.ofMinutes(20);
        Duration gracePeriod = Duration.ofMinutes(10);

        //finding overlapping time to schedule a match
        LocalTime availStart = player1Start.isAfter(player2Start) ? player1Start : player2Start;
        LocalTime availEnd = player1End.isBefore(player2End) ? player1End : player2End;

        //checking if there is enough time for the match
        if(availStart.plus(matchDuration).isBefore(availEnd)){
            LocalTime matchStart = availStart;//will be used for match scheudling 
            System.out.println("A match can be scheduled at " + matchStart + " on " + day + " between " + name1 + " and " + name2);

            //figuring out when the mach will be over for other matches to be held
            LocalTime matchEnd = matchStart.plus(matchDuration).plus(gracePeriod);
            
            //removing the colon from matchEnds "13:30" to be military time as "1330"
            String newStart = matchEnd.toString().replace(":", "");

            //modifying players times to be changed incase other matched can be held
            players.get(name1).setAvail(day, newStart + "-" + p1a2);
            players.get(name2).setAvail(day, newStart + "-" + p2a2);

            return true;
        }
        return false;

    }//end of match validity

}//end of pdfManager
