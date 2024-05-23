package study.controller.action.studyGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import study.controller.Action;
import user.model.UserDao;
import user.model.UserResponseDto;
import study.model.groupMember.GroupMemberDao;
import study.model.studyGroup.StudyGroupDao;
import study.model.studyGroup.StudyGroupResponseDto;

public class FindUserGroupAction implements Action{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		JSONObject obj = new JSONObject();
		JSONArray result = new JSONArray();
		JSONObject meta = new JSONObject();
		
		if (!request.getHeader("Authorization").equals("JU7Spt9DHvLaiHcxTD4h")) { //IPAdressManager.ADMIN_KEY
			obj.put("result",result);
			obj.put("meta", meta);
		} else {

			HttpSession session = request.getSession();
			//UserResponseDto user = (UserResponseDto) session.getAttribute("user");
			
			//로그인 백엔드 생성 후 토글 지우기
			//String userId = user.getId();
			//UserDao userDao = UserDao.getInstance();
			//String userCode = userDao.findUserCodeById(userId);
			
			String userCode = "2"; // 수정 후 삭제
			
			GroupMemberDao gmDao = GroupMemberDao.getInstance();
			List<String> groupCodes = gmDao.getGroupCodeByUserCode(userCode);
			
			StudyGroupDao sg = StudyGroupDao.getInstance();
			
			List<StudyGroupResponseDto> list = new ArrayList<>();
			
			for(String code : groupCodes) {
				StudyGroupResponseDto study = sg.getStudyListByGroupCodeList(code);
				list.add(study);
			}
			
			result = new JSONArray(list);
			
			meta = new JSONObject();
			meta.put("total_count", list.size());
			
			obj.put("result", result);
			obj.put("meta", meta);
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(obj.toString());
		
	}
}