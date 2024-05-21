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

public class UpdateAction implements Action{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

	      UserDao userDao = UserDao.getInstance();

	      HttpSession session = request.getSession();

	      UserResponseDto user = (UserResponseDto) session.getAttribute("user");

	      String password = request.getParameter("password");

	      // 입력된 패스워드 검증 후,
	      if (userDao.findUserByIdAndPassword(user.getId(), password) != null) {
	         UserRequestDto userDto = new UserRequestDto();

	         userDto.setId(user.getId());
	         userDto.setPassword(password);

	         String newPassword = request.getParameter("password-new");
	         String email = request.getParameter("email");
	         String phone = request.getParameter("phone");

	         if (user.getEmail() != null && !user.getEmail().equals(email)) {
	            userDto.setEmail(email);
	            user = userDao.updateUserEmail(userDto);
	         }

	         if( !user.getPhone().equals(phone)) {
	             
	             userDto.setPhone(phone);
	             user = userDao.updateUserPhone(userDto);
	          }
	         
	         if (!newPassword.equals("") && !newPassword.equals(password)) {
	             user = userDao.updateUserPassword(userDto, newPassword);
	          } else {
	             System.err.println("새 비밀번호 오류");
	             String.format("%s", newPassword);
	          }

	      }
	      session.setAttribute("user", user);
	      response.sendRedirect("");
	}

}
