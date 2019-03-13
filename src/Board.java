
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
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(board[i][j]){
					if(!isOpen(i,j)){
						return false;
					}
				}
			}
		}
		return true;
	}

/**
 * Returns the number of collisions. Returns 0 if there are no collisions
 * @return      the image at the specified URL
 */
	public int isDoneNumCollisions(){
		int numCollisions = 0;
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(board[i][j]){
					if(!isOpen(i,j)){
						numCollisions++;
					}
				}
			}
		}
		return numCollisions;
		// count all collisions one queen at a time and divide by 2
	}
}
