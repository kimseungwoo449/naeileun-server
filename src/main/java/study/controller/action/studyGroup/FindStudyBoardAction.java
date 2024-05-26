package study.controller.action.studyGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import study.controller.Action;
import study.model.groupMember.GroupMemberDao;
import study.model.studyGroup.StudyGroupDao;
import study.model.studyGroup.StudyGroupResponseDto;
import utill.IPAdressManager;

public class FindStudyBoardAction implements Action{
	
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		JSONObject obj = new JSONObject();
		JSONArray result = null;
		JSONObject meta = null;
		
		if (!request.getHeader("Authorization").equals(IPAdressManager.ADMIN_KEY)) {
			
		} else {

			InputStream in = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String data = "";
			
			while (br.ready()) {
				data = br.readLine();
			}
			
			String studyCode = new JSONObject(data).getString("group_code");
			System.out.println(studyCode);
			
			StudyGroupDao sgDao = StudyGroupDao.getInstance();
			StudyGroupResponseDto study = sgDao.getStudyByGroupCode(studyCode);
			JSONObject s = new JSONObject(study);
			
			//groupPost 객체 만들기
			JSONObject p = new JSONObject("post", "post");
			
			JSONObject object = new JSONObject();
			object.put("study", s);
			object.put("post", "post");
			
			meta = new JSONObject();
			meta.put("total_count", 1);
			
			result =  new JSONArray();
			result.put(object);
		}
		
		obj.put("result",result);
		obj.put("meta", meta);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(obj.toString());
		
	}
}