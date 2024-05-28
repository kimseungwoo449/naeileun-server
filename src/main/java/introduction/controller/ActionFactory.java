package introduction.controller;

import introduction.controller.action.createIntroduction;
import resume.controller.action.*;

public class ActionFactory {
	private ActionFactory() {
		
	}
	private static  ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String pathInfo,String method) {
		Action action = null;

		if(pathInfo==null) {
			if(method.equals("POST")) {
				action = new createIntroduction();
			}else if(method.equals("GET")) {

			}else if(method.equals("PUT")) {

			}else if(method.equals("DELETE")) {

			}
		}else {
			if(method.equals("GET")) {

			}else if(method.equals("DELETE")) {

			}
		}

		return action;
	}
}
