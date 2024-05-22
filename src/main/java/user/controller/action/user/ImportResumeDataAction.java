package user.controller.action.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.Action;
import user.model.UserResponseDto;


public class ImportResumeDataAction implements Action{
	 
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("123");
//		   List<ResumeResponseDto> resumeList = ResumeDao.getInstance().findCarAll();
//	       request.setAttribute("resumeList", resumeList);
//	       RequestDispatcher dispatcher = request.getRequestDispatcher("http://192.168.30.84:3000/naeileun/users/resume");
//	       dispatcher.forward(request, response);	
	}
       

}
