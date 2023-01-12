
public class playerObject {
    public final static int DAYS = 5;//setting max availability array size to 5 for the five days of the week
    public final static int MAX_MATCHES = enteredInfo.MAX_MATCHES;
    private String[] avail = new String[DAYS];//availability array for all days of the week, can be changed for match generation
    private String[] finalAvail = new String[DAYS];//contains availability but these times are not to be changed

    //variables for basic fields
    private String name, email;
    private int wins, losses, matchCounter;

    //constructor
    public playerObject(String name, String email, String mon, String tues, String wed, String thur, String fri){
        this.name = name;
        this.email = email;
 
        //setting availability to gathered times of each day of the week
        avail[0] = mon;
        avail[1] = tues;
        avail[2] = wed;
        avail[3] = thur;
        avail[4] = fri;

        //initializing final availability to 0s 
        for(int i = 0; i < DAYS; i++){
            finalAvail[i] = "0";
        }
        
        matchCounter = 0;
        wins = 0;
        losses = 0;
    }//end of constructor

    //getter and setter methods 
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getAvail(int day){
        return avail[day];
    }

    public void setAvail(int day, String time){
        this.avail[day] = time;
    }

    public String getFinalAvail(int day){
        return finalAvail[day];
    }

    public void setFinalAvail(int day, String time){
        this.finalAvail[day] = time;
    }

    public int get(){
        return wins;
    }

    public void setMatchCounter(int matches){
        this.matchCounter = matches;
    }

    public void incrementMatchCounter(){
        this.matchCounter++;
    }

    public int getMatchCounter(){
        return matchCounter;
    }

    public void setWins(int wins){
        this.wins = wins;
    }

    public int getLosses(){
        return losses;
    }

    public void setLosses(int losses){
        this.losses = losses;
    }


}//end of player class
