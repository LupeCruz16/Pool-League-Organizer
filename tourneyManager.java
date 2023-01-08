import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class tourneyManager {
    
    public static void createTournament(){

        //Setting up tournament creation parameters
        String name = "Test Tournament";
        String url = "test-tournament";
        String description = "Test tournament for understanding API";
        String tournamentType = "round robin";

        //Building API request URL
        String requestURL = hidden.CHA_URL + "tournaments.json";

        //Setting up request body 
        String requestBody = "tournament[name]=" + name + 
        "&tournament[url]=" + url +
        "&tournament[description]=" + description +
        "&tournament[tournament_type]=" + tournamentType;    
        
        //Send and get response
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        
        try{
            //Open the connection 
            URL conURL = new URL(requestURL);
            connection = (HttpURLConnection)conURL.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Connect-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authroization", "Basic" + hidden.CHA_Key);

            //Send the request body
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(requestBody);
            writer.flush();
            
            //Get the response 
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null){
                response.append(line);
            }

            //Printing out response
            System.out.println(response.toString());

        } catch(IOException e){
            e.printStackTrace();
        }

        if(connection != null){
            connection.disconnect();
        }
    }
}
