package board.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import board.controller.Action;
import board.model.BoardDao;
import board.model.BoardResponseDto;
import utill.KeyManager;

public class ReadDetailPostAction implements Action {

	private int boardCode;
	private int postCode;

	public ReadDetailPostAction(int boardCode, int postCode) {
		this.boardCode = boardCode;
		this.postCode = postCode;
	}

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resObj = new JSONObject();
		// test(in feature message)
		if (!request.getHeader("Authorization").equals(KeyManager.ADMIN_KEY)) {
			boolean status = false;
			String message = "Board is blocked.";

			resObj.put("status", status);
			resObj.put("message", message);
		} else {
			System.out.println("상세 게시글 가져오기");

			BoardDao boardDao = BoardDao.getInstance();

			BoardResponseDto post = boardDao.readPostByBoardCodeAndPostCode(boardCode, postCode);
			System.out.println("title : "+post.getTitle());

			resObj.put("result", new JSONObject(post));
			System.out.println(resObj);

		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(resObj.toString());
	}

}
