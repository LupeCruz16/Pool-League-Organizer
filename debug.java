import java.time.LocalDate;
import java.util.ArrayList;

public class debug {
    public static void displayPlayer(String name){

        playerObject player = pdfManager.players.get(name);

        System.out.println("Name: " + player.getName());
        System.out.println("Email: " + player.getEmail());

         
        System.out.println("Avail: ");
        for(int i = 0; i < playerObject.DAYS; i++){

            System.out.println(player.getStandardStartTime(i) + "-" + player.getStandardEndTime(i) + " ");
        }
        

        long score = player.getAvailScore().toMinutes();
        System.out.println("Avail score: " + score);

        System.out.println("Match Counter: " + player.getMatchCounter() + "\n");

        /*
        for(int i = 0; i < playerObject.DAYS; i++){
            
            System.out.println("Generated matches on " + i);
            //player.displayGenMatchesOfDay(i);
            
        }
        */
        System.out.println();

    }

    public static void displayAllPlayers(){

        ArrayList<String> names = new ArrayList<>(pdfManager.players.keySet());

        for(int i = 0; i < pdfManager.players.size(); i++){
            System.out.println("here");
            displayPlayer(names.get(i));
        }
    }

    public static void displayMatches(){
        for(int i = 0; i < pdfManager.matches.size(); i++){
            System.out.println("Matches in ArrayList: " + pdfManager.matches.get(i).getP1().getName() + " v " +  pdfManager.matches.get(i).getP2().getName());
        }
    }

    public static void dislpayWeeklyMatches(){
        LocalDate[] dates = tourneyManager.schedule.keySet().toArray(new LocalDate[tourneyManager.schedule.size()]);

        for(int i = 0; i < tourneyManager.schedule.size(); i++){
            tourneyManager.schedule.get(dates[i]).displayMatches();
        }
    }
}
