public class matchObject {

     private String player1, player2;
     private int time, day;

     //Constructor
     public matchObject(String player1, String player2, int time, int day){

        this.player1 = player1;
        this.player2 = player2;
        this.time = time;
        this.day = day;

     }//End of constructor

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

     public String getDay(int day){
        
         switch(day){
            case 0:
               return "Monday";
            case 1:
               return "Tuesday";
            case 2:
               return "Wednesday";
            case 3:
               return "Thursday";
            case 4:
               return "Friday";
            default:
               return "Invalid Day.";
         }
     }

     public void setDay(int day){
        this.day = day;
     }

     public int getTime(){
        return time;
     }

     public void setTime(int time){
        this.time = time;
     }

     
}//End of machObject class
