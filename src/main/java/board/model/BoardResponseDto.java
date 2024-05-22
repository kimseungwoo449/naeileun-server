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

	public BoardResponseDto(int boardCode, String boardName, String description, Timestamp createDate, String title,
			String content, String userId, Timestamp writeDate, Timestamp updateDate, int recommandation,
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
