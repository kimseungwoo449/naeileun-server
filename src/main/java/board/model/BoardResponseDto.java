package board.model;

import java.sql.Timestamp;

public class BoardResponseDto {
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
	
	public BoardResponseDto(int boardCode, String boardName, String description, Timestamp createDate, String title,
			String content, int userCode, Timestamp writeDate, Timestamp updateDate, int recommandation, int postCode) {
		super();
		this.boardCode = boardCode;
		this.boardName = boardName;
		this.description = description;
		this.createDate = createDate;
		this.title = title;
		this.content = content;
		this.userCode = userCode;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
		this.recommandation = recommandation;
		this.postCode = postCode;
	}

	public BoardResponseDto(int boardCode, String boardName, Timestamp createDate, String title, String content,
			int userCode, Timestamp writeDate, Timestamp updateDate, int recommandation, int postCode) {
		super();
		this.boardCode = boardCode;
		this.boardName = boardName;
		this.createDate = createDate;
		this.title = title;
		this.content = content;
		this.userCode = userCode;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
		this.recommandation = recommandation;
		this.postCode = postCode;
	}

	
}
