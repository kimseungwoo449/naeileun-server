package study.controller;

import study.controller.Action;
import study.controller.action.studyGroup.DeleteStudyAction;
import study.controller.action.studyGroup.FindPopularStudyGroupAction;
import study.controller.action.studyGroup.FindStudyBoardAction;
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
		}else if(command.equals("/popularGroup")){
			action = new FindPopularStudyGroupAction();
		}else if(command.equals("/board")){
			action = new FindStudyBoardAction();
		}else if(command.equals("/delete")){
			action = new DeleteStudyAction();
		}
		
		return action;
	}
}
