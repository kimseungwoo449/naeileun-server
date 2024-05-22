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

public class WriteAction implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resObj = new JSONObject();

		boolean status = true;
		String message = "Resume is created..";
		
		if (!request.getHeader("Authorization").equals(IPAdressManager.ADMIN_KEY)) {
			status = false;
			message = "Resume is blocked.";
		} else {
			InputStream in = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String data = "";
			int count = 0;
			
			while (br.ready()) {
				data += br.readLine() + "\n";
				count++;
			}
			
			JSONObject reqObj = new JSONObject(data);
			ResumeRequestDto dto = new ResumeRequestDto(reqObj);
			
			ResumeDao resumeDao = ResumeDao.getInstance();
			if(resumeDao.createResume(dto)==null) {
				status = false;
				message = "Resume is blocked.";
			}
		}

		resObj.put("status", status);
		resObj.put("message", message);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		response.getWriter().append(resObj.toString());
	}
}
