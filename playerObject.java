import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class playerObject {
    public final static int DAYS = 5;//setting max availability array size to 5 for the five days of the week
    public final static int MAX_MATCHES = enteredInfo.MAX_MATCHES;

    //contain the start and end times of the players availaility from their indexes
    private LocalTime[] start = new LocalTime[DAYS];
    private LocalTime[] end = new LocalTime[DAYS];

    //keeps track of a players generated matches 
    private ArrayList<LocalTime>[] generatedMatches = (ArrayList<LocalTime>[])(new ArrayList<?>[DAYS]);

    //variables for basic fields
    private String name, email;
    private Duration availScore;//will determine in what order they are paired up based on how available they are
    private int  matchCounter, weeklyMatchCount;//keeps track of current number of matches scheudled and how many are allowed per week
    private int wins, losses;//keeps track of players wins and losses

    /**
     * Object constructor that obtains each players basic information
     * 
     * @param name String containing players name
     * @param email String containing players email
     * @param avail Array of strings containing the players availability of Mon-Fri
     * 
     * Not parameters but exist in the constructor 
     * @hidden availScore used to determine the order of who is paired up first
     * @hidden start[] contains the beginning of the players availability, stored in local time
     * @hidden end[] contains the end of the players availability, stored in local time
     * @hidden generatedMatches[][] contains the times of a players possible matches
     * @hidden matchCounter contains the amount of matches the player has scheduled 
     * @hidden weeklyMatchCount contains the amount of matches the player can have a week, avoid a player having all their 
     *         matches in one week
     * @hidden wins contains the amount of wins the player has 
     * @hidden losses contains the amount of losses the player has 
     * 
     */
    public playerObject(String name, String email, String[] avail){
        this.name = name;
        this.email = email;

        this.availScore = Duration.ofMinutes(0);

        //collecting all of the players availability 
        for(int i = 0; i < avail.length; i++){

            //obtaining the availability scores and storing them into two different arrays 
            this.start[i] = utility.collectAvail(avail[i], true);
            this.end[i] = utility.collectAvail(avail[i], false);

            this.generatedMatches[i] = new ArrayList<LocalTime>();//initializing every slot within generated matches to have 0

            if(start[i] != LocalTime.MIN && end[i] != LocalTime.MIN){//if player availability is not 00:00 then sum to score
                this.availScore = getAvailScore().plusMinutes(Duration.between(start[i], end[i]).toMinutes());//obtaining the availability score as it is being collected
            }
        }

        matchCounter = 0;
        weeklyMatchCount = 0;

        wins = 0;
        losses = 0;

    }
    
    //generate all matches and then check player avail. if goes over then skip
    //list of days for matches and if match is scheduled the same day then add 30 mintues and ensure is still valid 

    /**
     * Returns players name
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Sets player objects name
     * @param name Used to fill in objects name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Returns players email
     * @return email
     */
    public String getEmail(){
        return email;
    }

    /**
     * Sets player objects email
     * @param email Used to fill in objects email 
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Returns start availability based on given day
     * @param day Used to identify which days availability is being called
     * @return Start of availability 
     */
    public LocalTime getStartAvail(int day){
        return start[day];
    }

    /**
     * Returns end availability based on given day
     * @param day Used to identify which days availability is being called
     * @return End of availability 
     */
    public LocalTime getEndAvail(int day){
        return end[day];
    }

    /**
     * Returns time as 1:00 instead of 13:00 for the client side of the program
     * @param day Used to identify which days availability is being called
     * @return Standard time 
     */
    public String getStandardStartTime(int day){
        String time = LocalTime.of(start[day].getHour() % 12, start[day].getMinute()).toString();

        if(time.charAt(0) == '0'){//if time is "02:00"
            time = time.substring(1, time.length());//change to "2:00"
        } 
        return time;
    }

    /**
     * Returns time as 1:00 instead of 13:00 for the client side of the program
     * @param day Used to identify which days availability is being called
     * @return Standard time 
     */
    public String getStandardEndTime(int day){
        String time = LocalTime.of(end[day].getHour() % 12, end[day].getMinute()).toString();

        if(time.charAt(0) == '0'){//if time is "02:00"
            time = time.substring(1, time.length());//change to "2:00"
        } 
        return time;
    }

    /**
     * Will display the generated match times for a given day of the week
     * @param day Determines what days availability is called
     */
    public void displayGenMatchesOfDay(int day){
        ArrayList<LocalTime> dayMatches = generatedMatches[day];

        if(dayMatches.size() > 0){//if day has a match in it 
            System.out.print("Times are: ");

            for(LocalTime match : dayMatches){//prints match times
                System.out.print(match + " ");
            }
            System.out.println();//for formatting 

        } else {//no matches were found that day
            System.out.println("No matches were found");

        }
    }

    /**
     * Adds a generated match time into the 2d array list
     * @param day Determines what day to add the time in
     * @param time Time to be added 
     */
    public void addGeneratedMatch(int day, LocalTime time){
        generatedMatches[day].add(time);
    }

    /**
     * Deleted a time from the 2d array list 
     * @param day Determines what day to delete the time in
     * @param time Time to be deleted 
     */
    public void deleteGeneratedMatch(int day, LocalTime time){
        generatedMatches[day].remove(time);
    }

    /**
     * Returns the players availaility score 
     * @return availability score 
     */
    public Duration getAvailScore(){
        return availScore;
    }

    /**
     * Increments players current match counder
     */
    public void incrementMatchCounter(){
        this.matchCounter++;
    }

    /**
     * Returns players match counter
     * @return match counter 
     */
    public int getMatchCounter(){
        return matchCounter;
    }

    /**
     * Resets weekly match counter and makes it so the player may have more matches scheduled for the next week 
     */
    public void resetWeeklyMatchCount(){
        this.weeklyMatchCount = 0;
    }

    /**
     * Increments weekly match counter 
     */
    public void incrementWeeklyMatchCoun(){
        this.weeklyMatchCount++;
    }

    /**
     * Returns weekly match counter 
     * @return weekly match counter 
     */
    public int getWeeklyMatchCoun(){
        return weeklyMatchCount;
    }

    /**
     * Returns wins 
     * @return wins 
     */
    public int getWins(){
        return wins;
    }

    /**
     * Increments player wins 
     */
    public void incrementWins(){
        this.wins++;
    }

    /**
     * Decrements from a players wins in case a matches decision is reversed. Accounts for attempting to decrement when the player has no wins 
     */
    public void decrementWins(){
        if(wins > 0){
            this.wins--;
        } else {
            System.out.println("Invalid, player has no wins to decrement from");
        }
    }

    /**
     * Returns losses 
     * @return losses
     */
    public int getLosses(){
        return losses;
    }

    /**
     * Increments player losses
     */
    public void incrementLosses(){
        this.losses++;
    }

    /**
     * Decrements from a players losses in case a matches decision is reversed. Accounts for attempting to decrement when the player has no losses 
     */
    public void decrementLosses(){
        if(losses > 0){
            this.losses--;
        } else {
            System.out.println("Invalid, player has no losses to decrement from");
        }
    }

}//end of player class
