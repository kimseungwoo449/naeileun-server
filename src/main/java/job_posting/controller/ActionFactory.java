package job_posting.controller;


import job_posting.controller.action.CreateJobPostingAction;
import job_posting.controller.action.DeleteJobPostingAction;
import job_posting.controller.action.ReadJobPostingAction;
import user.controller.action.user.JoinAction;

public class ActionFactory {
    private ActionFactory() {

    }

    private static ActionFactory instance = new ActionFactory();

    public static ActionFactory getInstance() {
        return instance;
    }

    public Action getAction(String command) {
        Action action = null;
        if (command.equals("/create")) {
            action = new CreateJobPostingAction();
        }else if (command.equals("/read")) {
            action = new ReadJobPostingAction();
        }else if (command.equals("/update")) {
            action = new CreateJobPostingAction();
        }else if (command.equals("/delete")) {
            action = new DeleteJobPostingAction();
        }
        return action;
    }
}
