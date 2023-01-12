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

        for(int curDay = 0; curDay < dates.length; curDay++){//itterates through the days of the tournament
          
            for(int i = 0; i < pdfManager.matches.size(); i++){//itterates through unique array list of matches
                matchObject match = pdfManager.matches.get(i);//obtaining a single match that contains two player names
                String p1 = match.getPlayer1();//collecting players names into strings 
                String p2 = match.getPlayer2();

                matchFound = false;//initialize to false
                for(int j = 0; !matchFound && j < playerObject.DAYS; j++){//itterates through all of the players availability

                    //p1a1 stands for player 1 time availability 1 or start time
                    int p1a1 = utility.getAvailTime(pdfManager.players.get(p1).getAvail(j), true);
                    int p1a2 = utility.getAvailTime(pdfManager.players.get(p1).getAvail(j), false);

                    int p2a1 = utility.getAvailTime(pdfManager.players.get(p2).getAvail(j), true);
                    int p2a2 = utility.getAvailTime(pdfManager.players.get(p2).getAvail(j), false);

                    if(p1a1 > 0 && p2a1 > 0){//if either player is available that day try to generate a match

                        //checking to ensure that another match can be added to each players match counter
                        if(pdfManager.players.get(p1).getMatchCounter() <= playerObject.MAX_MATCHES &&
                        pdfManager.players.get(p2).getMatchCounter() <= playerObject.MAX_MATCHES){

                            //checking to ensure that the players weekly match limit has not been reached
                            if(pdfManager.players.get(p1).getWeeklyMatchCoun() < enteredInfo.getMaxWeeklyMatches() &&
                            pdfManager.players.get(p2).getWeeklyMatchCoun() < enteredInfo.getMaxWeeklyMatches()){

                                //if a match was generated between the players move to next player
                                if(utility.matchValidity(dates[curDay], j, p1, p1a1, p1a2, p2, p2a1, p2a2)){

                                    //updating the amount of matches that each player has scheduled
                                    pdfManager.players.get(p1).incrementMatchCounter();
                                    pdfManager.players.get(p2).incrementMatchCounter();

                                    //updating thier weekly match counter
                                    pdfManager.players.get(p1).incrementWeeklyMatchCoun();
                                    pdfManager.players.get(p2).incrementWeeklyMatchCoun();

                                    pdfManager.matchDeletion(p1, p2);//removing that pairings match from possible matches array list 

                                    matchFound = true;//set boolean to true as match was generated

                                }
                            }
                        }
                    }
                }
    
            }
            curDay += 5;//continues to next week fo match making
            resetAvail();//reset each players availability for further match generation
        }

    }//end of match generation

    ///Reminder///
    //have to itterate thorugh the playable days weekly and have the date continously loop with the player availability and then reset
    //until that weeks complete list of matches has been generated

    //resets players availabilities
    private static void resetAvail(){
        Collection<playerObject> values = pdfManager.players.values();//obtains all players stored within the HashMap of players
        
        for(playerObject player : values){//itterates through each player
          
            //System.out.println("Before time reset");
            //debug.displayPlayer(player.getName());

            for(int i = 0; i < playerObject.DAYS; i++){//itterates through all days of the players availability
                player.setAvail(i, player.getFinalAvail(i));//resetting to original military converted time
                player.resetWeeklyMatchCount();//resets the amount of matches they can play weekly to 0
            }

            //System.out.println("After time reset");
            //debug.displayPlayer(player.getName());

        }
    }//end of reset availability

}//end of tourneyManager
