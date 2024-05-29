package user.controller.action.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import user.controller.Action;
import user.model.UserDao;
import user.model.UserResponseDto;

public class LoginAction implements Action{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("1234");
		InputStream in = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String data = "";
		while (br.ready()) {
			data += br.readLine() + "\n";
		}

		UserDao userDao = UserDao.getInstance();
		JSONObject object = new JSONObject(data);

		String id = object.getString("id");
		String password = object.getString("password");


		request.setCharacterEncoding("UTF-8");

		UserResponseDto userDto = userDao.findUserByIdAndPassword(id, password);
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(userDto);
		
		JSONObject resObj = new JSONObject();
		
		int status = 200;
		String message = "User login is success.";
		
		if(userDto == null) {
			status = 400;
			message = "User login is failed.";
		} 
		if(userDto != null) {

		    resObj.put("user", new JSONObject(json));
		}
		System.out.println(json);
		resObj.put("status", status);
		resObj.put("message", message);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		response.getWriter().append(resObj.toString());
		
	}

}
