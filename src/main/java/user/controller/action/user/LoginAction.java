package user.controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import user.controller.Action;
import user.model.UserDao;
import user.model.UserResponseDto;

public class LoginAction implements Action{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		boolean isValid = true;
		
		if(id == null || id.equals(""))
			isValid = false;
		else if(password == null || password.equals(""))
			isValid = false;
		
		if(isValid) {
			// 연동된 데이터 베이스로부터		(UserDao)
			// 유저의 정보를 조회 하고,		(findUserByIdAndPassword())
			// 정보가 일치하면				(return된 UserResponseDto가 null이 아니면)
			// 로그인 처리 후, 페이지 이동		(jsp 내장객체 중 session에 유저정보 저장)
			
			UserDao userDao = UserDao.getInstance();
			UserResponseDto user = userDao.findUserByIdAndPassword(id, password);
			
			HttpSession session = request.getSession();
			
			if(user != null) {
				session.setAttribute("user", user);

				
				response.sendRedirect("http://192.168.30.84:3000/naeileun/users/login");				

			} else {
				response.sendRedirect("http://192.168.30.84:3000/naeileun");				
			}
		} 
		System.out.println("로그인 처리 로직");
	}

}
