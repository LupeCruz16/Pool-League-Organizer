import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class playerObject {
    public final static int DAYS = 5;//setting max availability array size to 5 for the five days of the week
    public final static int MAX_MATCHES = enteredInfo.MAX_MATCHES;

    //contain the start and end times of the players availaility from their indexes
    private LocalTime[] start = new LocalTime[DAYS];
    private LocalTime[] end = new LocalTime[DAYS];

    //used to keep track of possible matches based on a day which would be rows
    private static int possibleMatches = 0;

    private ArrayList<LocalTime>[] generatedMatches = (ArrayList<LocalTime>[])(new ArrayList<?>[DAYS]); 

    //variables for basic fields
    private String name, email;
    private Duration availScore;//will determine in what order they are paired up based on how available they are
    private int  matchCounter, weeklyMatchCount;//keeps track of current number of matches scheudled and how many are allowed per week
    private int wins, losses;//keeps track of players wins and losses

    //constructor
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

    }//end of constructor
    
    //generate all matches and then check player avail. if goes over then skip
    //list of days for matches and if match is scheduled the same day then add 30 mintues and ensure is still valid 


    //getter and setter methods 
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public LocalTime getStartAvail(int day){
        return start[day];
    }

    public LocalTime getEndAvail(int day){
        return end[day];
    }

    public String getStandardStartTime(int day){
        String time = LocalTime.of(start[day].getHour() % 12, start[day].getMinute()).toString();

        if(time.charAt(0) == '0'){//if time is "02:00"
            time = time.substring(1, time.length());//change to "2:00"
        } 
        return time;
    }

    public String getStandardEndTime(int day){
        String time = LocalTime.of(end[day].getHour() % 12, end[day].getMinute()).toString();

        if(time.charAt(0) == '0'){//if time is "02:00"
            time = time.substring(1, time.length());//change to "2:00"
        } 
        return time;
    }

    public LocalTime getGeneratedMatch(int day, int matchPos){
        return generatedMatches[day].get(matchPos);
    }

    public void addGeneratedMatch(int day, LocalTime time){
        generatedMatches[day].add(time);
        possibleMatches++;//incrementing possible matches
    }

    public void deleteGeneratedMatch(int day, LocalTime time){

        for(int i = 0; i < DAYS; i++){
            for(int j = 0; j < possibleMatches; j++){
                if(generatedMatches[i].get(j).equals(time)){

                    generatedMatches[i].remove(j);
                }
            }
        }
    }

    public Duration getAvailScore(){
        return availScore;
    }

    public void setMatchCounter(int matches){
        this.matchCounter = matches;
    }

    public void incrementMatchCounter(){
        this.matchCounter++;
    }

    public int getMatchCounter(){
        return matchCounter;
    }

    public void resetWeeklyMatchCount(){
        this.weeklyMatchCount = 0;
    }

    public void incrementWeeklyMatchCoun(){
        this.weeklyMatchCount++;
    }

    public int getWeeklyMatchCoun(){
        return weeklyMatchCount;
    }

    public int getWins(){
        return wins;
    }

    public void setWins(int wins){
        this.wins = wins;
    }

    public int getLosses(){
        return losses;
    }

    public void setLosses(int losses){
        this.losses = losses;
    }


}//end of player class
