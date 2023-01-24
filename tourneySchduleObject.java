import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class tourneySchduleObject {

    private LocalDate scheduledDay;//holds day for given matches
    private ArrayList<matchObject> dayMatches = new ArrayList<>();//holds all matches for a given day
    //go through array and check if match has started 
    //sort by times as matches are added 

    
    /**
     * Class constructor used to keep track of the matches generated for a day under one single object
     * 
     * @param scheduledDay Will maintain the day the match will be added into
     */
    public tourneySchduleObject(LocalDate scheduledDay){

       this.scheduledDay = scheduledDay;
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
     * Returns the day matches array size 
     * @return day matches size
     */
    public int getDayMatchesSize(){
        if(this.dayMatches == null){
            return 0;
        } 
        return this.dayMatches.size();
    }

    /**
     * Appends a match to the objects list of matches for the given day
     * @param match Containd the new matches information
     */
    public void addMatch(matchObject match){
        if(this.dayMatches == null){
            this.dayMatches = new ArrayList<>();
            this.dayMatches.add(match);
        } else {
            this.dayMatches.add(match);
        }
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
     * Will determine if a match is valid to be added into the arraylist of day matches 
     * @param match match desired to be added
     * @return true or false if can be added
     */
    public boolean verifyMatch(matchObject match){
        int count = 0;

        //sorting the current matches in the day in ascending order 
        Collections.sort(this.dayMatches, new Comparator<matchObject>(){
            public int compare(matchObject m1, matchObject m2){
                return m1.getTime().compareTo(m2.getTime());
            }
        });
        
        for(int i = 0; i < this.dayMatches.size(); i++){//iterate through the matches in the array list 
            matchObject curMatch = this.dayMatches.get(i);//obtains match object from array of dayMatches

            //if the current match time is the same as the match time to be added then this cannot occur more than 3 times
            if(curMatch.getTime() == match.getTime()){

                //checking if either player appears in any of the matches with the same time 
                if(curMatch.getP1().getName() == match.getP1().getName() || curMatch.getP1().getName() == match.getP2().getName() |
                curMatch.getP2().getName() == match.getP1().getName() || curMatch.getP2().getName() == match.getP2().getName()){

                    //overlapping time. Now moves match start time and rechecks verifyMatch
                    //return moveMatchTime(match, getScheduledDay());
                    return false;

                } else {
                    count++;//increment to keep track of how many times this appears 
                }
            }

            //accounts for if there are already too many counts that have been found
            if(count > 3){
                return false;//there are already too many times with the same start in the dayMatches array list

            }
        }
        return true;//there is space for another new match

    }

    /**
     * Moves a match time forward to see if that time can then be added into the array list. Calls verifyMatch to do this
     * @param match match whos time has to be moved forward 
     * @param date date that match should take place on 
     * @return true or false if the time can be added 
     */
    public boolean moveMatchTime(matchObject match, LocalDate date){
        
        //adding the total match time into the new time for the match
        LocalTime newTime = match.getTime().plus(adminInfo.MATCH_DURATION).plus(adminInfo.GRACE_PERIOD);

        int day = utility.weekDayToInt(date);

        match.setTime(newTime);//setting match start time as a new start time 

        //ensuring that after the match time is moved, that both players are still available 
        //have to change the function to now account for the new match time 
        if(tourneyManager.timeValidity(match, day)){
            //checking to see if the match can be added 
            if(verifyMatch(match)){
                pdfManager.matchDeletion(match.getP1().getName(), match.getP2().getName());//removing that pairings match from possible matches array list 
                return true;//match can be added

            } 
        }

        return false;//cannot be added
    }

    /**
     * Displays all matches for the given day of the object
     */
    public void displayMatches(){

        if(this.dayMatches.size() > 0){//if matches were generated and added for today
            System.out.println("On: " + scheduledDay.toString());//printing the day of the matches

            for(int i = 0; i < this.dayMatches.size(); i++){//itterate through all generated matches

                System.out.println(this.dayMatches.get(i).getP1().getName() + " will play againt " + dayMatches.get(i).getP2().getName() + 
                " at " + this.dayMatches.get(i).getStandardTime());
            }

        } else {
            System.out.println("There are no matches on: " + scheduledDay.toString());
        }
    }

}//end of tournament schedule object
