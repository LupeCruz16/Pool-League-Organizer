
public class debug {
    public static void displayPlayer(String name){

        playerObject player = pdfManager.players.get(name);

        System.out.println("Name: " + player.getName());
        System.out.println("Email: " + player.getEmail());

        System.out.println("Avail: ");
        for(int i = 0; i < playerObject.DAYS; i++){
            System.out.println(player.getAvail(i) + " ");
        }

        System.out.println("Final Avail: ");
        for(int i = 0; i < playerObject.DAYS; i++){
            System.out.println(player.getFinalAvail(i) + " ");
        }

        System.out.println("Match Counter: " + player.getMatchCounter() + "\n");

    }
}
