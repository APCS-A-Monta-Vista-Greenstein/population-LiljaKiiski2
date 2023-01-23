/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Lilja Kiiiski
 *	@since January 10, 2023
 */
public class City implements Comparable<City> {
	/** fields */
	private String name;
	private String state;
	private String type;
	private int population;
	
	// constructor
	public City(String state, String name, String type, int population){
		this.name = name;
		this.state = state;
		this.type = type;
		this.population = population;
	}

	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	@Override
	public int compareTo(City o){
		if (population != o.population){
			return population - o.population;
		
		} else if (!state.equals(o.state)){
			return state.compareTo(o.state);
		
		} else {
			return name.compareTo(o.name);
		}
	}
	
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof City &&
			((City)o).name.equals(name) &&
			((City)o).state.equals(state) &&
			((City)o).type.equals(type) &&
			((City)o).population == population){
				return true;
		}
		return false;
	}
	
	
	/**	Accessor methods */
	public String getName() { return name; }
	
	public String getState() { return state; }
	
	public String getType() { return type; }
	
	public int getPopulation() { return population; }
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, type,
						population);
	}
}
