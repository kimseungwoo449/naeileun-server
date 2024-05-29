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

public class ReadAllBoardAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resObj = new JSONObject();
		
		if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
			boolean status = false;
			String message = "Board is blocked.";
			
			resObj.put("status", status);
			resObj.put("message", message);
		} else {
			System.out.println("게시판 전체 가져오기");
			
			BoardDao boardDao = BoardDao.getInstance();
			List<BoardResponseDto> boardList = boardDao.readAllBoard();
			List<BoardResponseDto> bestPostList = boardDao.readBestPost();
			
			JSONObject meta = new JSONObject();
			meta.put("total_count", boardList.size());
			meta.put("pageable_count", 8);
			
			JSONArray result = new JSONArray();
			result.put(boardList);
			result.put(bestPostList);
			System.out.println(result);
			
			resObj.put("meta", meta);
			resObj.put("result", result);
			System.out.println(resObj);
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(resObj.toString());
	}

}
