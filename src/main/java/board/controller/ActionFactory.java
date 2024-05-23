package board.controller;

import board.controller.ActionFactory;
import board.controller.action.ReadAllBoardAction;
import board.controller.action.ReadAllPostAction;
import board.controller.action.ReadDetailPostAction;

public class ActionFactory {
	private ActionFactory() {
		
	}
	private static  ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		String[] commands = new String[] {};
		
		if(command != null)
			commands = command.split("/");
		if(command == null) {
			action = new ReadAllBoardAction();
		}
		else if(commands.length > 2 && commands[1].equals("view")) {
			
			if(commands.length == 3) {
				int code = Integer.parseInt(commands[2]);
				System.out.println("code : " + code);
				action = new ReadAllPostAction(code);
			} else {
				int boardCode = Integer.parseInt(commands[2]);
				int postCode = Integer.parseInt(commands[3]);
				System.out.println("boardCode : " + boardCode);
				System.out.println("postCode : " + postCode);
				action = new ReadDetailPostAction(boardCode, postCode);
			}
		}
		
		return action;
	}
	
	public Action getAction(String command, String params) {
		Action action = null;
		if(command.equals("/view/")) {
			
		}
		
		return action;
	}
}
