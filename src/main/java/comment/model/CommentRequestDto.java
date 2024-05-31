package comment.model;

import java.sql.Timestamp;

public class CommentRequestDto {
	private int commentCode;
	private int userCode;
	private int postCode;
	private String content;
	private Timestamp writeDate;
	private Timestamp updateDate;

	public CommentRequestDto() {

	}

	public CommentRequestDto(int commentCode, int userCode, int postCode, String content, Timestamp writeDate, Timestamp updateDate) {
		this.commentCode = commentCode;
		this.userCode = userCode;
		this.postCode = postCode;
		this.content = content;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
	}

	public CommentRequestDto(int commentCode, int userCode, int postCode, String content) {
		this.commentCode = commentCode;
		this.userCode = userCode;
		this.postCode = postCode;
		this.content = content;
	}

	public int getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(int commentCode) {
		this.commentCode = commentCode;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	
}
