package study.controller.action.studyGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import study.controller.Action;
import study.model.groupMember.GroupMemberRequestDto;
import utill.KeyManager;
import study.model.studyGroup.StudyGroupDao;
import study.model.studyGroup.StudyGroupResponseDto;

public class GetStudyGroupAction implements Action{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		JSONObject obj = new JSONObject();
		JSONArray result = new JSONArray();
		boolean status = true;
		int meta = 0;
		
		if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
			obj.put("result",result);
			obj.put("meta", meta);
			obj.put("status", false);
		} else {
			String userCode = request.getParameter("user_code");
			System.out.println(userCode);
			GroupMemberRequestDto gmDto = new GroupMemberRequestDto();
			gmDto.setUserCode(userCode);

			StudyGroupDao sgDao = StudyGroupDao.getInstance();
			List<StudyGroupResponseDto> list = sgDao.getUserStudyByUserCode(gmDto);
			JSONArray myStudy = new JSONArray(list);

			List<StudyGroupResponseDto> popularStudy = sgDao.getPopularStudy();
			JSONArray popular = new JSONArray(popularStudy);

			result.put(myStudy);
			result.put(popular);

			if(list.isEmpty() || popularStudy.isEmpty()){
				status = false;
			}

			meta = list.size();
			obj.put("meta",meta);
			obj.put("status",status);
			obj.put("result", result);
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(obj.toString());


	}
}
