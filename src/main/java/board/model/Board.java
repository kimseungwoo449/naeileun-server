package board.model;

import java.sql.Timestamp;

public class Board {
	private int boardCode;
	private String boardName;
	private String description;
	private Timestamp createDate;
	private String title;
	private String content;
	private int userCode;
	private Timestamp writeDate;
	private Timestamp updateDate;
	private int recommandation;
	private int postCode;
	
	// 보드 카테고리 생성자
	public Board(String boardName, String description) {
		super();
		this.boardName = boardName;
		this.description = description;
	}

	// 보드 카테고리 생성자(설명X)
	public Board(String boardName) {
		super();
		this.boardName = boardName;
	}

	// 게시글 생성자
	public Board(String title, String content, int userCode) {
		super();
		this.title = title;
		this.content = content;
		this.userCode = userCode;
	}

	
}
