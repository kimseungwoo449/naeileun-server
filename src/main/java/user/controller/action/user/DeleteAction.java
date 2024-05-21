package user.controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.controller.Action;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;

public class DeleteAction implements Action{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");

		UserDao userDao = UserDao.getInstance();

		UserResponseDto user = (UserResponseDto) session.getAttribute("user");
		

		String id = user.getId();
		String password = request.getParameter("password");

		// 패스워드가 일치하면 -> 삭제 처리 
		UserRequestDto userDto = new UserRequestDto();

		userDto.setId(id);
		userDto.setPassword(password);

		boolean result = userDao.deleteUser(userDto);

		if(result) {
			System.out.println("탈퇴완료");
			session.removeAttribute("user");
			response.sendRedirect("http://192.168.30.84:3000/naeileun");
		} else {	
			response.sendRedirect("http://192.168.30.84:3000/naeileun");
			System.out.println("탈퇴실패");
		}
	}

}
