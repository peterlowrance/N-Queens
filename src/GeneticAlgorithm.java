import java.util.Random;

public class GeneticAlgorithm {

	private int boardsPerGen;
	private int n; //size of the boards
	private float mutateChance;
	private Board[] boards;
	static Random rand;

	public static void main(String[] args) {
		rand = new Random();
		GeneticAlgorithm thisAlgorithm = new GeneticAlgorithm(4, 6, .5f);
		System.out.println("Solution: " + thisAlgorithm.solve().fitness);
	}

	//constructor that creates random boards
	public GeneticAlgorithm(int n, int boardsPerGen, float mutateChance) {
		//if number of boards is odd, throw an error
		if (boardsPerGen % 2 == 1) {
			throw new java.lang.UnsupportedOperationException("Please use an even number of boards.");
		}
		this.boardsPerGen = boardsPerGen;
		this.n = n;
		this.mutateChance = mutateChance;
		boards = new Board[boardsPerGen];
		//initialize all boards
		for (int i = 0; i < boardsPerGen; i++) {
			boards[i] = new Board(n, true);
		}
	}

	//use a genetic algorithm to find a solution to the n-queens problem
	public Board solve() {
		//loop until a solution is found
		while (true) {
			// calculate all the boards fitnesses
			calculateFitness();
			//if a fitness is 1, then that is a solution
			for (Board b : boards) {
				if(b.fitness == 1) {
					return b;
				}
				System.out.println("s" + b.fitness);
			}
			//select the fittest half of the boards
			selection();
			//breed two parents into two children boards
			crossover();
			calculateFitness(); //remove
			//mutate the boards with a random chance
			if (rand.nextFloat() > mutateChance) {
				mutate();
				System.out.println("mutate");
			}
			calculateFitness(); //remove
		}
	}

	//change a column of each board at random
	public void mutate() {
		//go through all the boards
		for (int i = 0; i < boardsPerGen; i++) {
			int mutateCol = rand.nextInt(n); //column to mutate
			int mutateRow = rand.nextInt(n); //row to mutate
			//change all the places in that column, setting one to the mutated place
			for (int row = 0; row < n; row++) {
				if (row == mutateRow) {
					boards[i].set(row, mutateCol, true);
				} else {
					boards[i].set(row, mutateCol, false);
				}
			}
		}
	}

	//calculate how fit a board is based on how many collisions there are
	public void calculateFitness() {
		for (int i = 0; i < boardsPerGen; i++) {
			boards[i].calculateFitness();
			System.out.println(boards[i].fitness); //remove
		}
	}

	//swap half of the board the next board
	public void crossover() {
		int splicePoint = rand.nextInt(n); //choose a random point to splice
		//go through each board, 2 at a time
		for (int i = 0; i < boardsPerGen; i += 2) {
			//go through the columns of this board
			for (int j = 0; j < splicePoint; j++) {
				//swap all the columns with the next board
				swapCol(i, i + 1, j);
			}
		}

	}

	//swap the given column of the board at index1 with index2 
	public void swapCol(int index1, int index2, int col) {
		// go through rows
		for (int i = 0; i < n; i++) {
			// swap elements in this row
			boolean temp = boards[index1].get(i, col);
			boards[index1].set(i, col, boards[index2].get(i, col));
			boards[index2].set(i, col, temp);
		}
	}

	//
	public void selection() {
		// sort the first half by fitness
		bubbleSort(boards, boardsPerGen / 2);
		// duplicate the boards so there are
		for (int i = 0; i < boardsPerGen / 2; i++) {
			boards[i] = boards[boardsPerGen / 2 + i];
		}
	}

	public static void bubbleSort(Board[] arr, int k) {
		int n = arr.length;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j].fitness > arr[j + 1].fitness) {
					// swap arr[j+1] and arr[i]
					Board temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
}
