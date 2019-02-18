
public class Board {
	boolean[][] board;
	int size;

	// constructor
	public Board(int n) {
		// create a blank board, it is initialized to 0
		board = new boolean[n][n];
		size = n;
	}

	// getter
	public boolean get(int x, int y) {
		return board[x][y];
	}

	// setter
	public void set(int x, int y, boolean q) {
		board[x][y] = q;
	}

	public boolean isDone() {
		// TODO: check conditions
		boolean rowQ = false;
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				//check the condition along each row
				if(board[i][j]) {
					if(rowQ) return false;
					else rowQ = true;
				}
			}
			rowQ = false;
		}
		return false;
	}
}
