package introduction.controller;

import introduction.controller.action.*;

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
				action = new UpdateIntroduction();
			}else if(method.equals("DELETE")) {
				action = new DeleteAllIntroductionByUserId();
			}
		}else {
			if(method.equals("GET")) {
				action = new GetIntroductionByDocumentCode();
			}else if(method.equals("DELETE")) {
				action = new DeleteIntroductionByDocumentCode();
			}
		}

		return action;
	}
}
