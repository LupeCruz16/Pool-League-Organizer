import java.time.LocalDate;
import java.time.LocalTime;

public class matchObject {

   private String player1, player2;//coontains both oppponents names for the match
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
   public matchObject(String player1, String player2){

      this.player1 = player1;
      this.player2 = player2;
      this.table = 0;

      this.matchDay = LocalDate.of(2023, 1, 1);//default to January 1st of 2023
      this.matchTime = LocalTime.of(12, 12);//defaulting time of match start at 12:12

   }

   /**
    * matchObject default constructor 
    * Constains the same variables as the constructor but sets these variables to predetermines values
    */
   public matchObject(){

      this.player1 = "Unkown";
      this.player2 = "Unkown";
      this.table = 0;

      this.matchDay = LocalDate.of(2023, 1, 1);//default to January 1st of 2023
      this.matchTime = LocalTime.MIN;//defaulting time of match start at 00:00

   }
  
   /**
    * Returns player ones name
    * @return player one name
    */
   public String getPlayer1(){
      return player1;
   }

   /**
    * Sets player ones name
    * @param player1 Used to set matches first player
    */
   public void setPlayer1(String player1){
     this.player1 = player1;
   }

   /**
    * Returns player twos name
    * @return player twos name
    */
   public String getPlayer2(){
     return player2;
   }

   /**
    * Sets player twos name
    * @param player2 Used to set matches second player
    */
   public void setPlayer2(String player2){
     this.player2 = player2;
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
     
}//End of machObject class
