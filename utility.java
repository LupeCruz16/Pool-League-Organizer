import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class utility {

    public static LocalTime collectAvail(String avail, boolean firstAvail){

        if(avail.indexOf("-") == -1){//if player has no availability
            return LocalTime.MIN;//set time top 00:00
        } else {//if player is available
            String[] parts = avail.split("-");//split string based on dash

            if(firstAvail){//if first time
                return convertLocal(parts[0]);

            } else {//if second time
                return convertLocal(parts[1]);
            }
        }
    }

    //will take in a string and conver it into local time 
    private static LocalTime convertLocal(String avail){
        String time;//collects final time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");//will determine if time is am or pm
    

        if(avail.length() > 1 && Character.isDigit(avail.charAt(1))){//if players availability has a second digit
            if(minuteFinder(avail) == "0"){//checking to see if minutes were found
                if(avail.substring(0, 2) == "12"){//if time is 12:00 it has to be pm or it is midnight 
                    time = avail.substring(0, 2) + ":00 PM";

                } else {
                    time = avail.substring(0, 2) + ":00 AM";

                }
                return LocalTime.parse(time, formatter);//will return double digit times without minutes

            } else {//minutes were found
                if(avail.substring(0, 2) == "12"){//if time is 12:00 it has to be pm or it is midnight 
                    time = avail.substring(0, 2) + minuteFinder(avail) + " PM";

                } else {
                    time = avail.substring(0, 2) + minuteFinder(avail) + " AM";

                }
                return LocalTime.parse(time, formatter);
            }

        } else {//if player has a single digit availability such as either "2" or "2:30"
            if(minuteFinder(avail) == "0"){//checking to see if minutes were found
                time = "0" + avail.substring(0, 1) + ":00 PM";
                return LocalTime.parse(time, formatter);//will return single digit times without minutes

            } else {//minutes were found
                time = "0" + avail.substring(0, 1) + minuteFinder(avail) + " PM";
                return LocalTime.parse(time, formatter);
            }

        }
    }//end of convert to local time

    //will return "0" if no minutes were found or the minutes if found
    private static String minuteFinder(String avail){
        String minutes = "0";
        int colon;

        //obtain colons position if it exists 
        colon = avail.indexOf(":");

        if(colon != -1){//no minutes are found
            minutes = avail.substring(colon, colon + 3);//obtains the minutes including the colon ex ":30"

        }
        return minutes;//return minutes at end 

    }//end of minute finder

    public static boolean matchValidity(int day, String name1, LocalTime p1a1, LocalTime p1a2, 
                                        String name2, LocalTime p2a1, LocalTime p2a2){

        //adding the duration of each match into a duration variable also accounting for a grace period
        Duration matchDuration = Duration.ofMinutes(20);
        Duration gracePeriod = Duration.ofMinutes(10);

        //finding overlapping time to schedule a match
        LocalTime availStart = p1a1.isAfter(p2a1) ? p1a1 : p2a1;
        LocalTime availEnd = p1a2.isBefore(p2a2) ? p1a2 : p2a2;

        //checking if there is enough time for the match including a grace period 
        if(availStart.plus(matchDuration).plus(gracePeriod).isBefore(availEnd)){
            LocalTime matchStart = availStart;//will be used for match scheudling 

            //adding the time into the players possible matches
            pdfManager.players.get(name1).addGeneratedMatch(day, matchStart);
            pdfManager.players.get(name2).addGeneratedMatch(day, matchStart);

            System.out.println("Match was added");

            return true;
        }
        return false;

    }//end of match validity

}//end of utility
