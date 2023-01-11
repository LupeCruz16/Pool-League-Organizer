import javax.swing.JOptionPane;

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

}//end of utility
