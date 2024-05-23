package study.controller.action.studyGroup;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import study.controller.Action;
import study.model.studyGroup.StudyGroupDao;
import utill.IPAdressManager;

public class FindPopularStudyGroupAction implements Action{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		JSONObject obj = new JSONObject();
		JSONArray result = new JSONArray();
		JSONObject meta = new JSONObject();
		
		if (!request.getHeader("Authorization").equals(IPAdressManager.ADMIN_KEY)) {
			
		} else {
			
			
			
			
			
			
			
		}
		
		obj.put("result",result);
		obj.put("meta", meta);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(obj.toString());
		
	}
}
