package introduction.controller;

import introduction.controller.action.CreateIntroduction;
import introduction.controller.action.DeleteAllIntroductionByUserId;
import introduction.controller.action.GetAllIntroductionByUserId;

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
				action = new CreateIntroduction();
			}else if(method.equals("GET")) {
				action = new GetAllIntroductionByUserId();
			}else if(method.equals("PUT")) {

			}else if(method.equals("DELETE")) {
				action = new DeleteAllIntroductionByUserId();
			}
		}else {
			if(method.equals("GET")) {

			}else if(method.equals("DELETE")) {

			}
		}

		return action;
	}
}
