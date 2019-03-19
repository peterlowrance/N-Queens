import java.util.Random;

public class GeneticAlgorithm {

	private int boardsPerGen;
	private int n;
	private Board[] boards;
	static Random rand;

	public static void main(String[] args) {
		rand = new Random();
		GeneticAlgorithm thisAlgorithm = new GeneticAlgorithm(4, 4);
		thisAlgorithm.solution();
	}

	public GeneticAlgorithm(int n, int boardsPerGen) {
		if(boardsPerGen % 2 == 1) {
			System.err.println("Please use an even number of boards.");
		}
		this.boardsPerGen = boardsPerGen;
		this.n = n;
		boards = new Board[boardsPerGen];
		for (int i=0; i<boardsPerGen; i++) {
			boards[i] = new Board(n, true);
			System.out.println(boards[i].getFitness());
		}
	}

	public Board solution() {
		Board[] bHalf = bestHalf(boards);
		for(Board b: bHalf) {
			System.out.println(":" + b.getFitness());
		}
		return null;
	}
	
	public Board[] spliceDoulbe(Board[] boards) {
		Board[] splicedBoards = new Board[boardsPerGen];
		for(int i=0; i< boards.length; i+= 2) {
			int splicePoint = rand.nextInt(n);
			Board newBoard1 = new Board(n, false);
			for(int j=0; i<n; j++) {
				if(j < splicePoint) {
					setCol(newBoard1, boards[i], j);
				}
				else {
					setCol(newBoard1, boards[i+1], j);
				}
			}
			splicedBoards[i] = newBoard1;
			Board newBoard2 = new Board(n, false);
			for(int j=0; i<n; j++) {
				if(j < splicePoint) {
					setCol(newBoard2, boards[i+1], j);
				}
				else {
					setCol(newBoard2, boards[i], j);
				}
			}
			splicedBoards[i+1] = newBoard2;
		}
		return splicedBoards;
	}
	
	public void setCol(Board getCol, Board setCol, int col) {
		for(int i=0; i<n; i++) {
			setCol.set(i, col, getCol.get(i, col));
		}
	}

	public Board[] bestHalf(Board[] boards) {
		float[] fitnesses = new float[boardsPerGen];
		for (int i = 0; i < boardsPerGen; i++) {
			fitnesses[i] = boards[i].getFitness();
		}
		bubbleSort(fitnesses, boardsPerGen/2);

		Board[] bestHalf = new Board[boardsPerGen/2];
		for (int i = 0; i < boardsPerGen/2; i++) {
			for(Board b: boards) {
				if(Math.abs(b.getFitness() - fitnesses[fitnesses.length - i - 1]) < 0.0001) {
					bestHalf[i] = b;
					break;
				}
			}
		}
		return bestHalf;
	}

	public static void bubbleSort(float arr[], int k) {
		int n = arr.length;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					// swap arr[j+1] and arr[i]
					float temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
}
