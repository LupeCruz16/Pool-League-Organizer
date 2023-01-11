public class matchObject {

     private String player1, player2, day;
     private int time;

     //Constructor
     public matchObject(String player1, String player2, String day, int time){

        this.player1 = player1;
        this.player2 = player2;
        this.time = time;
        this.day = day;

     }//End of constructor

     //Getter methods
     public String getPlayer1(){
        return player1;
     }

     public String getPlayer2(){
        return player2;
     }

     public String getDay(){
        return day;
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

     public void setDay(String day){
        this.day = day;
     }



    
}//End of machObject class
