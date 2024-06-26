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
import study.model.groupPost.GroupPostDao;
import study.model.groupPost.GroupPostResponseDto;
import study.model.studyGroup.StudyGroupDao;
import study.model.studyGroup.StudyGroupRequestDto;
import study.model.studyGroup.StudyGroupResponseDto;
import utill.KeyManager;

public class FindStudyBoardAction implements Action{
	
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		JSONObject obj = new JSONObject();
		JSONArray result ;
		JSONObject meta ;
		boolean status = false;
		if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
			result = null;
			meta = null;
		} else {

			String groupCode = request.getParameter("group_code");
			String userCode = request.getParameter("user_code");

			StudyGroupRequestDto sgReqDto = new StudyGroupRequestDto();
			sgReqDto.setGroupCode(groupCode);

			StudyGroupDao sgDao = StudyGroupDao.getInstance();
			StudyGroupResponseDto study = sgDao.getStudyByGroupCode(sgReqDto);

			if(study !=null) {
				status = true;
			}

			JSONObject s = new JSONObject(study);
			//groupPost 객체 만들기

			GroupPostDao gpDao = GroupPostDao.getInstance();
			List<GroupPostResponseDto> postLists = gpDao.getGroupPostsByGroupCode(sgReqDto.getGroupCode());
			JSONArray p = new JSONArray(postLists);

			int pageCount = gpDao.getGroupPostsCount(sgReqDto);
			GroupMemberDao gmDao = GroupMemberDao.getInstance();
			boolean isMember = gmDao.getIsMember(groupCode,userCode);
			JSONObject member = new JSONObject();
			member.put("isMember", isMember);

			JSONObject object = new JSONObject();
			object.put("study", s);
			object.put("post", p);
			object.put("isMember", member);

			meta = new JSONObject();
			meta.put("total_count",pageCount );
			
			result =  new JSONArray();
			result.put(object);
		}
		
		obj.put("result",result);
		obj.put("meta", meta);
		obj.put("status",status);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(obj.toString());
		
	}
}
