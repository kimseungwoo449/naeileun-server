package user.controller.action.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;

import com.google.gson.Gson;

import study.model.groupMember.GroupMemberDao;
import study.model.studyGroup.StudyGroupDao;
import study.model.studyGroup.StudyGroupResponseDto;
import user.controller.Action;
import utill.KeyManager;

public class ImportMyStudyAction implements Action{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject resObj = new JSONObject();

		int status = 0;
		String message = "";

		if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
			status = 400;
			message = "User registration is failed.";
			resObj.put("status", status);
			resObj.put("message", message);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(resObj.toString());
		} else {
			InputStream in = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String data = "";
			while (br.ready()) {
				data += br.readLine() + "\n";
			}
			JSONObject object = new JSONObject(data);
			String userCode = object.getString("userCode");

			GroupMemberDao gmDao = GroupMemberDao.getInstance();

			StudyGroupDao sg = StudyGroupDao.getInstance();

			List<StudyGroupResponseDto> list = sg.findMyStudyAllByUserCode(userCode);

			Gson gson = new Gson();

			String jsonResponse = gson.toJson(list);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			response.getWriter().write(jsonResponse);
		}
	}

}
