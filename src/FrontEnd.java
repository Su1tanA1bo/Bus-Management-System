
import java.util.*;


public class FrontEnd {
	
	
	public static boolean userQuery() 
	{
		System.out.println("What program would you like to run?");
        System.out.println("1 : Finding shortest paths between 2 bus stops");
        System.out.println("2 : Search information about a bus stop using the bus stop name");
        System.out.println("3 : Search information about a bus stop using the arrival time");
        System.out.println("4 : Exit Program");
        
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        try{
        	userInput = scanner.nextInt();
        }catch (Exception e){
        	System.out.println("Invalid Input. Your input must be an integer");
        }
        if(userInput == 1) 
        {
        	//finding shortest path
        }
        if(userInput == 2) 
        {
        	//Search information about a bus stop using bus stop name
        	System.out.println("Enter stop name\n");
        	 try{
        		 int counter = 0; //will be used to see how many results are printed
        		 TST tst = new TST();
                 String userInputStop = scanner.next().toUpperCase();
                 Iterable<String> allStops = tst.keysWithPrefix(userInputStop);
            
                 for (String i : allStops) 
                 {
                	 counter++;
                     System.out.println("" + tst.get(i));
                 }
                 
                 if (counter == 0) {
                	 System.out.println("No matching stops were found\n");
                 }
             } catch (Exception e) {
                System.out.println(e);
             }
        }
        if(userInput == 3) 
        {
        	//Search info using arrival time
        }
        if(userInput == 4) 
        {
        	//terminate
        	return false;
        }
        return true;
	}
	public static void main(String[] args) {
		boolean quitter = true;
		while(quitter) 
		{
			quitter = userQuery();
			
		}
	}

}
