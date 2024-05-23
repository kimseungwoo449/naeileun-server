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
import utill.IPAdressManager;

public class ReadAllPostAction implements Action {
	
	private int code;
	
	public ReadAllPostAction(int code) {
		this.code = code;
	}

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resObj = new JSONObject();
		
		if (!request.getHeader("Authorization").equals(IPAdressManager.ADMIN_KEY)) {
			boolean status = false;
			String message = "Board is blocked.";
			
			resObj.put("status", status);
			resObj.put("message", message);
		} else {
			System.out.println("전체 게시글 가져오기");
			
			BoardDao boardDao = BoardDao.getInstance();
			
			List<BoardResponseDto> postList = boardDao.readAllPostByBoardCode(code);
			
			JSONObject meta = new JSONObject();
			meta.put("total_count", postList.size());
			meta.put("pageable_count", 10);
			
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
