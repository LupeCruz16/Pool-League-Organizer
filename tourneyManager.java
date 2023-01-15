import java.util.HashMap;
import java.time.LocalDate;
import java.time.LocalTime;

public class tourneyManager {

    //contains tournament schedule
    static HashMap<LocalDate, tourneySchduleObject> schedule = new HashMap<LocalDate, tourneySchduleObject>();

    /**
     * Begins by itterating through array list of match pair generated from end to start. This prevents issues when removing
     * the unique pairing after the match has been generated. 
     * 
     * Then collects each players name and utilizes a boolean ot organically break the for loop that itterates through the
     * players availability. Once the loop is entered it obtains each players availability and acesses if a match can be made
     * 
     * After this is done, if matchVadlidity from utility.h is true then the players match counters are incremented and the loop
     * is terminated
     */
    public static void matchGeneration(){
        boolean matchFound;//boolean used to break out of for loop when match was found

        for(int i = pdfManager.matches.size() - 1; i >= 0 ; i--){//itterates through unique array list of matches in reverse
                matchObject match = pdfManager.matches.get(i);//obtaining a single match that contains two player names

                //collecting players names into strings
                String p1 = match.getPlayer1(); 
                String p2 = match.getPlayer2();

                matchFound = false;//initialize to false

                for(int j = 0; !matchFound && j < playerObject.DAYS; j++){//itterates through all of the players availability

                    //p1a1 stands for player 1 time availability 1 or start time
                    LocalTime p1a1 = pdfManager.players.get(p1).getStartAvail(j);
                    LocalTime p1a2 = pdfManager.players.get(p1).getEndAvail(j);

                    LocalTime p2a1 = pdfManager.players.get(p2).getStartAvail(j);
                    LocalTime p2a2 = pdfManager.players.get(p2).getEndAvail(j);

                    if(p1a1 != LocalTime.MIN && p2a1 != LocalTime.MIN){//if either player is available that day try to generate a match

                        //checking to ensure that another match can be added to each players match counter
                        if(pdfManager.players.get(p1).getMatchCounter() <= playerObject.MAX_MATCHES &&
                        pdfManager.players.get(p2).getMatchCounter() <= playerObject.MAX_MATCHES){

                            //if a match was generated between the players move to next player
                            if(utility.matchValidity(j, p1, p1a1, p1a2, p2, p2a1, p2a2)){

                                //updating the amount of matches that each player has scheduled
                                pdfManager.players.get(p1).incrementMatchCounter();
                                pdfManager.players.get(p2).incrementMatchCounter();

                                pdfManager.matchDeletion(p1, p2);//removing that pairings match from possible matches array list 
                                matchFound = true;//set boolean to true as match was generated
                            }
                        }
                    }
                }
        }
    }

}//end of tourneyManager
