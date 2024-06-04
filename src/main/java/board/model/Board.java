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
	private int recommendation;
	private int postCode;
	private int commentCount;
	
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

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
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

	public Timestamp getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public int getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public int getCommentCount() { return commentCount; }

	public void setCommentCount(int commentCount) { this.commentCount = commentCount; }
}
