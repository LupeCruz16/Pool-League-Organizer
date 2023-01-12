import javax.swing.JOptionPane;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class utility {

    //finding and returning minutes from either the first availability time slott or second
    public static int minutesFinder(String avail, int dash, boolean firstTime){
        int colon, minutes = 0;

        if(firstTime){//checks the first times colon, before the dash

            //if the time has a colon in the availability such as "12:30" or "2:30"
            colon = avail.indexOf(":");

            if(colon != -1 && colon < dash){//ensuring a colon was found and that it is with the first time slott
                minutes = Integer.parseInt(avail.substring(colon + 1, colon + 3));//convert minutes into an integer

            }
        } else {

            //if the time has a colon in the availability such as "12:30" or "2:30" after the dash 
            colon = avail.indexOf(":", dash);

            if(colon != -1 && colon > dash){//ensuring a colon was found and that it is with the first time slott
                minutes = Integer.parseInt(avail.substring(colon + 1, colon + 3));//convert minutes into an integer

            }
        }

        return minutes;
    }//end of minutesFinder

    //used to change the players availability to military time for easier comparisions and match making
    public static void modifyTime(playerObject player, int day){

        //playerObject player = players.get(id);//creating an instance of playerObject
        String avail = player.getAvail(day);//storing the availability of the day into a string

        String firstTime, secTime;//store the final times of the availabilities given
        int firstDigit, secDigit, finalTime, minutes;//used to store integer values of string
        int dash = avail.indexOf("-");//obtain the dash location for second end time

        if(dash == -1){//if no time available for that given day ex "N/A"
            player.setAvail(day, "0");//setting that days availability to 0 
        }
        else if(Character.isDigit(avail.charAt(0))){//if first character of player availability is an integer
            firstDigit = Integer.parseInt(avail.substring(0, 1));//converts character into an integer

            if(Character.isDigit(avail.charAt(1))){//if there is a second integer character followed 
                secDigit = Integer.parseInt(avail.substring(1, 2));//converting second digit into an integer
                minutes = utility.minutesFinder(avail, dash, true);//if first time has a colon in the time such as "12:30"

                finalTime = (firstDigit * 10) + secDigit;//converts time into double digits
                firstTime = Integer.toString((finalTime * 100) + minutes);//converts time into military time
                
            } else {//only one digit for the first time availability
                minutes = utility.minutesFinder(avail, dash, true);//if this single digit time has a colon such as "2:30"

                firstTime = Integer.toString((firstDigit * 100) + 1200 + minutes);//converts time into military time
            }

            //checking the availability after the dash
            if(Character.isDigit(avail.charAt(dash + 1))){//if first character of player availability is an integer
                firstDigit = Integer.parseInt(avail.substring(dash + 1, dash + 2));//converts character into an integer

                if(Character.isDigit(avail.charAt(dash + 2))){//if there is a second integer character followed 
                    secDigit = Integer.parseInt(avail.substring(dash + 2, dash + 3));//converting second digit into an integer
                    minutes = utility.minutesFinder(avail, dash, false);//if first time has a colon in the time such as "12:30"

                    finalTime = (firstDigit * 10) + secDigit;//converts time into double digits
                    secTime = Integer.toString(finalTime * 100 + minutes);//converts time into military time
                    
                } else {//only one digit for the first time availability
                    minutes = utility.minutesFinder(avail, dash, false);//if first time has a colon in the time such as "12:30"

                    secTime = Integer.toString((firstDigit * 100) + 1200 + minutes);//converts time into military time
                }

                player.setAvail(day, firstTime + "-" + secTime);//setting player availability to new military time converted time
                player.setFinalAvail(day, firstTime + "-" + secTime);//setting players final availability to military time conversion

            }else {
                JOptionPane.showMessageDialog(null, "Invalid PDF Format. Please Enter a valid PDF");
            }

        } else{
            JOptionPane.showMessageDialog(null, "Invalid PDF Format. Please Enter a valid PDF");
        }

    }//end of modify time

    //returns either the first or second time of a player availability as an integer
    public static int getAvailTime(String avail, boolean firstTime){
        String timeString;//temporary hold for segmented time 
        int dash, time = 0;//holders of dash placement and returned time as an int

        dash = avail.indexOf("-");//finding the dash in availability

        if(dash == -1){//if there is no dash stored ex "0"
            return time;//return time as 0
        } else {//if availability has dash ex "1400-1600"

            if(firstTime){//if first time ex "1400"
                timeString = avail.substring(0, dash);//obtain segement of string

            } else {//if second time ex "1600"
                timeString = avail.substring(dash + 1);//obtain segment of string
        
            }

            time = Integer.parseInt(timeString);//converting timeString into an integer
        }

        return time;
    }//end of get available time

    public static boolean matchValidity(int day, String name1, int p1a1, int p1a2, String name2, int p2a1, int p2a2){

        //seperating the military time conversion into hours and minutes for easier comparision 
        LocalTime player1Start = LocalTime.of(p1a1 / 100, p1a1 % 100);
        LocalTime player1End = LocalTime.of(p1a2 / 100, p1a2 % 100);

        LocalTime player2Start = LocalTime.of(p2a1 / 100, p2a1 % 100);
        LocalTime player2End = LocalTime.of(p2a2 / 100, p2a2 % 100);

        //adding the duration of each match into a duration variable also accounting for a grace period
        Duration matchDuration = Duration.ofMinutes(20);
        Duration gracePeriod = Duration.ofMinutes(10);

        //finding overlapping time to schedule a match
        LocalTime availStart = player1Start.isAfter(player2Start) ? player1Start : player2Start;
        LocalTime availEnd = player1End.isBefore(player2End) ? player1End : player2End;

        //checking if there is enough time for the match
        if(availStart.plus(matchDuration).isBefore(availEnd)){
            LocalTime matchStart = availStart;//will be used for match scheudling 

            /////
            //temp date print until match has been placed correctly
            /////
            LocalDate startDay = LocalDate.of(2022, 1, 3);

            //creating match object and filling in its match information
            matchObject match = new matchObject(name1, name2);
            match.setTime(matchStart);
            match.setMatchDay(startDay);

            tourneyManager.schedule.get(startDay).addMatch(match);//adding the match into the generated schedule

            //figuring out when the mach will be over for other matches to be held
            LocalTime matchEnd = matchStart.plus(matchDuration).plus(gracePeriod);
            
            //removing the colon from matchEnds "13:30" to be military time as "1330"
            String newStart = matchEnd.toString().replace(":", "");

            //modifying players times to be changed incase other matched can be held
            pdfManager.players.get(name1).setAvail(day, newStart + "-" + p1a2);
            pdfManager.players.get(name2).setAvail(day, newStart + "-" + p2a2);


            ///////future issue of changing the availability and have to change back to begining matches
            return true;
        }
        return false;

    }//end of match validity

    //used to reset players availability if their times are no longer able to generate more matches
    public static void invalidAvail(){


    }//end of invalid availability 

}//end of utility
