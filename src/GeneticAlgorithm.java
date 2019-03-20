import java.util.Random;

public class GeneticAlgorithm {

	private int boardsPerGen;
	private int n;
	private float mutateChance;
	private Board[] boards;
	static Random rand;

	public static void main(String[] args) {
		rand = new Random();
		GeneticAlgorithm thisAlgorithm = new GeneticAlgorithm(5, 6, .5f);
		thisAlgorithm.solve();
	}

	public GeneticAlgorithm(int n, int boardsPerGen, float mutateChance) {
		if (boardsPerGen % 2 == 1) {
			System.err.println("Please use an even number of boards.");
		}
		this.boardsPerGen = boardsPerGen;
		this.n = n;
		this.mutateChance = mutateChance;
		boards = new Board[boardsPerGen];
		for (int i = 0; i < boardsPerGen; i++) {
			boards[i] = new Board(n, true);
		}
	}

	public void solve() {
		// calculate all the boards fitnesses
		calculateFitness();
		// select the fittest half of the boards
		selection();
		for (Board b : boards) {
			System.out.println("s" + b.fitness);
		}
		crossover();
		calculateFitness();
		if (rand.nextFloat() > mutateChance) {
			mutate();
			System.out.println("mutate");
		}
		calculateFitness();
	}

	public void mutate() {
		for (int i = 0; i < boardsPerGen; i++) {
			int mutateCol = rand.nextInt(n);
			for (int row = 0; row < n; row++) {
				if (row == mutateCol) {
					boards[i].set(row, mutateCol, true);
				} else {
					boards[i].set(row, mutateCol, false);
				}
			}
		}
	}

	public void calculateFitness() {
		for (int i = 0; i < boardsPerGen; i++) {
			boards[i].calculateFitness();
			System.out.println(boards[i].fitness);
		}
	}

	public void crossover() {
		int splicePoint = rand.nextInt(n);
		for (int i = 0; i < boardsPerGen; i += 2) {
			for (int j = 0; j < splicePoint; j++) {
				swapCol(i, i + 1, j);
			}
			// boards[i].calculateFitness();
			// boards[i+1].calculateFitness();
			// System.out.println(boards[i].fitness);
			// System.out.println(boards[i+1].fitness);
		}

	}

	public void swapCol(int index1, int index2, int col) {
		// go through rows
		for (int i = 0; i < n; i++) {
			// swap elements in this row
			boolean temp = boards[index1].get(i, col);
			boards[index1].set(i, col, boards[index2].get(i, col));
			boards[index2].set(i, col, temp);
		}
	}

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
