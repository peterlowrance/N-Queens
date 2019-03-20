import java.util.Random;

public class Board {
	boolean[][] board;
	int size;
	Random rand;
	public float fitness;

	public void calculateFitness() {
		fitness = (size*size - isDoneNumCollisions())/((float) size*size);
	}

	/**
	 * Initializes a board
	 * @param n size of the board
	 * @param initializeRandomly place a queen in each column at a random location
	 */
	public Board(int n, boolean initializeRandomly) {
		if (n < 4) {
			System.err.println("There is no solution to a board of size " + n);
		}
		// create a blank board, it is initialized to 0
		board = new boolean[n][n];
		size = n;
		rand = new Random();
		if (initializeRandomly) {
			for (int i = 0; i < size; i++) {
				board[rand.nextInt(size)][i] = true;
			}
		}
	}

	// getter
	public boolean get(int x, int y) {
		return board[x][y];
	}

	// setter
	public void set(int x, int y, boolean q) {
		board[x][y] = q;
	}

	/**
	 * Returns true if there are no collisions
	 * 
	 * @return true if the board satisfies the constraints
	 */
	public boolean isDone() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j]) {
					if (!isOpen(i, j)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean isOpen(int i, int j) {
		for (int k = 0; k < size; k++) {
			if (board[i][k] && k != j) {
				return false;
			}
		}
		for (int k = 0; k < size; k++) {
			if (board[k][j] && k != i) {
				return false;
			}
		}
		// TODO check diagonals
		/*
		 * for(int k=0; i<size; i++) { if(board[k-i][k-j] && k != j && k != i) { return
		 * false; } }
		 */
		return true;
	}

	/**
	 * Returns the number of collisions. Returns 0 if there are no collisions
	 * 
	 * @return the number of collisions
	 */
	public int isDoneNumCollisions() {
		int numCollisions = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j]) {
					// System.out.println(i + " " + j + " " + isOpen(i,j));
					if (!isOpen(i, j)) {
						// System.out.println("collide");
						numCollisions++;
					}
				}
			}
		}
		return numCollisions;
		// count all collisions one queen at a time and divide by 2
	}
	
	
	public String toString(){
		int numSpacesAcross = this.size*2+3;
		boolean thisSpace;
		String returnStr = "";
		
		for(int i = 0; i <= numSpacesAcross; i++){
			for(int j = 0; j < (this.size + 2); j++){
				if(i == 0 || i == (this.size + 1)){
					if(j == 0 || j == numSpacesAcross - 1){
						returnStr += '+';
					}else{
						returnStr += '-';
					}
				}else if(j == numSpacesAcross){
					returnStr += '\n';
				}else{
					if(j == 0 || j == numSpacesAcross - 1){
						returnStr += '|';
					}else{
						if(j % 2 == 0){
							returnStr += ' '; 
						}else{
							thisSpace = this.get(i - 1, (j - 1)/2);
							if(thisSpace){
								returnStr += '1';
							}else{
								returnStr += '0';
							}
						}
					}
				}
			}
		}
		
		return returnStr;
	}
}
