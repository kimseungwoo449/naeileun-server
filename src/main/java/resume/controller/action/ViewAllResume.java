package resume.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import resume.controller.Action;
import resume.model.ResumeDao;
import resume.model.ResumeRequestDto;
import resume.model.ResumeResponseDto;
import utill.KeyManager;

public class ViewAllResume implements Action {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resObj = new JSONObject();
		List<ResumeResponseDto> resumes =null;
		if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
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
			
			String userId = reqObj.getString("user_id");
			System.out.println(userId);
			dto.setUserCode(userId);
			ResumeDao resumeDao = ResumeDao.getInstance();
			
			resumes = resumeDao.getAllResume(dto);
		}
		
		if(resumes!=null) {			
			Object[] tempArr = new Object[resumes.size()];
			
			for(int i =0;i<resumes.size();i++) {
				ResumeResponseDto targetResume = resumes.get(i);
				
				JSONObject temp = new JSONObject();
				temp.put("user_code", targetResume.getUserCode());
				temp.put("name", targetResume.getName());
				temp.put("title", targetResume.getTitle());
				temp.put("user_age", targetResume.getUserAge());
				temp.put("academic_career", targetResume.getAcademicCareer());
				temp.put("resume_code", targetResume.getResumeCode());
				temp.put("career", targetResume.getCareer());
				temp.put("skill", targetResume.getSkill());
				temp.put("certificate", targetResume.getCertificate());
				temp.put("language", targetResume.getLanguage());
				temp.put("award", targetResume.getAward());
				temp.put("write_date", targetResume.getWriteDate());
				temp.put("update_date", targetResume.getUpdateDate());
				tempArr[i] = temp;
			}
			resObj.put("result", tempArr);
		}else {
			resObj=null;
		}
		
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		response.getWriter().append(resObj.toString());
	}
}
