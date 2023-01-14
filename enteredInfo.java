import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class enteredInfo {
    
    static final LocalDate STAR_DATE = LocalDate.of(2023, 1, 3);

    static final LocalDate END_DATE = LocalDate.of(2023, 1, 31);

    //obtaining the amount of weeks in the tournament
    static final long TOURNEY_WEEKS = ChronoUnit.WEEKS.between(STAR_DATE, END_DATE);

    //include pool table and tie to match object

    static final int MAX_MATCHES = 15;

    //obtains the max weekly matches that a player can have to continue and have matches throughout the tournament
    public static int getMaxWeeklyMatches(){

        int scheduleSize = tourneyManager.schedule.size();//obtaing the playable days from tourneyManager

        if(scheduleSize == 0){//hashmap of the tournmanet schedule is empty
            System.out.println("The tournament schedule is empty");
            return scheduleSize;

        } else {//tournement has dates within it
            
            int weeklyMatches = MAX_MATCHES /  (int)TOURNEY_WEEKS;//finding how many matches they should have per week
            return weeklyMatches;//return that number

        }
    }//end of get max weekly matches 

}
