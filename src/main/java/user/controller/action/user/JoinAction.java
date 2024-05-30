package user.controller.action.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import user.controller.Action;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;

public class JoinAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		InputStream in = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String data = "";
		while (br.ready()) {
			data += br.readLine() + "\n";
		}

		System.out.println("data : " + data);

		JSONObject object = new JSONObject(data);

		String id = object.getString("id");
		String password = object.getString("password");
		String email = object.getString("email");
		String name = object.getString("name");
		String residentNumber = object.getString("resident_number");
		String phone = object.getString("phone");
		Boolean admin = object.getBoolean("admin");

		UserRequestDto user = new UserRequestDto(id, password, name, residentNumber, phone, admin, email);
		UserDao userDao =  UserDao.getInstance();

		UserResponseDto userDto = userDao.createUser(user);

		JSONObject resObj = new JSONObject();
		
		int status = 200;
		String message = "User registration is success.";
		
		if(userDto == null) {
			status = 400;
			message = "User registration is failed.";
		}
		System.out.println(message);
		resObj.put("status", status);
		resObj.put("message", message);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		response.getWriter().append(resObj.toString());


	}

}
