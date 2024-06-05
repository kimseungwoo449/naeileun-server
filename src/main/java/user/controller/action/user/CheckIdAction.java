package user.controller.action.user;

import org.json.JSONObject;
import user.controller.Action;
import user.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CheckIdAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String data = "";
        try (
                InputStream in = request.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            while (br.ready()) {
                data += br.readLine() + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isAvailable = false;
        UserDao userDao = UserDao.getInstance();
        JSONObject object = new JSONObject(data);
        if (object.has("check")) {
            String id = object.getString("check");
            isAvailable = userDao.checkIdAvailability(id);
        }
        JSONObject resObj = new JSONObject();
        resObj.put("isAvailable", isAvailable);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().append(resObj.toString());

    }
}
