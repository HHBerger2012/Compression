import java.util.ArrayList;

public class City {
	public String name;
	public int inDeg;
	private  Boolean _hasVisited;
	private ArrayList<Flight> Flights = new ArrayList<Flight>();
	
	 
		public City(String name) {
			this.name = name;
			_hasVisited = false;
			inDeg = 0;
		}
		public void addInDeg() {
			++inDeg;
		}
		public void addFlightTo(City x, int Price) {
			Flights.add(new Flight(x, Price));
		}
		public boolean hasVisited() {
			return _hasVisited;
		}
		public void Visited() {
			_hasVisited = true;
		}
		public ArrayList<Flight> getFlights() {
			return Flights;
		}
		
}