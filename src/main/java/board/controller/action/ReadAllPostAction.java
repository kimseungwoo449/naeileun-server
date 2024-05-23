package board.controller.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import board.controller.Action;
import board.model.BoardDao;
import board.model.BoardResponseDto;

public class ReadAllPostAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resObj = new JSONObject();
		
		if (!request.getHeader("Authorization").equals("ADMIN JU7Spt9DHvLaiHcxTD4h")) {
			boolean status = false;
			String message = "Board is blocked.";
			
			resObj.put("status", status);
			resObj.put("message", message);
		} else {
			System.out.println("읽어오기");
			
			BoardDao boardDao = BoardDao.getInstance();
			
			List<BoardResponseDto> postList = boardDao.readAllPost();
			
			
			
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
