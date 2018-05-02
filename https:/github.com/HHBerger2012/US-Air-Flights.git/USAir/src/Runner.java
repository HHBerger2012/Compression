
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;
/*
 USAir Project by Michael Shea and Henry Berger
 05.02.2018
 This project demonstates maps and paths between them
 */



public class Runner {
	static ArrayList<City> Cities = new ArrayList<City>();
	static City From;
	static City To;
	
	static Deque<Flight> stack = new ArrayDeque<Flight>();

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scnr = new Scanner(System.in);
		System.out.print("Enter City File name: ");
		String cityName = scnr.next();
		loadCities(cityName);
		System.out.print("Enter Flight File name: ");
		String flightName = scnr.next();
		loadFlights(flightName);
		int keepGoing = 1;
		
		while(keepGoing == 1){
		Cities = new ArrayList<City>();
		loadCities(cityName);	
		loadFlights(flightName);	
		getCities();
		Boolean pathFound = false;
		Boolean pathExist = true;
		stack = new ArrayDeque<Flight>();
		
		
		From.Visited();
		//stack.push(From);
		Flight current = new Flight(From, 0);
		stack.push(current);

		
		
		while(!pathFound) {
			
			current = getNextToVisit(current);
			

			
			if(current == null) {
				stack.pop();
				
				if(stack.isEmpty()) {
					pathFound = true;
					pathExist = false;
					break;
				}
				current = stack.pop();
			}
			
			
			
			
			stack.push(current);
			if(current.to.name.equals(To.name)) {
				pathFound = true;
			}
		
			
			
			
			
		}
		if(pathExist) {	
			print();	
			}else {
				System.out.println("Sorry, USAir does not fly from " + From.name + " to " + To.name);
			}	
		
		System.out.println("Would you like to run again? (yes or no)");
		if(!scnr.next().equals("yes")){
			keepGoing = 0;
		}
		
		
		
		
		}

	}
	private static Flight getNextToVisit(Flight to) {
		for(int i = 0; i < to.to.getFlights().size(); ++ i){
			//System.out.println(to.to.getFlights().get(i).to.name);
			if(to.to.getFlights().get(i).to.hasVisited() == false) {
				to.to.getFlights().get(i).to.Visited();
				return to.to.getFlights().get(i);
			}	
		}
		//System.out.println(getCity("Chicago").getFlights().get(1).to.name);
		return null;
	}
	public static void loadCities(String x) throws FileNotFoundException {
		String name = "";
		Scanner scnr = null;
		try{
			scnr = new Scanner(new File(x));
		}catch(FileNotFoundException e) {
			System.out.print(e);
		}
		
		while(scnr.hasNext()){
			Cities.add(new City(scnr.nextLine()));	
		}
	
		
		
		}
	public static void loadFlights(String x) throws FileNotFoundException {
		String line = "";
		Scanner scnr = null;
		try{
			scnr = new Scanner(new File(x));
		}catch(FileNotFoundException e) {
			System.out.print(e);
		}
		String[] lineArray;
		City start = null;
		City end = null;
		while(scnr.hasNextLine()){
			//System.out.println("hi");
			line = scnr.nextLine();
			lineArray = line.split(",");
			//System.out.println(lineArray[0] + " " + lineArray[1] + " " + lineArray[2] + " ");
			start = binarySearch(lineArray[0]);
			//System.out.println(start.name);
			end = binarySearch(lineArray[1].trim());
			//System.out.println(lineArray[2].trim());
			start.addFlightTo(end, Integer.parseInt(lineArray[2].trim()));
			
			
			
			
			
		}
		
		
		}

	
	
	public static void getCities() {
		String name = "";
		Scanner scnr = new Scanner(System.in);
		System.out.println("Where would you like to fly from?");
		name = scnr.nextLine();
		From = binarySearch(name);
		if(From == null) {
			System.out.print("Sorry, USAir Does not serve " + name);
			System.exit(0);
			}
		System.out.println("Where would you like to fly to?");
		name = scnr.nextLine();
		To = binarySearch(name);
		if(From == null) {
			System.out.print("Sorry, USAir Does not serve " + name);
			System.exit(0);
			}
		
		
	}
	
	public static City binarySearch(String x) {
	    int low = 0;
	    int high = Cities.size() - 1;
	    int mid;

	    while (low <= high) {
	        mid = (low + high) / 2;

	        if (Cities.get(mid).name.compareTo(x) < 0) {
	            low = mid + 1;
	        } else if (Cities.get(mid).name.compareTo(x) > 0) {
	            high = mid - 1;
	        } else {
	            return Cities.get(mid);
	        }
	    }

	    return null;
	}
	
	
	
	
//	public static City getCity(String name) {
//		for(int i = 0; i < Cities.size(); ++i) {
//			if(Cities.get(i).name.equals(name)) {
//				return Cities.get(i);
//			}
//		}
//		
//		return null;
//	}
	public static void print() {
		Deque<Flight> stack2 = new ArrayDeque<Flight>();
		Flight past = null;
		int totalCost = 0;
		while(!stack.isEmpty()) {
		past = stack.peek();
		stack2.push(stack.pop());
		}
		stack2.pop();
		System.out.println("Request to fly from " + From.name + " to " + To.name );
		while(!stack2.isEmpty()) {
			totalCost += stack2.peek().price;
			System.out.println("Flight From " + past.to.name + " to " + stack2.peek().to.name+ "\tCost:" + stack2.peek().price);
			past = stack2.pop();
			}
		System.out.println("Total Cost...............$" + totalCost);
	}

}
