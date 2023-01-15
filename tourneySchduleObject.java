import java.time.LocalDate;
import java.util.ArrayList;

public class tourneySchduleObject {

    private LocalDate scheduledDay;//holds day for given matches
    private static ArrayList<matchObject> dayMatches = new ArrayList<>();//holds all matches for a given day
    private int todaysMatches;//keeps track of the amount of matches in one day

    /**
     * Class constructor used to keep track of the matches generated for a day under one single object
     * 
     * @param scheduledDay Will maintain the day the match will be added into
     * @param match Will collect the match information when added in 
     * 
     */
    public tourneySchduleObject(LocalDate scheduledDay, matchObject match){

       this.scheduledDay = scheduledDay;
       this.todaysMatches = 0;
       dayMatches.add(todaysMatches, match);

    }

    /**
     * Returns day of match schedule
     * @return scheduled day
     */
    public LocalDate getScheduledDay(){
       return scheduledDay;
    }

    /**
     * Obtains scheduled day for matches to be held
     * @param scheduledDay Contains matches day
     */
    public void setScheduledDay(LocalDate scheduledDay){
       this.scheduledDay = scheduledDay;
    }

    /**
     * Returns total matches for a given day 
     * @return todays matches 
     */
    public int getTodaysMatches(){
        return todaysMatches;
    }

    /**
     * Appends a match to the objects list of matches for the given day
     * @param match Containd the new matches information
     */
    public void addMatch(matchObject match){
        dayMatches.add(todaysMatches++, match);
    }

    /**
     * Displays all matches for the given day of the object
     */
    public void displayMatches(){

        if(todaysMatches > 0){//if matches were generated and added for today
            System.out.println("On: " + scheduledDay.toString());//printing the day of the matches

            for(int i = 0; i < todaysMatches; i++){//itterate through all generated matches

                System.out.println(dayMatches.get(i).getPlayer1() + " will play againt " + dayMatches.get(i).getPlayer2() + 
                " at " + dayMatches.get(i).getTime());
            }

        } else {
            System.out.println("There are no matches on: " + scheduledDay.toString());
        }
    }

}//end of tournament schedule object
