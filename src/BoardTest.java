/*import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
	@Test 
	public void createEmptyBoard() {
		Board board = new Board(2, false);
		Assert.assertFalse(board.get(0, 0));
	}
	@Test 
	public void setBoard() {
		Board board = new Board(2, false);
		board.set(0,0,true);
		Assert.assertTrue(board.get(0, 0));
	}
	@Test 
	public void largeBoard() {
		Board board = new Board(20, false);
		board.set(10,10,true);
		Assert.assertTrue(board.get(10, 10));
	}
	@Test 
	public void checkOpenHorizontal() {
		Board board = new Board(2, false);
		board.set(0,0,true);
		Assert.assertFalse(board.isOpen(1,0));
	}
	@Test 
	public void checkOpenVertical() {
		Board board = new Board(2, false);
		board.set(0,0,true);
		Assert.assertFalse(board.isOpen(0,1));
	}
	@Test 
	public void checkOpenDiagonal() {
		Board board = new Board(2, false);
		board.set(0,0,true);
		Assert.assertFalse(board.isOpen(1,1));
	}
	@Test 
	public void checkOpenTrue() {
		Board board = new Board(3, false);
		board.set(0,0,true);
		Assert.assertTrue(board.isOpen(2,1));
	}
	@Test 
	public void checkIsDoneTrue() {
		Board board = new Board(5, false);
		board.set(0,0,true);
		Assert.assertTrue(board.isDone());
	}
}*/
