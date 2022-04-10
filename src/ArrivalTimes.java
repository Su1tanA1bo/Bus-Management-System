import java.text.*;
import java.util.*;
import java.io.*;
import java.util.Date.*;

public class ArrivalTimes {
	
	public static ArrayList<String> findTrips(String arrivalTime) throws FileNotFoundException{
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date maxTime; //will be used to ignore faulty lines
		String line;
		try {
			Date userInputDate = format.parse(arrivalTime);
			maxTime = format.parse("24:00:00");
			ArrayList<String> CorrectTrips = new ArrayList<String>();
			File fileName = new File("stop_times.txt");
			Scanner scanner = new Scanner(fileName);
			Scanner lineScanner = null;
			while(scanner.hasNextLine()) 
			{
				line = scanner.nextLine();
				lineScanner = new Scanner(line);//will hold every line of stop times txt
				lineScanner.useDelimiter(",");
				lineScanner.next();
				//now on arrival time
				String currentLineTime = lineScanner.next();
				Date currentTimeFormatted = format.parse(currentLineTime);
				if(userInputDate.getTime() == currentTimeFormatted.getTime() && currentTimeFormatted.getTime() < maxTime.getTime()) 
				{
					//here only correct arrrival times will go
					CorrectTrips.add(line);
				}
			}
			//all correct times are now in CorrectTrips
			//will be sorted in ascending order
			CorrectTrips.sort(Comparator.naturalOrder());	
			return CorrectTrips;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return null;
	}
	
}
