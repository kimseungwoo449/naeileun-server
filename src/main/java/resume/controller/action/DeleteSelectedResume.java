package resume.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import resume.controller.Action;
import resume.model.ResumeDao;
import resume.model.ResumeRequestDto;
import utill.IPAdressManager;

public class DeleteSelectedResume implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resObj = new JSONObject();
		
		boolean status = true;
		String message = "Resume is deleted.";
		
		if (!request.getHeader("Authorization").equals(IPAdressManager.ADMIN_KEY)) {
			status = false;
			message = "Resume is not deleted";
		} else {
			String strResumeCode = request.getPathInfo();
			int resumeCode = Integer.parseInt(strResumeCode.substring(1,strResumeCode.length()));
			
			ResumeRequestDto dto = new ResumeRequestDto();
			
			dto.setResumeCode(resumeCode);
			ResumeDao resumeDao = ResumeDao.getInstance();
			
			if(!resumeDao.deleteResume(dto)) {
				status = false;
				message = "Resume is not deleted";
			}
		}
		
		resObj.put("status", status);
		resObj.put("message", message);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		response.getWriter().append(resObj.toString());

	}
}
