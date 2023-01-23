/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Lilja Kiiski
 *	@since January 10, 2023
 */

 import java.util.ArrayList;
 import java.util.List;
 
 public class SortMethods {
	
	/**
	 * Sort given array in descending order of names using merge sort
	 * @param arr the array to be sorted
	 */
	public static void descendingNames(List<City> arr){
		int n = arr.size();
		recursiveDescSort(arr, 0, n-1, "names");
	}

	/**
	 * Sort given array in ascending order of names using insertion sort
	 * @param arr the array to be sorted
	 */
	public static void ascendingNames(List<City> arr){
		for (int x = 1; x < arr.size(); x++){
			City temp = arr.get(x);
			
			int n = x;

			//shift until one is greater
			while (n > 0 && temp.getName().compareTo(arr.get(n-1).getName()) < 0){
				arr.set(n, arr.get(n-1)); //shift
				n--;
			}
			arr.set(n, temp);
		}
	}

	/**
	 * Sort given array in descending order of population using merge sort
	 * @param arr the array to be sorted
	 */
	public static void descendingPopulation(List<City> arr){
		int n = arr.size();
		recursiveDescSort(arr, 0, n-1, "population");
	}

	/**
	 * Recurse and split array using mergesort
	 * @param arr array to recurse through
	 * @param from initial start point to start from
	 * @param to point to end to
	 * @param type either "population" or "names" depending on how to sort
	 */
	private static void recursiveDescSort(List<City> arr, int from, int to, String type){
		//Base case (maybe switch) 1 or 2 elements
		if (to - from < 2){
			if ((type.equals("population") && //Handle for population
				to > from && arr.get(to).compareTo(arr.get(from)) > 0) || 
				(type.equals("names") && //Handle for names
				to > from && arr.get(to).getName().compareTo(arr.get(from).getName()) > 0 )){
				
				swap(arr, to, from);
			}
		//We gotta recurse
		} else {
			int middle = (from + to) / 2;
			
			recursiveDescSort(arr, from, middle, type);
			recursiveDescSort(arr, middle+1, to, type);
			mergeDesc(arr, from, middle, to, type);
		}
	}
	
	/**
	 * Merge a section of an array
	 * @param arr the array to merge
	 * @param from start point for merging
	 * @param middle middle point for merging
	 * @param to end point for merging
	 * @param type either "population" or "names" affects how to merge
	 */
	private static void mergeDesc(List<City> arr, int from, int middle, int to, String type){
		int i = from, j = middle + 1, k = from;
		int n = arr.size();
		List<City> temp = new ArrayList<City>();

		//Fill with empty items
		for (int x = 0; x < n; x++){
			temp.add(null);
		}
		
		//While both arrays have elements to be processed
		while (i <= middle && j <= to){
			if ((type.equals("population") && //Handle for population
				arr.get(i).compareTo(arr.get(j)) >= 0) ||
				(type.equals("names") && //Handle for names
				arr.get(i).getName().compareTo(arr.get(j).getName()) >= 0)){

				temp.set(k, arr.get(i));
				i++;
			} else {
				temp.set(k, arr.get(j));
				j++;
			}
			k++;
		}
		
		//Tail of first half, into temp
		while (i <= middle){
			temp.set(k, arr.get(i));
			i++;
			k++;
		}
		
		//Tail of second half into temp
		while (j <= to){
			temp.set(k, arr.get(j));
			j++;
			k++;
		}
		
		//Copy from temp to a
		for (k = from; k <= to; k++){
			arr.set(k, temp.get(k));
		}
	}

	/**
	 * Cities in ascending population order using selection sort
	 * @param arr array to sort
	 */
	public static void ascendingPopulation(List<City> arr){
		int pointerIndex; //Pointer index to curr
		
		for (int x = 0; x < arr.size(); x++){
			pointerIndex = 0;
			for (int y = pointerIndex+1; y < arr.size()-x; y++){
				if (arr.get(pointerIndex).compareTo(arr.get(y)) < 0){
					pointerIndex = y;
				}
				
				if (y == arr.size()-x-1){
					swap(arr, pointerIndex, y);
				}
			}
		}
	}
	
	/**
	 * Swap two items in a cities array
	 * @param arr array to swap two items in
	 * @param x index of first item
	 * @param y index of second item
	 */
	private static void swap(List<City> arr, int x, int y) {
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
}