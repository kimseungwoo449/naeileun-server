package comment.controller;

import comment.controller.action.ReadPostCommentsAction;
import user.controller.action.user.*;

public class ActionFactory {
	private ActionFactory() {
		
	}
	private static  ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command, String method) {
		Action action = null;
//		if(command.equals("/post")) {
//			controller = new ReadPostCommentsAction();
//		}else if(command.equals("/login")) {
//			controller = new LoginAction();
//		}else if(command.equals("/delete")) {
//			controller = new DeleteAction();
//		}else if(command.equals("/update")) {
//			controller = new UpdateAction();
//		}else if(command.equals("/resume")) {
//			controller = new ImportResumeDataAction();
//		}else if(command.equals("/study")) {
//			controller = new ImportMyStudyAction();
//		}else if(command.equals("/check")) {
//			controller = new CheckIdAction();
//		}
//		if(command == null) {
//			if(method.equals("GET")){
//				controller = new ReadPostCommentsAction();
//			}
//		} else {
//			String[] commands = command.split("/");
//
//			if(method.equals("POST")) {
//				controller = new CreatePostAction();
//			}
//			else if(method.equals("GET") && commands.length > 2) {
//				if(commands.length == 3) {
//					int code = Integer.parseInt(commands[2]);
//					System.out.println("code : " + code);
//					controller = new ReadAllPostAction(code);
//				}else if(commands.length == 4) {
//					int boardCode = Integer.parseInt(commands[2]);
//					int postCode = Integer.parseInt(commands[3]);
//					System.out.println("boardCode : " + boardCode);
//					System.out.println("postCode : " + postCode);
//					controller = new ReadDetailPostAction(boardCode, postCode);
//				}
//			}
//		}
		
		return action;
	}
}
