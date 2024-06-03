package board.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import board.controller.Action;
import board.model.BoardDao;
import board.model.BoardResponseDto;
import utill.KeyManager;

public class ReadAllPostAction implements Action {
	
	private int code;
	
	public ReadAllPostAction(int code) {
		this.code = code;
	}

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resObj = new JSONObject();
		
		if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
			boolean status = false;
			String message = "Board is blocked.";
			
			resObj.put("status", status);
			resObj.put("message", message);
		} else {
			System.out.println("전체 게시글 가져오기");

			int page = Integer.parseInt(request.getParameter("page"));
			String search = request.getParameter("search");

			BoardDao boardDao = BoardDao.getInstance();
			
			List<BoardResponseDto> postList = boardDao.readAllPostByBoardCode(page, search, code);
			
			JSONObject meta = new JSONObject();
			meta.put("total_count", boardDao.countPostByBoardCode(code));
			meta.put("pageable_count", boardDao.countPostByBoardCode(code));
			
			JSONArray result = new JSONArray(postList);
			
			resObj.put("meta", meta);
			resObj.put("result", result);
			System.out.println(resObj);
			
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(resObj.toString());
		
	}

}
