package comment.controller;

import comment.controller.action.CreateCommentAction;
import comment.controller.action.ReadAllCommentAction;
import comment.controller.action.UpdateCommentAction;

public class ActionFactory {
	private ActionFactory() {
		
	}
	private static  ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command, String method) {
		Action action = null;

		if(command == null) {
			if(method.equals("GET")){
				action = new ReadAllCommentAction();
			}
			else if(method.equals("PUT")){
				action = new UpdateCommentAction();
			}
		} else {
			String[] commands = command.split("/");

			if(method.equals("POST")) {
				action = new CreateCommentAction();
			}
			else if(method.equals("GET")) {
				action = new ReadAllCommentAction();
			}
		}

		return action;
	}
}
