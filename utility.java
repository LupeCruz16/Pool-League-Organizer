
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

}//end of utility
