import java.util.HashMap;
import java.time.DayOfWeek;
import java.time.Duration;
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
     * After this is done the method uses Duration to account for a matches length and a grace period to avoid back to back 
     * matches. Obtains the players availability and determines if there is time for a match to be generated and adds that 
     * time to each players generated match times for the day it was found in. 
     */
    public static void matchTimeGeneration(){
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

                        //checking to make sure that their max weekly matches have not been met
                        if(pdfManager.players.get(p1).getSingleDayNumOfMatches(j) < enteredInfo.getMaxWeeklyMatches() &&
                        pdfManager.players.get(p2).getSingleDayNumOfMatches(j) < enteredInfo.getMaxWeeklyMatches()){

                            //checking to ensure that another match can be added to each players match counter
                            if(pdfManager.players.get(p1).getMatchCounter() <= playerObject.MAX_MATCHES &&
                            pdfManager.players.get(p2).getMatchCounter() <= playerObject.MAX_MATCHES){

                                //adding the duration of each match into a duration variable also accounting for a grace period
                                Duration matchDuration = Duration.ofMinutes(20);
                                Duration gracePeriod = Duration.ofMinutes(10);

                                //finding overlapping time to schedule a match
                                LocalTime availStart = p1a1.isAfter(p2a1) ? p1a1 : p2a1;
                                LocalTime availEnd = p1a2.isBefore(p2a2) ? p1a2 : p2a2;

                                //checking if there is enough time for the match including a grace period 
                                if(availStart.plus(matchDuration).plus(gracePeriod).isBefore(availEnd)){
                                    LocalTime matchStart = availStart;//will be used for match scheudling 
                                    match.setTime(matchStart);//setting the match objects time to found start

                                    //adding the time into the players possible matches
                                    pdfManager.players.get(p1).addGeneratedMatch(j, matchStart);
                                    pdfManager.players.get(p2).addGeneratedMatch(j, matchStart);

                                    //updating the amount of matches that each player has scheduled
                                    pdfManager.players.get(p1).incrementMatchCounter();
                                    pdfManager.players.get(p2).incrementMatchCounter();

                                    scheduleMatchGeneration(match, utility.dayOfWeekMatchFound(j));

                                    pdfManager.matchDeletion(p1, p2);//removing that pairings match from possible matches array list 
                                    matchFound = true;//set boolean to true as match was generated
                                    
                                }
                                   
                            }
                        }
                    }
                }

        }
    }

    /**
     * In progress 
     */
    public static void scheduleMatchGeneration(matchObject match, DayOfWeek weekDay){

        LocalDate[] dates = schedule.keySet().toArray(new LocalDate[schedule.size()]);//obtaining schedule days 
        boolean matchAdded = false;//used to break out of for loop when match was sucessfully added into the schedules day

        for(int i = 0; !matchAdded && dates[i].getDayOfWeek() != weekDay && i < dates.length; i ++){//itterates through all playable days on a weekly basis

            LocalDate date = dates[i];//contains the specific date within the array
            match.getTime();//if time exists in current day and is > 3 dont add 

            //if dates week day is the same day that the match was scheduled within the players availability 
            if(date.getDayOfWeek() == weekDay){
                if(schedule.get(date).getMatch(0).getTime() == LocalTime.MIN){//if current day has no real match scheduled
                    schedule.get(date).removeMatch(0);//first remove the match from the array list
                    schedule.get(date).addMatch(match);//add the new match into the array list
    
                } else {
                    //if today has more room for matches
                    if(schedule.get(date).getTodaysMatches() < enteredInfo.getMaxDailyMatches()){
                        schedule.get(date).addMatch(match);
                     
                    } else { //if the day has no more room move to the next weeks same day of the week

                    }
                    
                }
                matchAdded = true;//breaking out of for loop
            }

        }

    }

    /**
     * Displays the tournament schedule. Itterates through each day of the schedule and displays the matches for that day
     */
    public static void displayTourneySchedule(){
        LocalDate[] dates = schedule.keySet().toArray(new LocalDate[schedule.size()]);//obtaining schedule days

        for(int i = 0; i < schedule.size(); i++){
            schedule.get(dates[i]).displayMatches();
        }
    }

}//end of tourneyManager
