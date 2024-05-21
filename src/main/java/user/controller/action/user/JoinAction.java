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
		
		resObj.put("status", status);
		resObj.put("message", message);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		response.getWriter().append(resObj.toString());
		
		
//		// Backend 에서 전달받은 데이터에 대한 유효성 검증
//		boolean isValid = true;
//
//		if (id == null || id.equals("")) {
//			isValid = false;
//			System.out.println("1");
//		}
//		else if (password == null || password.equals("")) {
//			isValid = false;
//			System.out.println("2");
//
//		}
//		else if (name == null || name.equals("")) {
//			isValid = false;
//			System.out.println("3");
//		}
//		else if (resident_number == null || resident_number.equals("")) {
//			isValid = false;
//			System.out.println("4");
//		}
////		else if (admin == null || admin.equals(""))
////			isValid = false;
//		else if (phone == null || phone.equals("")) {
//			isValid = false;
//			System.out.println("5");
//		}
//
//		// Processing Page 에서는 사용자에게 보여주는 화면을 작성하지 않음
//		// 요청에 대한 응답 처리를 작성
//		// 1) 페이지 이동 처리 (흐름을 제어)
//		if (isValid) {
//			// 연동된 데이터 베이스로부터
//			// 유저의 정보를 조회 하고,
//			// 중복에 대한 검증을 한 후에
//			// 가입 처리 후, 페이지 이동
//
//			UserRequestDto userDto = new UserRequestDto(id, password, name, resident_number, phone, admin, email);
//
//			UserDao userDao = UserDao.getInstance();
//			UserResponseDto user = userDao.createUser(userDto);
//
//			if (user == null) {
//				// 실패
//				response.sendRedirect("http://192.168.30.84:3000/naeileun/users/join");
//			} else {
//				// 성공
//				System.out.println("user : " + user);
//
//				response.sendRedirect("http://192.168.30.84:3000/naeileun");
//			}
//		}
	}

}
