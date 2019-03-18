
public class GeneticAlgorithm {

	private int boardsPerGen;
	private int n;
	private Board[] boards;

	public static void main(String[] args) {
		GeneticAlgorithm thisAlgorithm = new GeneticAlgorithm(4, 4);

	}

	public GeneticAlgorithm(int n, int boardsPerGen) {
		this.boardsPerGen = boardsPerGen;
		this.n = n;
		boards = new Board[boardsPerGen];
		for (Board b : boards) {
			b = new Board(n, true);
			System.out.println(b.getFitness());
		}
	}

	public Board solution() {
		return null;
	}

	public Board[] bestHalf(Board[] boards) {
		int[] fitnesses = new int[boardsPerGen];
		for (int i = 0; i < boardsPerGen; i++) {
			fitnesses[i] = boards[i].isDoneNumCollisions();
		}
		int[] greatestFitness = new int[boardsPerGen / 2];
		for (int i = 0; i < boardsPerGen; i++) {
			for (int greatest : greatestFitness) {
				if (fitnesses[i] > greatest) {

				}
			}
			fitnesses[i] = boards[i].isDoneNumCollisions();
		}
	}

	void bubbleSort(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (arr[j] > arr[j + 1]) {
					// swap arr[j+1] and arr[i]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
	}
}
