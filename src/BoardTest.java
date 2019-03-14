
public class BoardTest {
	public static void main(String[] args) {
		Board board = new Board(2);
		board.set(0,0, true);
		System.out.println(board.isDone());
		System.out.println("Finished");
	}
}
