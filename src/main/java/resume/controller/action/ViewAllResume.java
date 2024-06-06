package resume.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import org.json.JSONObject;

import resume.controller.Action;
import resume.model.ResumeDao;
import resume.model.ResumeRequestDto;
import resume.model.ResumeResponseDto;
import utill.KeyManager;

public class ViewAllResume implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String jsonResponse ="";
		List<ResumeResponseDto> resumes =null;
		if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
			jsonResponse = null;
		} else {
			ResumeRequestDto dto = new ResumeRequestDto();
			

			String userId = request.getParameter("user_id");
			dto.setUserCode(userId);
			ResumeDao resumeDao = ResumeDao.getInstance();
			
			resumes = resumeDao.getAllResume(dto);
		}
		
		if(resumes!=null) {
			jsonResponse = gson.toJson(resumes);
		}else {
			jsonResponse=null;
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		response.getWriter().append(jsonResponse);

	}
}
