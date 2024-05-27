package user.controller.action.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import resume.model.ResumeDao;
import resume.model.ResumeRequestDto;
import resume.model.ResumeResponseDto;
import user.controller.Action;
import user.model.UserResponseDto;

public class ImportResumeDataAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("123");
		InputStream in = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String data = "";
		while (br.ready()) {
			data += br.readLine() + "\n";
		}
		JSONObject object = new JSONObject(data);

		String userId = object.getString("id");
		ResumeRequestDto dto = new ResumeRequestDto();
		dto.setUserCode(userId);
		List<ResumeResponseDto> resumeList = ResumeDao.getInstance().getAllResume(dto);

		Gson gson = new Gson();
		String jsonResponse = gson.toJson(resumeList);

		// 응답 콘텐츠 타입을 JSON으로 설정
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// JSON 응답을 출력에 작성
		response.getWriter().write(jsonResponse);
	}

}
