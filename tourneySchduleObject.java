import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class tourneySchduleObject {

    private LocalDate scheduledDay;//holds day for given matches
    private ArrayList<matchObject> dayMatches = new ArrayList<>();//holds all matches for a given day
    private HashMap<LocalTime, Integer> matchTimes = new HashMap<>();//holds all times for the matches of the day
    private int todaysMatches;//keeps track of the amount of matches in one day

    //private TreeMap<LocalTime, ArrayList<LocalTime>> matchTimes = new TreeMap<>();

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
     * Will soley add a time to the objects hash map bsed on if the current times do not interfere
     * @param time time to be added
     */
    public void matchTimeAdded(LocalTime time){

        if(!matchTimes.containsKey(time)){//if times hash map does not contain the time then add it
            this.matchTimes.put(time, 1);//add it to the hash map
        } else if (matchPossibility(time)){
            int count = matchTimes.get(time);
            this.matchTimes.put(time, count + 1);
        }
    }

    /**
     * Checks if there is time for a match based on: before and after the match time. If there is space then it return true
     * @param time time that the match is set to occur
     * @return true or false if there is time for the match
     */
    private boolean matchPossibility(LocalTime time){
       LocalTime[] times = this.matchTimes.keySet().toArray(new LocalTime[matchTimes.size()]);
        Arrays.sort(times);//sorting array 

        Long mins = adminInfo.MATCH_DURATION.plus(adminInfo.GRACE_PERIOD).toMinutes();
        LocalTime beforeMatch = time.minusMinutes(mins);
        LocalTime afterMatch = time.plusMinutes(mins);

        int before = closestIndex(times, beforeMatch);
        int after = closestIndex(times, afterMatch);
        int count = 0;
       
        for(int i = before; i < after; i++){
            if(this.matchTimes.get(times[i]) > 1){
                count += this.matchTimes.get(times[i]);
            } else {
                count++;
            }
        }

        if(count < adminInfo.POOL_TABLES){
            return true;
        }
        return false;

    }

    /**
     * Returns the closest index or exact index of a time in order to verify if a match has enough time to occur
     * @param times time that the match is set to occur
     * @param beforeOrAfter determines which time either before or after is attempting to be found
     * @return either exact or closest index of the before or after time 
     */
    private int closestIndex(LocalTime[] times, LocalTime beforeOrAfter){

        int index = Arrays.binarySearch(times, beforeOrAfter, LocalTime::compareTo);
        
        if(index < 0){
            index = -(index + 1);
        }

        return index;
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
