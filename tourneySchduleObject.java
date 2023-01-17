import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class tourneySchduleObject {

    private LocalDate scheduledDay;//holds day for given matches
    private ArrayList<matchObject> dayMatches = new ArrayList<>();//holds all matches for a given day
    private HashMap<LocalTime, Integer> matchTimes = new HashMap<>();//holds all times for the matches of the day
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
     * Returns match object from array list of the days matches
     * @param pos obtains the position of the match
     * @return match object
     */
    public matchObject getMatch(int pos){
        return dayMatches.get(pos);
    }

    /**
     * Removes a match from array list of day matches based on index 
     * @param pos index of match to be removed 
     */
    public void removeMatch(int pos){
        dayMatches.remove(pos);
    }

    /**
     * Will ensure a table is open given a certain time from the hashmap of matchTimes
     * @param time time to be either added or rejected
     * @return true or false if added or rejected
     */
    public boolean tableOpen(LocalTime time){

        if(matchTimes.containsKey(time)){//if times hash map already contains the time
            int count = matchTimes.get(time);//count is set to the integer value associated with the time

            ///
            ///
            ///
            ///need to account for times interfering with eachother 
            ///
            ///
            ///
            if(count < enteredInfo.POOL_TABLES){//if more room for tables to be used 
                this.matchTimes.put(time, count + 1);//put the time into matchTimes hashmap
                return true;//sucessfully added
            } else {
                return false;//was not added
            }
        } else {//if key is not within the hashmap
            this.matchTimes.put(time, 1);//add it to the hashmap
            return true;//sucessfully added 
        }
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
