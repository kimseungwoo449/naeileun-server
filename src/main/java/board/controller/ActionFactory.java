package board.controller;

import board.controller.action.*;

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
				action = new ReadAllBoardAction();
			}
		} else {
			String[] commands = command.split("/");

			if(method.equals("POST")) {
				action = new CreatePostAction();
			}
			else if(method.equals("GET") && commands.length > 2) {
				if(commands.length == 3) {
					int code = Integer.parseInt(commands[2]);
					System.out.println("code : " + code);
					action = new ReadAllPostAction(code);
				}else if(commands.length == 4) {
					int boardCode = Integer.parseInt(commands[2]);
					int postCode = Integer.parseInt(commands[3]);
					System.out.println("boardCode : " + boardCode);
					System.out.println("postCode : " + postCode);
					action = new ReadDetailPostAction(boardCode, postCode);
				}
			}
			else if(method.equals("DELETE")) {
				System.out.println("DELETE command : " + command);
				action = new DeletePostAction();
			}
			else if(method.equals("PUT")) {
				System.out.println("PUT command : " + command);
				if(command.equals("/recommendation")) {
					action = new UpdateRecommendationAction();
				}else if(command.equals("/update")) {
					action = new UpdatePostAction();
				}
			}
		}

		return action;
	}
}
