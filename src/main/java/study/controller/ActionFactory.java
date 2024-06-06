package study.controller;

import board.controller.action.DeletePostAction;
import study.controller.action.studyGroup.*;
import study.controller.action.studyGroup.post.*;

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
		}else if(command.equals("/getStandbyMembers")){
			action = new GetStudyStandbyMembersAction();
		}else if(command.equals("/deleteMember")){
			action = new DeleteStudyMemberAction();
		}else if(command.equals("/joinMember")){
			action = new JoinStudyMemberAction();
		}else if(command.equals("/checkStandbyMember")){
			action = new CheckStandbyMemberAction();
		}else if(command.equals("/addStandbyMember")){
			action = new AddStandbyMemberAction();
		}else if(command.equals("/deleteMemberByMemberCode")){
			action = new DeleteMemberByMemberCodeAction();
		}else if(command.equals("/deleteStandbyMember")){
			action = new RefuseStandbyMemberAction();
		}else if(command.equals("/getMemberFromStandbyMember")){
			action = new AddMemberFromStandbyMemberAction();
		}else if(command.equals("/createPost")){
			action = new CreateGroupPostAction();
		}else if(command.equals("/post")) {
			action = new GetGroupPostDetailAction();
		}else if(command.equals("/updatePost")){
			action = new UpdateGroupPostAction();
		}else if(command.equals("/deletePost")){
			action = new DeleteGroupPostAction();
		}else if(command.equals("/groupPosts")){
			action = new GetGroupPostsAction();
		}
		
		return action;
	}
}
