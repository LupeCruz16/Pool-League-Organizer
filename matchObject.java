import java.time.LocalDate;
import java.time.LocalTime;

public class matchObject {

   private playerObject player1, player2;//coontains both oppponents names for the match
   private int table;//contains the pool table the match will take place on 
   private LocalDate matchDay;//contains the day the match is scheduled 
   private LocalTime matchTime;//contains the time the match is scheduled 

   /**
    * matchObject constructor 
    * Utilized to store match information into an easier way of acessingg
    *
    * @param player1 String contains opponent ones name
    * @param player2 String contains opponent twos name 
    *
    * @hidden table contains the pool table the match will take place on 
    * @hidden matchDay contains the day the match is scheduled 
    * @hidden matchTime contains the time the match is scheduled 
   */
   public matchObject(playerObject player1, playerObject player2){

      this.player1 = player1;
      this.player2 = player2;
      this.table = 0;

   }

   /**
    * Returns player object of player one 
    * @return player object
    */
   public playerObject getP1(){
      return player1;
   }

   /**
    * Returns player object of player two 
    * @return player object
    */
   public playerObject getP2(){
      return player2;
   }

   /**
    * Returns table number that match will take place on
    * @return table 
    */
   public int getTable(){
      return table;
   }

   /**
    * Sets table the match will take place on 
    * @param table Int containing table number 
    */
   public void setTable(int table){
      this.table = table;
   }

   /**
    * Returns day of match
    * @return matchDay
    */
   public String getMatchDay(){
      return matchDay.toString();
   }

   /**
    * Returns match day as a local date instead of string
    * @return matchDay
    */
   public LocalDate getMatchDate(){
      return matchDay;
   }

   /**
    * Sets the day the match will take place on 
    * @param day LocalDate of day of the match
    */
   public void setMatchDay(LocalDate day){
      this.matchDay = day;
   }

   /**
    * Returns the time the match will take place
    * @return matchTime 
    */
   public LocalTime getTime(){
     return matchTime;
   }

   /**
    * Sets the time of the match
    * @param time LocalTime of the match
    */
   public void setTime(LocalTime time){
     this.matchTime = time;
   }

   /**
    * Returns the match time generated as standard time instead of "17:00" will be returned as "5:00"
    * @return string as standard time 
    */
   public String getStandardTime(){
      String time = LocalTime.of(matchTime.getHour() % 12, matchTime.getMinute()).toString();

        if(time.charAt(0) == '0'){//if time is "02:00"
            time = time.substring(1, time.length());//change to "2:00"
        } 
        return time;
   }

   /**
    * Determines if both players availability, based on the day, have enough time for a match to be scheduled 
    * @param day day the match should be scheduled for 
    * @return LocalTime.MIN if no time was found and opposite if time was found 
    */
   public LocalTime matchPossibility(int day){

      //obtaining players availability 
      LocalTime p1a1 = getP1().getStartAvail(day);
      LocalTime p1a2 = getP1().getEndAvail(day);

      LocalTime p2a1 = getP2().getStartAvail(day);
      LocalTime p2a2 = getP2().getEndAvail(day);

      //finding overlapping time to schedule a match
      LocalTime availStart = p1a1.isAfter(p2a1) ? p1a1 : p2a1;
      LocalTime availEnd = p1a2.isBefore(p2a2) ? p1a2 : p2a2;

      //checking if there is enough time for the match including a grace period 
      if(availStart.plus(adminInfo.MATCH_DURATION).plus(adminInfo.GRACE_PERIOD).isBefore(availEnd)){
         return availStart;//returns match start time 
      }

      return LocalTime.MIN;//returns if no time is found 
   }
     
}//End of machObject class
