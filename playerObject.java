
public class playerObject {
    private final int SIZE = 5;//setting max availability array size to 5 for the five days of the week
    private String[] avail = new String[SIZE];//availability array for all days of the week
    private String name, email;
    private int rank;
    private int score;

    //constructor
    public playerObject(String name, String email, String mon, String tues, String wed, String thur, String fri){
        this.name = name;
        this.email = email;
 
        avail[0] = mon;
        avail[1] = tues;
        avail[2] = wed;
        avail[3] = thur;
        avail[4] = fri;
      
        //this.rank = rank;
        //this.score = score;
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

    public void setAvail(int pos, String day){
        this.avail[pos] = day;
    }

    public int getRank(){
        return rank;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }


}//end of player class
