import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class adminInfo {
    
    static final LocalDate STAR_DATE = LocalDate.of(2023, 1, 3);

    static final LocalDate END_DATE = LocalDate.of(2023, 1, 31);

    //obtaining the amount of weeks in the tournament
    static final long TOURNEY_WEEKS = ChronoUnit.WEEKS.between(STAR_DATE, END_DATE);

    static final int POOL_TABLES = 3;//contains the amount of pool tables that matches can take place on

    //include pool table and tie to match object

    static final int MAX_MATCHES = 15;

    static final Duration MATCH_DURATION = Duration.ofMinutes(20);
    
    static final Duration GRACE_PERIOD = Duration.ofMinutes(10);

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

    public static int getMaxDailyMatches(){

        int scheduleSize = tourneyManager.schedule.size();//obtaing the playable days from tourneyManager

        if(scheduleSize == 0){//hashmap of the tournmanet schedule is empty
            System.out.println("The tournament schedule is empty");
            return scheduleSize;

        } else {//tournement has dates within it
            int dailyMatches = (MAX_MATCHES * pdfManager.players.size()) /  scheduleSize;//finding how many matches they should have per week
            return dailyMatches;//return that number

        }
    }//end of get max weekly matches 

}
