package resume.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import resume.controller.Action;
import resume.model.ResumeDao;
import resume.model.ResumeRequestDto;
import resume.model.ResumeResponseDto;
import user.model.UserDao;
import utill.KeyManager;

public class ViewResume implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resObj = new JSONObject();

		if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
			resObj = null;
		} else {
			String strResumeCode = request.getPathInfo();
			int resumeCode = Integer.parseInt(strResumeCode.substring(1,strResumeCode.length()));
			
			ResumeRequestDto dto = new ResumeRequestDto();
			
			dto.setResumeCode(resumeCode);
			ResumeDao resumeDao = ResumeDao.getInstance();
			ResumeResponseDto result = resumeDao.getResume(dto);

			UserDao userDao = UserDao.getInstance();
			resObj.put("user_id", userDao.findUserIdByCode(result.getUserCode()));
			resObj.put("name", result.getName());
			resObj.put("title", result.getTitle());
			resObj.put("user_age", result.getUserAge());
			resObj.put("academic_career", result.getAcademicCareer());
			resObj.put("resume_code", result.getResumeCode());
			resObj.put("career", result.getCareer());
			resObj.put("skill", result.getSkill());
			resObj.put("certificate", result.getCertificate());
			resObj.put("language", result.getLanguage());
			resObj.put("award", result.getAward());
			resObj.put("phone", result.getPhone());
			resObj.put("expected_salary", result.getExpectedSalary());
			resObj.put("expected_region", result.getExpectedRegion());
			resObj.put("is_newbie", result.isNewbie());
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		response.getWriter().append(resObj.toString());

	}
}
