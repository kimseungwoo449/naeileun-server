package comment.model;

import java.sql.Timestamp;

public class Comment {
	private int commentCode;
	private int userCode;
	private int postCode;
	private String content;
	private Timestamp writeDate;
	private Timestamp updateDate;
	public Comment(){

	}
	public Comment(int commentCode, int userCode, int postCode, String content, Timestamp writeDate, Timestamp updateDate) {
		this.commentCode = commentCode;
		this.userCode = userCode;
		this.postCode = postCode;
		this.content = content;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
	}

	public Comment(String content, int postCode, int userCode, int commentCode) {
		this.content = content;
		this.postCode = postCode;
		this.userCode = userCode;
		this.commentCode = commentCode;
	}

	public int getCommentCode() {
		return commentCode;
	}

	public int getUserCode() {
		return userCode;
	}

	public int getPostCode() {
		return postCode;
	}

	public String getContent() {
		return content;
	}

	public Timestamp getWriteDate() {
		return writeDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}
}
