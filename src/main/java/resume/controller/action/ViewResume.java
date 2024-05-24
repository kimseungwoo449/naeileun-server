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
import resume.model.ResumeResponseDto;
import user.model.UserDao;
import utill.IPAdressManager;

public class ViewResume implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resObj = new JSONObject();

		if (!request.getHeader("Authorization").equals(IPAdressManager.ADMIN_KEY)) {
			resObj = null;
		} else {

			InputStream in = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String data = "";

			while (br.ready()) {
				data += br.readLine() + "\n";
			}

			JSONObject reqObj = new JSONObject(data);
			ResumeRequestDto dto = new ResumeRequestDto();

			dto.setResumeCode(reqObj.getInt("resume_code"));
			ResumeDao resumeDao = ResumeDao.getInstance();
			ResumeResponseDto result = resumeDao.getResume(dto);

			UserDao userDao = UserDao.getInstance();
			resObj.put("user_id", userDao.findUserIdByCode("" + result.getUserCode()));
			System.out.println(resObj.get("user_id"));
			resObj.put("name", result.getName());
			resObj.put("title", result.getTitle());
			resObj.put("user_age", result.getUserAge());
			resObj.put("academic_career", result.getAcademicCareer());
			resObj.put("resume_code", result.getResumeCode());
			resObj.put("career", result.getCareer());
			resObj.put("skill", result.getSkill());
			resObj.put("certificate", result.getCertificate());
			resObj.put("langauage", result.getLanguage());
			resObj.put("award", result.getAward());

		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		response.getWriter().append(resObj.toString());

	}
}
