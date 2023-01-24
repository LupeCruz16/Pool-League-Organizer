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
                String p1 = match.getP1().getName(); 
                String p2 = match.getP2().getName();

                matchFound = false;//initialize to false

                for(int j = 0; !matchFound && j < playerObject.DAYS; j++){//itterates through all of the players availability

                    //p1a1 stands for player 1 time availability 1 or start time
                    LocalTime p1a1 = pdfManager.players.get(p1).getStartAvail(j);
                    //LocalTime p1a2 = pdfManager.players.get(p1).getEndAvail(j);

                    LocalTime p2a1 = pdfManager.players.get(p2).getStartAvail(j);
                    //LocalTime p2a2 = pdfManager.players.get(p2).getEndAvail(j);

                    if(p1a1 != LocalTime.MIN && p2a1 != LocalTime.MIN){//if either player is available that day try to generate a match

                        //checking to make sure that their max weekly matches have not been met
                        if(pdfManager.players.get(p1).getSingleDayNumOfMatches(j) < adminInfo.getMaxWeeklyMatches() &&
                        pdfManager.players.get(p2).getSingleDayNumOfMatches(j) < adminInfo.getMaxWeeklyMatches()){

                            //checking to ensure that another match can be added to each players match counter
                            if(pdfManager.players.get(p1).getMatchCounter() <= playerObject.MAX_MATCHES &&
                            pdfManager.players.get(p2).getMatchCounter() <= playerObject.MAX_MATCHES){

                                boolean timeFound = timeValidity(match, j);
                                if(timeFound){
                                    pdfManager.matchDeletion(p1, p2);//removing that pairings match from possible matches array list 
                                    matchFound = true;
                                }
                                   
                            }
                        }
                    }
                }

        }
    }

    /**
     * Time validity checks and ensures that both players availability line up and have enough space for another match
     * @param match match object containing match info as well as each players info
     * @param day day the players availability could have a match
     * @return true if the match can be generated
     */
    public static boolean timeValidity(matchObject match, int day){
        
        //obtaining the players names 
        String p1 = match.getP1().getName();
        String p2 = match.getP2().getName();

        //obtaining players availability 
        LocalTime p1a1 = match.getP1().getStartAvail(day);
        LocalTime p1a2 = match.getP1().getEndAvail(day);

        LocalTime p2a1 = match.getP2().getStartAvail(day);
        LocalTime p2a2 = match.getP2().getEndAvail(day);

        //adding the duration of each match into a duration variable also accounting for a grace period
        Duration matchDuration = adminInfo.MATCH_DURATION;
        Duration gracePeriod = adminInfo.GRACE_PERIOD;

        //finding overlapping time to schedule a match
        LocalTime availStart = p1a1.isAfter(p2a1) ? p1a1 : p2a1;
        LocalTime availEnd = p1a2.isBefore(p2a2) ? p1a2 : p2a2;

        //checking if there is enough time for the match including a grace period 
        if(availStart.plus(matchDuration).plus(gracePeriod).isBefore(availEnd)){
            LocalTime matchStart = availStart;//will be used for match scheudling 
            match.setTime(matchStart);//setting the match objects time to found start

            //adding the time into the players possible matches
            //pdfManager.players.get(p1).addGeneratedMatch(day, matchStart);
            //pdfManager.players.get(p2).addGeneratedMatch(day, matchStart);

            //updating the amount of matches that each player has scheduled
            pdfManager.players.get(p1).incrementMatchCounter();
            pdfManager.players.get(p2).incrementMatchCounter();

            scheduleMatchGeneration(match, utility.dayOfWeekMatchFound(day));

            //pdfManager.matchDeletion(p1, p2);//removing that pairings match from possible matches array list 
            return true;//set boolean to true as match was generated
            
        }
        return false;
    }

    /**
     * In progress 
     */
    public static void scheduleMatchGeneration(matchObject match, DayOfWeek weekDay){

        LocalDate[] dates = schedule.keySet().toArray(new LocalDate[schedule.size()]);//obtaining schedule days 
        boolean matchAdded = false;//used to break out of for loop when match was sucessfully added into the schedules day

        for(int i = 0; !matchAdded && i < dates.length; i++){//iterates through entire array of dates until match is added
            LocalDate date = dates[i];//obtains date

            //if dates week day is the same day that the match was scheduled within the players availability 
            if(date.getDayOfWeek() == weekDay){

                //if current day has no real match scheduled
                if(schedule.get(date).getDayMatchesSize() == 0){
                    //schedule.get(date).removeMatch(0);//first remove the match from the array list
                    schedule.get(date).addMatch(match);//add the new match into the array list
                    matchAdded = true;//breaking out of for loop

                } else {//if there are matches in the current date
     
                    //account for player overlap
                    if(schedule.get(date).verifyMatch(match)){
                        schedule.get(date).addMatch(match);//add the match to the date 
                        matchAdded = true;//breaking out of for loop
                    }
                }
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
