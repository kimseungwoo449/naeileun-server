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
	private String userId;
	private Timestamp writeDate;
	private Timestamp updateDate;
	private int recommendation;
	private int postCode;
	private String imagePath;
	
	public BoardResponseDto(int boardCode, String boardName, String description, Timestamp createDate, String title,
			String content, int userCode, Timestamp writeDate, Timestamp updateDate, int recommendation, int postCode) {
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
		this.recommendation = recommendation;
		this.postCode = postCode;
	}

	public BoardResponseDto(int boardCode, String boardName, String description, Timestamp createDate, String title,
			String content, String userId, Timestamp writeDate, Timestamp updateDate, int recommendation,
			int postCode) {
		super();
		this.boardCode = boardCode;
		this.boardName = boardName;
		this.description = description;
		this.createDate = createDate;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
		this.recommendation = recommendation;
		this.postCode = postCode;
	}

	public BoardResponseDto(int boardCode, String boardName, String description, Timestamp createDate) {
		super();
		this.boardCode = boardCode;
		this.boardName = boardName;
		this.description = description;
		this.createDate = createDate;
	}

	public BoardResponseDto(String title, String content, String userId, Timestamp writeDate,
			Timestamp updateDate, int recommendation, int postCode, int boardCode) {
		super();
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
		this.recommendation = recommendation;
		this.postCode = postCode;
		this.boardCode = boardCode;
	}

	public BoardResponseDto(String title, String content, String userId, Timestamp writeDate, Timestamp updateDate, int recommendation, int postCode, int boardCode, String imagePath) {
		super();
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
		this.recommendation = recommendation;
		this.postCode = postCode;
		this.boardCode = boardCode;
		this.imagePath = imagePath;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getImagePath() { return imagePath; }

	public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
