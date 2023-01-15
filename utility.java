import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class utility {

    /**
     * Obtains a players availability string and determine its local time
     * 
     * @param avail String containing a players availability such as "11am-1pm"
     * @param firstAvail Boolen used to determine if the first or second availability is what should be returned
     * @return Returns either LocalTime.MIN (00:00) or the LocalTime conversion
     */
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

    /**
     * Obtains a string and determine its local time. Utilizes a DateTimeFormatter to distinct between AM or PM
     * 
     * @param avail String containing a segmenet of a players availability such as "11am" or "1pm"
     * @return Returns local time conversion including disctinction of am and pm as well as minutes 
     */
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
    }

    /**
     * Takes in a string and finds its minutes if any exists. Utilizes the ":" to determine if minutes exists in the string
     * 
     * @param avail String containing "11:00" or "12:30"
     * @return Returns the minutes found or "0" if no minutes were found
     */
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

    /**
     * Boolean method used to determine if a match between two players was sucessfully generated. Uses Duration to account for
     * a matches length and a grace period to avoid back to back matches. Obtains the players availability and determines if 
     * their is time for a match to be generated and adds that time to each players generated match times for the day it was
     * found in. 
     * 
     * @param day Day that the match would take place (Mon, Tues, Wed, Thur, Fri)
     * @param name1 Player one of the match 
     * @param p1a1 Player ones start availability of the day
     * @param p1a2 Player ones end availability of the day
     * @param name2 Player two of the match 
     * @param p2a1 Player twos start availability of the day
     * @param p2a2 Player twos start availability of the day
     * @return True or false if the match was sucessfully generated 
     */
    public static boolean matchValidity(int day, String name1, LocalTime p1a1, LocalTime p1a2, String name2, LocalTime p2a1, LocalTime p2a2){

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

            return true;
        }
        return false;

    }

}//end of utility
