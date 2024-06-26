package study.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
    
	   
	   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//GoF의 디자인 패턴중 
		// ㄴ 생성패턴인 Singleton Pattern
		// ㄴ 생성 패턴인 Factory Method Pattern
		// ㄴ 행위 패턴인 Command Pattern
		
		request.setCharacterEncoding("UTF-8");
		String command = request.getPathInfo();

		if(command!=null) {
			ActionFactory af = ActionFactory.getInstance();
			Action action = af.getAction(command);
			if(action != null) {
				action.excute(request, response);
			}else {
				response.sendError(404);			
			}
		}else {
			response.sendError(404);			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
