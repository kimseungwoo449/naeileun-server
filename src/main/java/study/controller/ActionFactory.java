package study.controller;

import study.controller.action.studyGroup.*;

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
			action = new GetStudyGroupAction();
		}else if(command.equals("/board")){
			action = new FindStudyBoardAction();
		}else if(command.equals("/delete")){
			action = new DeleteStudyAction();
		}else if(command.equals("/createStudy")){
			action = new CreateStudyAction();
		}else if(command.equals("/getMember")){
			action = new GetStudyMemberAction();
		}else if(command.equals("/getAwaiter")){
			action = new GetStudyAwaiterAction();
		}else if(command.equals("/deleteMember")){
			action = new DeleteStudyMemberAction();
		}else if(command.equals("/joinMember")){
			action = new JoinStudyMemberAction();
		}else if(command.equals("/checkAwaiter")){
			action = new CheckAwaiterAction();
		}else if(command.equals("/addAwaiter")){
			action = new AddAwaiterAction();
		}else if(command.equals("/deleteMemberByMemberCode")){
			action = new DeleteMemberByMemberCodeAction();
		}
		
		return action;
	}
}
