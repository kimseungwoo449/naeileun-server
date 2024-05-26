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
	
	// 게시판 카테고리 생성 생성자
	public BoardRequestDto(String boardName, String description) {
		super();
		this.boardName = boardName;
		this.description = description;
	}
	
	// 게시판 카테고리 게시판 설명 제외 생성자
	public BoardRequestDto(String boardName) {
		super();
		this.boardName = boardName;
	}
	

	// 게시판 설명 제외 생성자
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

	public int getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public int getRecommandation() {
		return recommandation;
	}

	public void setRecommandation(int recommandation) {
		this.recommandation = recommandation;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	
}
