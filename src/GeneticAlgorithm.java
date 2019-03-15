
public class GeneticAlgorithm {
	public static final int NUM_BOARDS_PER_GEN = 4;
	public static final int N_SIZE = 4;
	
	private Board[] boards;
	
	public static void main(String[] args) {
		GeneticAlgorithm thisAlgorithm = new GeneticAlgorithm(4,4);
		
	}
	
	public GeneticAlgorithm(int n, int boardsPerGen){
		boards = new Board[boardsPerGen];
		for(Board b: boards) {
			b = new Board(n);
		}
	}
	
	public Board solution() {
		return null;
	}
}
