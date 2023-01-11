public class matchObject {

     private String player1, player2;
     private int time, day;

     //Constructor
     public matchObject(String player1, String player2, int day, int time){

        this.player1 = player1;
        this.player2 = player2;
        this.time = time;

     }//End of constructor

     //Getter methods
     public String getPlayer1(){
        return player1;
     }

     public String getPlayer2(){
        return player2;
     }

     public String getDay(int day){
        
         switch(day){
            case 0:
               return "mon";
            case 1:
               return "tues";
            case 2:
               return "wed";
            case 3:
               return "thurs";
            case 4:
               return "fri";
            default:
               return "This isnt supposed to happen.";
         }
     }

     public int getTime(){
        return time;
     }

     //Setter methods
     public void setPlayer1(String player1){
        this.player1 = player1;
     }

     public void setPlayer2(String player2){
        this.player2 = player2;
     }

     public void setTime(int time){
        this.time = time;
     }

     public void setDay(int day){
        this.day = day;
     }
}//End of machObject class
