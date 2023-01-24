import java.time.DayOfWeek;
import java.time.LocalDate;
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

        if(colon != -1){//if minutes are found
            //minutes = avail.substring(colon, colon + 3);//obtains the minutes including the colon ex ":30"

            minutes = ":30";//ensring everyones minute availability is 30 or an hour exactly 
        }
        return minutes;//return minutes at end 

    }//end of minute finder

    /**
     * Returns day of the week dpending on the integer entered. With the default case being an invalid day of Sunday
     * @param day Integer of day of the week Mon-Fri
     * @return DayOfWeek
     */
    public static DayOfWeek dayOfWeekMatchFound(int day){
        
        switch(day){
            case 0: 
                return DayOfWeek.MONDAY;
            case 1: 
                return DayOfWeek.TUESDAY;
            case 2: 
                return DayOfWeek.WEDNESDAY;
            case 3: 
                return DayOfWeek.THURSDAY;
            case 4: 
                return DayOfWeek.FRIDAY;
            default: 
                return DayOfWeek.SUNDAY;
        }
    }

    /**
     * Obtains a date, obtains the dates day of the week and returns it as an int to represent the slot of a players start and end 
     * availability 
     * @param date date to obtain day of the week from 
     * @return integer 0-4 if valid or 5 if invalid day of the week
     */
    public static int weekDayToInt(LocalDate date){
        //DayOfWeek day = date.getDayOfWeek();
        switch(date.getDayOfWeek()){
            case MONDAY: 
                return 0;
            case TUESDAY: 
                return 1;
            case WEDNESDAY: 
                return 2;
            case THURSDAY: 
                return 3;
            case FRIDAY: 
                return 4;
            default: 
                return 5;
        }
    }

}//end of utility
