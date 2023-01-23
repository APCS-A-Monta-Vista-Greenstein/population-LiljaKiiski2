/**
 *	Population - a program that displays cool informationabout 
 *  the US states with a menu
 * 
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Lilja Kiiski
 *	@since January 10, 2023
 */
 
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
 
public class Population {
	// List of cities
	private List<City> allCities;
	private List<City> stateCities;
	private List<City> nameCities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	//Constructor
	public Population(){
		allCities = new ArrayList<>(); //all cities
		stateCities = null; //state cities
		nameCities = null; //names of cities
		readData();
	}
	
	/** main */
	public static void main(String[] args){
		Population p = new Population();
		p.run();
	}

	/** Run population */
	public void run(){
		printIntroduction();
		System.out.println(allCities.size() + " cities in database");

		int selection = 0;
		long startTime;

		while (selection != 9){
			selection = getNumChoice();
			startTime = System.currentTimeMillis();
			
			//Go through different selections
			switch (selection){
				case 1:
					SortMethods.ascendingPopulation(allCities);
					System.out.println("Fifty least populous cities");
					break;
				case 2:
					SortMethods.descendingPopulation(allCities);
					System.out.println("Fifty most populous cities");
					break;
				case 3:
					SortMethods.ascendingNames(allCities);
					System.out.println("Fifty cities sorted by name");
					break;
				case 4:
					SortMethods.descendingNames(allCities);
					System.out.println("Fifty cities sorted by name descending");
					break;
				case 5:
					String state = getState();
					stateCities = getStateCities(allCities, state);
					SortMethods.descendingPopulation(stateCities);
					System.out.println("Fifty most populous cities in " + state);
					break;
				case 6:
					String name = Prompt.getString("Enter city name");
					nameCities = getNameCities(allCities, name);
					SortMethods.descendingPopulation(nameCities);
					System.out.println("City " + name + " by population");
					break;
			}
			long elapsedTime = System.currentTimeMillis() - startTime;

			//Print out cities and cime
			if (selection >= 1 && selection <= 4){
				printCities(allCities, 50);
				System.out.println("\nElapsed Time: " + elapsedTime + " milliseconds");
			
			} else if (selection == 5){
				printCities(stateCities, 50);

			} else if (selection == 6){
				printCities(nameCities, nameCities.size());
			
			} else {
				System.out.println("Thanks for using Population!");
			}
		}
	}

	/**
	 * Give all cities with a certain name
	 * @param arr array of all cities
	 * @param name the name city should have
	 * @return new array with cities with certain name
	 */
	public List<City> getNameCities(List<City> arr, String name){
		List<City> nameCities = new ArrayList<City>();

		//Collect all 
		for (City city : arr){
			if (city.getName().equals(name)){
				nameCities.add(city);
			}
		}

		return nameCities;
	}

	/**
	 * Give all cities in certain state
	 * @param arr array of all cities
	 * @param state the state to get cities from
	 * @return new array with cities from certain state
	 */
	public List<City> getStateCities(List<City> arr, String state){
		List<City> stateCities = new ArrayList<City>();

		//Collect all 
		for (City city : arr){
			if (city.getState().equals(state)){
				stateCities.add(city);
			}
		}

		return stateCities;
	}
	
	/**
	 * Read data from input file and place in cities list
	 */
	public void readData(){
		Scanner utils = FileUtils.openToRead(DATA_FILE).useDelimiter("[\t\n]");
		
		while (utils.hasNext()){
			City city = new City(utils.next(), 
									utils.next(), 
									utils.next(), 
									utils.nextInt());
			
			allCities.add(city);
		}
	}

	/**
	 * Print cities from list
	 */
	 public void printCities(List<City> cities, int max) {
		System.out.println(String.format("    %-22s %-22s %-12s %-12s", "State", "Name", "Type", "Population"));

		for (int x = 0; x < max; x++){
			if (x < 9){
				System.out.println((x+1) + " : " + cities.get((x)));
			} else {
				System.out.println((x+1) + ": " + cities.get((x)));
			}
		}
	}
	
	/**
	 * Prompts user for a state name until found
	 * @return state name from user
	 */
	public String getState(){
		String[] allStates = {"Alaska", "Alabama", "Arkansas", "Arizona", "California", "Colorado", 
			"Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Iowa", "Idaho", "Illinois", "Indiana", 
			"Kansas", "Kentucky", "Louisiana", "Massachusetts", "Maryland", "Maine", "Michigan", "Minnesota", 
			"Missouri", "Mississippi", "Montana", "North Carolina", "North Dakota", "Nebraska", "New Hampshire",
			"New Jersey", "New Mexico", "Nevada", "New York", "Ohio", "Oklahoma", "Oregon", "Pennsylvania",
			"Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Virginia", "Vermont", 
			"Washington", "Wisconsin", "West Virginia", "Wyoming"};
		boolean goodState = false;
		String state = null;

		while (!goodState){
			state = Prompt.getString("Enter state name (ie. Alabama)");

			//If state exists return the state
			for (String s : allStates){
				if (s.equals(state)){
					goodState = true;
				}
			}

			if (!goodState){
				System.out.println("ERROR: " + state + " is not valid");
			}
		}

		return state;
	}

	/**
	 * Let user select a choice from 1-6 or 9
	 * @return user selection
	 */
	public int getNumChoice(){
		int selection = 0;

		while (selection < 1 || (selection > 6 && selection != 9)){
			printMenu();

			selection = Prompt.getInt("Enter selection");
		}
		System.out.println();

		return selection;		
	}

	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("\n1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}	
}