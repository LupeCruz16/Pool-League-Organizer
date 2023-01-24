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

   public playerObject getP1(){
      return player1;
   }

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
    * Sets the time of the match
    * @param time LocalTime of the match
    */
   public void setTime(LocalTime time){
     this.matchTime = time;
   }
     
}//End of machObject class
