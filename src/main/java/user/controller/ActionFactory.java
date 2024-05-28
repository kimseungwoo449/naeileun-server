package user.controller;

import user.controller.action.user.*;

public class ActionFactory {
	private ActionFactory() {
		
	}
	private static  ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	public Action getAction(String command) {
		Action action = null;
		if(command.equals("/join")) {
			action = new JoinAction();
		}else if(command.equals("/login")) {
			action = new LoginAction();
		}else if(command.equals("/delete")) {
			action = new DeleteAction();
		}else if(command.equals("/update")) {
			action = new UpdateAction();
		}else if(command.equals("/resume")) {
			action = new ImportResumeDataAction();
		}else if(command.equals("/study")) {
			action = new ImportMyStudyAction();
		}else if(command.equals("/check")) {
			action = new CheckIdAction();
		}
		
		return action;
	}
	
}
