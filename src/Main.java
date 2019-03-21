import java.util.Scanner;

public class Main {
	public static Scanner in;
	static long startTime;

	public static void main(String[] args) {
		in = new Scanner(System.in);
		System.out.print("n-Queens algorithms. By Peter Lowrance and Nathaniel Sprecher.\nEnter 1 for genetic algorithm, 2 for backtracking algorithm, or 3 for both: ");
		int input = in.nextInt();
		System.out.print("Enter the size for n: ");
		int n = in.nextInt();
		if(input == 1 || input == 3) {
			RunGen(n);
		}
		if(input == 2 || input == 3) {
			RunBack(n);
		}
	}
	
	public static void RunGen(int n) {
		System.out.print("Enter an even number for the number of boards per generation: ");
		int boardsPerGen = in.nextInt();
		System.out.print("Enter the chance for a mutation to occur (.5 for 50% chance): ");
		float mutationChance = in.nextFloat();
		GeneticAlgorithm thisAlgorithm = new GeneticAlgorithm(n, boardsPerGen, mutationChance);
		System.out.println("\nGenetic Algorithm:");
		//start timer
		startTime = System.currentTimeMillis();
		System.out.print("Solution: \n" + thisAlgorithm.solve());
		System.out.println("Found in " + (System.currentTimeMillis() - startTime)/1000f + " seconds.");
		
	}
	
	public static void RunBack(int n) {
		Board brd = new Board(n, false);		
		
		BacktrackingAlgorithm bta = new BacktrackingAlgorithm();
		//star timer
		startTime = System.currentTimeMillis();
		bta.backtracking(brd, 0);
		System.out.println("\nBacktracking Algorithm:");
		if(brd.isDone()){
			System.out.println("No collisions in solution.");
		}else{
			System.out.println("There was a collision.");
		}
		System.out.print(brd.toString());
		System.out.println("Found in " + (System.currentTimeMillis() - startTime)/1000.0 + " seconds.");
	}

}
