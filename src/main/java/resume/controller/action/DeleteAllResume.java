package resume.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import resume.controller.Action;
import resume.model.ResumeDao;
import resume.model.ResumeRequestDto;
import utill.IPAdressManager;

public class DeleteAllResume implements Action{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
JSONObject resObj = new JSONObject();
		
		boolean status = true;
		String message = "User's resumes is all deleted.";
		
		if (!request.getHeader("Authorization").equals(IPAdressManager.ADMIN_KEY)) {
			status = false;
			message = "User's resumes is not deleted.";
		} else {
			InputStream in = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String data = "";
			
			while (br.ready()) {
				data += br.readLine() + "\n";
			}
			
			JSONObject reqObj = new JSONObject(data);
			ResumeRequestDto dto = new ResumeRequestDto();
			
			String userId = reqObj.getString("user_id");
			dto.setUserCode(userId);
			
			ResumeDao resumeDao = ResumeDao.getInstance();
			
			if(!resumeDao.deleteAllResume(dto)) {
				status = false;
				message = "User's resumes is not deleted.";
			}
		}
		
		resObj.put("status", status);
		resObj.put("message", message);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		response.getWriter().append(resObj.toString());
	}
}
