package message.controller;


import message.controller.action.CheckMessages;
import message.controller.action.DeleteAllMessageByUserId;
import message.controller.action.GetAllMessageByUserId;
import message.controller.action.SendMessage;

public class ActionFactory {
    private ActionFactory() {

    }

    private static ActionFactory instance = new ActionFactory();

    public static ActionFactory getInstance() {return instance;}

    public Action getAction(String pathInfo, String method) {
        Action action = null;

        if (pathInfo == null) {
            if(method.equals("GET")) {
                action = new GetAllMessageByUserId();
            }else if(method.equals("POST")) {
                action = new SendMessage();
            }else if(method.equals("PUT")) {
                action = new CheckMessages();
            }else if(method.equals("DELETE")) {
                action = new DeleteAllMessageByUserId();
            }
        } else {
            if(method.equals("GET")) {
                action = new GetAllMessageByUserId();
            }
        }

        return action;
    }
}
