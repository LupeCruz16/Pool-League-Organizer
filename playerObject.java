
public class playerObject {
   private String name, avail;
    private int rank;
    private int score;

    public playerObject(String name, String avail, int rank, int score){
        this.name = name;
        this.avail = avail;
        this.rank = rank;
        this.score = score;
    }//end of public player 

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAvail(){
        return avail;
    }

    public void setAvail(String avail){
        this.avail = avail;
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
