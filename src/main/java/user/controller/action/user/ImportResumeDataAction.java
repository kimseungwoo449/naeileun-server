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
import utill.KeyManager;

public class ImportResumeDataAction implements Action {

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
			String data = "";
			try (InputStream in = request.getInputStream()) {
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				while (br.ready()) {
					data += br.readLine() + "\n";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			JSONObject object = new JSONObject(data);

			String userId = object.getString("id");
			ResumeRequestDto dto = new ResumeRequestDto();
			dto.setUserCode(userId);
			List<ResumeResponseDto> resumeList = ResumeDao.getInstance().getAllResume(dto);

			Gson gson = new Gson();
			String jsonResponse = gson.toJson(resumeList);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			response.getWriter().write(jsonResponse);
		}
	}

}
