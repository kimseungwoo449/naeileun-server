package utill;

import javax.naming.Context;
import javax.naming.InitialContext;

public class KeyManager {
    public static String getAdminKey() {
        String adminKey ="";
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            adminKey = (String) envContext.lookup("ADMIN_KEY");

        }catch (Exception e){
            e.printStackTrace();
        }
        return adminKey;
    }
    public static String getUploadcareKey() {
        String key ="";
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            key = (String) envContext.lookup("UPLOADCARE_KEY");

        }catch (Exception e){
            e.printStackTrace();
        }
        return key;
    }

    public static String getDeletecareKey() {
        String key ="";
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            key = (String) envContext.lookup("DELETECARE_KEY");

        }catch (Exception e){
            e.printStackTrace();
        }
        return key;
    }
}