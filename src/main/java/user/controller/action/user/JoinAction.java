package user.controller.action.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import org.json.JSONObject;

import user.controller.Action;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;
import utill.KeyManager;

public class JoinAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject resObj = new JSONObject();

		int status = 0;
		String message = "";

		if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
			status = 400;
			message = "admin key is not correct.";
		} else {
			InputStream in = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String data = "";
			while (br.ready()) {
				data += br.readLine() + "\n";
			}

			Gson gson = new Gson();
			UserRequestDto user = gson.fromJson(data, UserRequestDto.class);
			UserDao userDao = UserDao.getInstance();
			UserResponseDto userDto = userDao.createUser(user);
			status = userDto != null ? 200 :400 ;
			message = userDto != null ? "User registration is success." : "User registration is failed.";
		}
		resObj.put("status", status);
		resObj.put("message", message);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(resObj.toString());
	}

}
