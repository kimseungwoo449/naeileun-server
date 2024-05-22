package board.model;

public class BoardDao {

	private BoardDao() {
		
	}
	
	private static BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance() {
		return instance;
	}
	
	
}
