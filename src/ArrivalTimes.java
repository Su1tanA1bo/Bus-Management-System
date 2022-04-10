import java.text.*;
import java.util.*;
import java.io.*;

public class ArrivalTimes {
	public static ArrayList<String> findTrips(String arrivalTime){
		SimpleDateFormat date = new SimpleDateFormat("HH:MM:SS");
		Date maxTime; //will be used to ignore faulty lines
		try {
			maxTime = date.parse("24:00:00");
			//Date userInputted = Date.parse(arrivalTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return null;
	}
}
