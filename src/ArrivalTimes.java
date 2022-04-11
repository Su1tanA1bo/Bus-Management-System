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
			//System.out.println(userInputDate);
			maxTime = format.parse("24:00:00");
			ArrayList<String> CorrectTrips = new ArrayList<String>();
			File fileName = new File("stop_times.txt");
			Scanner scanner = new Scanner(fileName);
			scanner.nextLine();
			Scanner lineScanner = null;
			int globalCounter = 0;
			if(userInputDate.getTime() > maxTime.getTime()) 
			{
				System.out.println("Your answer must be less than 24:00:00");
				return null;
				
			}
			while(scanner.hasNext()) 
			{
				globalCounter++;
				//System.out.println(globalCounter);
				line = scanner.nextLine();
				lineScanner = new Scanner(line);//will hold every line of stop times txt
				lineScanner.useDelimiter(",");
				lineScanner.next();
				//now on arrival time
				String currentLineTime = lineScanner.next();
				//.out.println(currentLineTime);
				Date currentTimeFormatted = format.parse(currentLineTime);
				//System.out.println(currentTimeFormatted);
				if(userInputDate.getTime() == currentTimeFormatted.getTime()) 
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
			System.out.println("Please make sure your answer is in the format: HH:MM:SS");
		}
			
		return null;
	}
	
}
