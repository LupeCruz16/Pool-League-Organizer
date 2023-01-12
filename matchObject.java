import java.time.LocalDate;
import java.time.LocalTime;

public class matchObject {

     private String player1, player2;
     private int table;
     private LocalDate matchDay;
     private LocalTime matchTime;

     //Constructor
     public matchObject(String player1, String player2){

        this.player1 = player1;
        this.player2 = player2;
        this.table = 0;

        this.matchDay = LocalDate.of(2023, 1, 1);//default to January 1st of 2023
        this.matchTime = LocalTime.of(12, 12);//defaulting time of match start at 12:12

     }//End of constructor

     //Default constructor
     public matchObject(){

      this.player1 = "Unkown";
      this.player2 = "Unkown";
      this.table = 0;

      this.matchDay = LocalDate.of(2023, 1, 1);//default to January 1st of 2023
      this.matchTime = LocalTime.of(12, 12);//defaulting time of match start at 12:12

   }//End of Defualt constructor

     //Getter and setter methods
     public String getPlayer1(){
        return player1;
     }

     public void setPlayer1(String player1){
        this.player1 = player1;
     }

     public String getPlayer2(){
        return player2;
     }

     public void setPlayer2(String player2){
        this.player2 = player2;
     }

     public int getTable(){
         return table;
     }

     public void setTable(int table){
         this.table = table;
     }

     public String getMatchDay(){
         return matchDay.toString();
     }

     public void setMatchDay(LocalDate day){
         this.matchDay = day;
     }

     public LocalTime getTime(){
        return matchTime;
     }

     public void setTime(LocalTime time){
        this.matchTime = time;
     }
     
}//End of machObject class
