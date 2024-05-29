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
import utill.KeyManager;
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
		
		if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
			obj.put("result",result);
			obj.put("meta", meta);
		} else {

			//HttpSession session = request.getSession();
			//UserResponseDto user = (UserResponseDto) session.getAttribute("user");
			
//			String userId = user.getId();
//			UserDao userDao = UserDao.getInstance();
//			String userCode = String.valueOf(userDao.findUserCodeById(userId));
//			System.out.println("usercode" + userCode);
//			System.out.println("usercode" + userDao.findUserCodeById(userId));

			String userCode = "2";
			System.out.println(userCode);
			GroupMemberDao gmDao = GroupMemberDao.getInstance();
			List<String> groupCodes = gmDao.getGroupCodeByUserCode(userCode);
			System.out.println("groupCodes :"+ groupCodes.toString());
			StudyGroupDao sg = StudyGroupDao.getInstance();
			
			List<StudyGroupResponseDto> list = new ArrayList<>();
			
			for(String code : groupCodes) {
				System.out.println("code :"+ code);
				StudyGroupResponseDto study = sg.getStudyByGroupCode(code);
				if(study != null) {
					list.add(study);
				}
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
