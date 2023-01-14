import java.util.HashMap;
import java.time.LocalDate;
import java.time.LocalTime;

public class tourneyManager {

    //contains tournament schedule
    static HashMap<LocalDate, tourneySchduleObject> schedule = new HashMap<LocalDate, tourneySchduleObject>();

    //will find possible matches and add them into the Challonge API as found
    public static void matchGeneration(){
        boolean matchFound;//boolean used to break out of for loop when match was found

        for(int i = 0; i < pdfManager.matches.size(); i++){//itterates through unique array list of matches
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

                            //checking to ensure that the players weekly match limit has not been reached
                            //if(pdfManager.players.get(p1).getWeeklyMatchCoun() < enteredInfo.getMaxWeeklyMatches() &&
                            //pdfManager.players.get(p2).getWeeklyMatchCoun() < enteredInfo.getMaxWeeklyMatches()){

                                //if a match was generated between the players move to next player
                                if(utility.matchValidity(j, p1, p1a1, p1a2, p2, p2a1, p2a2)){

                                    //updating the amount of matches that each player has scheduled
                                    pdfManager.players.get(p1).incrementMatchCounter();
                                    pdfManager.players.get(p2).incrementMatchCounter();

                                    //updating their weekly match counter
                                    //pdfManager.players.get(p1).incrementWeeklyMatchCoun();
                                    //pdfManager.players.get(p2).incrementWeeklyMatchCoun();

                                    pdfManager.matchDeletion(p1, p2);//removing that pairings match from possible matches array list 

                                    matchFound = true;//set boolean to true as match was generated
                                }
                            //}
                        }
                    }
                }

        }

    }//end of match generation

}//end of tourneyManager
