package message.controller;


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
            }
        } else {
            if(method.equals("GET")) {

            }
        }

        return action;
    }
}
