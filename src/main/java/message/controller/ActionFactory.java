package message.controller;


public class ActionFactory {
    private ActionFactory() {

    }

    private static ActionFactory instance = new ActionFactory();

    public static ActionFactory getInstance() {return instance;}

    public Action getAction(String pathInfo, String method) {
        Action action = null;

        if (pathInfo == null) {
            if(method.equals("GET")) {

            }else if(method.equals("POST")) {

            }
        } else {
            if(method.equals("GET")) {

            }
        }

        return action;
    }
}
