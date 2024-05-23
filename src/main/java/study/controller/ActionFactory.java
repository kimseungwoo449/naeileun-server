package study.controller;

import study.controller.Action;
import study.controller.action.studyGroup.FindUserGroupAction;

public class ActionFactory{
	
	private ActionFactory() {
		
	}
	
	private static  ActionFactory instance = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		if(command.equals("/myGroup")) {
			action = new FindUserGroupAction();
		}
		
		return action;
	}
}
