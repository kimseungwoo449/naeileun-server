package study.controller.action.studyGroup;

import java.io.IOException;
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
import utill.KeyManager;

public class FindPopularStudyGroupAction implements Action{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		JSONObject obj = new JSONObject();
		JSONArray result = null;
		JSONObject meta = null;
		
		if (!request.getHeader("Authorization").equals(KeyManager.ADMIN_KEY)) {
			
		} else {
			
			GroupMemberDao gmDao = GroupMemberDao.getInstance();
			List<String> list = gmDao.getPopularStudyGroupCode();
			
			StudyGroupDao sgDao = StudyGroupDao.getInstance();
			
			List<StudyGroupResponseDto> popularStudy = new ArrayList<>();
			for(String groupCode : list) {
				StudyGroupResponseDto study = sgDao.getStudyByGroupCode(groupCode);
				popularStudy.add(study);
			}
			
			meta = new JSONObject();
			meta.put("total_count", list.size());
			
			result =  new JSONArray(popularStudy);
			
		}
		
		obj.put("result",result);
		obj.put("meta", meta);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(obj.toString());
		
	}
}
