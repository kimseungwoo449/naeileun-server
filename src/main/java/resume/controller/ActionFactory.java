package resume.controller;

import resume.controller.action.UpdateAction;
import resume.controller.action.WriteAction;

public class ActionFactory {
private ActionFactory() {
		
	}
	private static  ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String pathInfo, String method) {
		Action action = null;
		
		if(pathInfo==null) {
			if(method.equals("POST")) {
				action = new WriteAction();
			}else if(method.equals("GET")) {
				
			}else if(method.equals("PUT")) {
				action = new UpdateAction();
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
