import java.io.File;
import java.io.FileInputStream;
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
                matches.add(new matchObject(names[i], names[j], 0, 0));//adding the pair into the match object
            }
        }

    }//end of military conversion

    //will find possible matches and add them into the Challonge API as found
    private static void matchGeneration(){
        boolean matchFound;//boolean used to break out of for loop when match was found

        for(int i = 0; i < matches.size(); i++){//itterates through unique array list of matches
            matchObject match = matches.get(i);//obtaining a single match that contains two player names
            
            matchFound = false;//initialize to false
            for(int j = 0; !matchFound && j < playerObject.DAYS; j++){//itterates through all of the players availability

                //p1a1 stands for player 1 time availability 1 or start time
                int p1a1 = utility.getAvailTime(players.get(match.getPlayer1()).getAvail(j), true);
                int p1a2 = utility.getAvailTime(players.get(match.getPlayer1()).getAvail(j), false);

                int p2a1 = utility.getAvailTime(players.get(match.getPlayer2()).getAvail(j), true);
                int p2a2 = utility.getAvailTime(players.get(match.getPlayer2()).getAvail(j), false);

                if(p1a1 > 0 && p2a1 > 0){//if either player is available that day try to generate a match

                    //checking to ensure that another match can be added to each players match counter
                    if(players.get(match.getPlayer1()).getMatchCounter() < playerObject.MAX_MATCHES &&
                       players.get(match.getPlayer2()).getMatchCounter() < playerObject.MAX_MATCHES){

                        //if a match was generated between the players move to next player
                        if(utility.matchValidity(j, match.getPlayer1(), p1a1, p1a2, match.getPlayer2(), p2a1, p2a2)){

                            //System.out.println(players.get(match.getPlayer1()).getName() + players.get(match.getPlayer1()).getAvail(j));

                            //updating the amount of matches that each player has scheduled
                            players.get(match.getPlayer1()).incrementMatchCounter();
                            players.get(match.getPlayer2()).incrementMatchCounter();
                            matchFound = true;//set boolean to true as match was generated

                        }
                    }
                }
            }
        }

    }//end of match generation

    //resets players availabilities
    private static void resetAvail(){
        Collection<playerObject> values = players.values();//obtains all players stored within the HashMap of players
        
        for(playerObject player : values){//itterates through each player
          
            for(int i = 0; i < playerObject.DAYS; i++){//itterates through all days of the players availability
                player.setAvail(i, player.getFinalAvail(i));//resetting to original military converted time
            }

        }
    }//end of reset availability

}//end of pdfManager
