package board.model;

public class BoardRequestDto {
	private int boardCode;
	private String boardName;
	private String description;
	private String title;
	private String content;
	private int userCode;
	private int recommandation;
	private int postCode;
	
	// 기본 생성자
	public BoardRequestDto() {
		super();
	}

	// 게시판 설명 제되 생성자
	public BoardRequestDto(int boardCode, String boardName, String title, String content, int userCode,
			int recommandation, int postCode) {
		super();
		this.boardCode = boardCode;
		this.boardName = boardName;
		this.title = title;
		this.content = content;
		this.userCode = userCode;
		this.recommandation = recommandation;
		this.postCode = postCode;
	}

	// 게시판 설명 포함 셍성자
	public BoardRequestDto(int boardCode, String boardName, String description, String title, String content,
			int userCode, int recommandation, int postCode) {
		super();
		this.boardCode = boardCode;
		this.boardName = boardName;
		this.description = description;
		this.title = title;
		this.content = content;
		this.userCode = userCode;
		this.recommandation = recommandation;
		this.postCode = postCode;
	}

	
}
