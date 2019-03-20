
public class BacktrackingAlgorithm {
	//recursive method to solve N-queens via backtracking
	boolean backtracking(Board brd, int column){
		//Base case: if we have reached the right-end side of the board, return true
		if(column >= brd.size){
			return true;
		}
		
		//Loop through each row for given column
		for(int i = 0; i < brd.size; i++){
			//if the current row is open, place a queen and recursively call
			if(brd.isOpen(i, column)){
				//place queen
				brd.set(i, column, true);
				
				//recursively call to next column, if it works, return true, else, remove queen
				if(backtracking(brd, column + 1)){
					return true;
				}else{
					brd.set(i, column, false);
				}
			}
		}
		
		//if we reach the end of the column, return false
		return false;
	}
}
