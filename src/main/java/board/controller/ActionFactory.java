package board.controller;

import board.controller.ActionFactory;
import board.controller.action.ReadAllPostAction;

public class ActionFactory {
	private ActionFactory() {
		
	}
	private static  ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		if(command.equals("read")) {
			action = new ReadAllPostAction();
		}
		
		return action;
	}
}
