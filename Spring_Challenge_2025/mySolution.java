import java.util.*;
import java.io.*;
import java.math.*;

/**
 * comment here
 **/

class Grid{

}


class Player {



	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int depth = in.nextInt();
		int final_sum = 0;
		StringBuilder sb =  new StringBuilder();
		List<List<Integer>> initialGrid = new ArrayList<>();
		System.err.println("depth = " + depth);
		for (int i = 0; i < 3; i++) {
			List<Integer> tempList = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				int value = in.nextInt();
				tempList.add(value);
				//sb.append(value);
			}
			initialGrid.add(tempList);
		}

		for (int i = 0; i < 3; i++){
			System.err.println(initialGrid.get(i).toString());
		}



		System.err.print(Hash_Calcul(initialGrid));

		//final_sum = final_sum + Integer.parseInt(sb.toString());


		// Write an action using System.out.println()
		// To debug: System.err.println("Debug messages...");

		System.out.println(final_sum);
	}

	public static int Hash_Calcul(List<List<Integer>> grid){
		//check if playable
		if (Playable(grid)){
			return 1;
		}
		return 0;
	}

	public static boolean Playable(List<List<Integer>> grid){
		for (int i = 0; i < grid.size(); i++){
			for (int j = 0; j<grid.get(0).size(); j++){
				if (grid.get(i).get(j) == 0){
					return true;
				}
			}
		}
		return false;
	}

}
