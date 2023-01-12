import java.time.LocalDate;
import java.util.ArrayList;

public class tourneySchduleObject {

    private LocalDate scheduledDay;
    private static ArrayList<matchObject> dayMatches = new ArrayList<>();
    private int todaysMatches;

    //Constructor
    public tourneySchduleObject(LocalDate scheduledDay, matchObject match){

       this.scheduledDay = scheduledDay;
       this.todaysMatches = 0;
       dayMatches.add(todaysMatches, match);

    }//End of constructor

    //Getter and setter methods
    public LocalDate getScheduledDay(){
       return scheduledDay;
    }

    public void setScheduledDay(LocalDate scheduledDay){
       this.scheduledDay = scheduledDay;
    }

    public int getTodaysMatches(){
        return todaysMatches;
    }

    public void addMatch(matchObject match){
        dayMatches.add(todaysMatches++, match);
    }

    public void displayMatches(){

        if(todaysMatches > 0){//if matches were generated and added for today
            System.out.println("On: " + scheduledDay.toString());//printing the day of the matches

            for(int i = 0; i < todaysMatches; i++){//itterate through all generated matches
                String matchTime = utility.convertToStandardTime(dayMatches.get(i).getTime());

                System.out.println(dayMatches.get(i).getPlayer1() + " will play againt " + dayMatches.get(i).getPlayer2() + 
                " at " + matchTime);
            }

        } else {
            System.out.println("There are no matches on: " + scheduledDay.toString());
        }
    }

}//end of tournament schedule object
