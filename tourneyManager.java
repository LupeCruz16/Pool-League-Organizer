import java.util.Collection;
import java.util.HashMap;
import java.time.LocalDate;

public class tourneyManager {

    //contains tournament schedule
    static HashMap<LocalDate, tourneySchduleObject> schedule = new HashMap<LocalDate, tourneySchduleObject>();
    
    //will find possible matches and add them into the Challonge API as found
    public static void matchGeneration(){
        boolean matchFound;//boolean used to break out of for loop when match was found

        LocalDate[] dates = schedule.keySet().toArray(new LocalDate[schedule.size()]);

        for(int i = 0; i < pdfManager.matches.size(); i++){//itterates through unique array list of matches
            matchObject match = pdfManager.matches.get(i);//obtaining a single match that contains two player names

            matchFound = false;//initialize to false
            for(int j = 0; !matchFound && j < playerObject.DAYS; j++){//itterates through all of the players availability
                LocalDate start = dates[j];//contains all days that are playable in the tournament and calls them one at a time

                //p1a1 stands for player 1 time availability 1 or start time
                int p1a1 = utility.getAvailTime(pdfManager.players.get(match.getPlayer1()).getAvail(j), true);
                int p1a2 = utility.getAvailTime(pdfManager.players.get(match.getPlayer1()).getAvail(j), false);

                int p2a1 = utility.getAvailTime(pdfManager.players.get(match.getPlayer2()).getAvail(j), true);
                int p2a2 = utility.getAvailTime(pdfManager.players.get(match.getPlayer2()).getAvail(j), false);

                if(p1a1 > 0 && p2a1 > 0){//if either player is available that day try to generate a match

                    //checking to ensure that another match can be added to each players match counter
                    if(pdfManager.players.get(match.getPlayer1()).getMatchCounter() < playerObject.MAX_MATCHES &&
                    pdfManager.players.get(match.getPlayer2()).getMatchCounter() < playerObject.MAX_MATCHES){

                        //if a match was generated between the players move to next player
                        if(utility.matchValidity(start, j, match.getPlayer1(), p1a1, p1a2, match.getPlayer2(), p2a1, p2a2)){

                            //updating the amount of matches that each player has scheduled
                            pdfManager.players.get(match.getPlayer1()).incrementMatchCounter();
                            pdfManager.players.get(match.getPlayer2()).incrementMatchCounter();
                            matchFound = true;//set boolean to true as match was generated

                        }
                    }
                }
            }
            
            resetAvail();//reset each players availability for further match generation
        }

    }//end of match generation

    //resets players availabilities
    private static void resetAvail(){
        Collection<playerObject> values = pdfManager.players.values();//obtains all players stored within the HashMap of players
        
        for(playerObject player : values){//itterates through each player
          
            for(int i = 0; i < playerObject.DAYS; i++){//itterates through all days of the players availability
                player.setAvail(i, player.getFinalAvail(i));//resetting to original military converted time
            }

        }
    }//end of reset availability

}//end of tourneyManager
