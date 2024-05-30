package user.controller.action.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import user.controller.Action;
import user.model.UserDao;
import user.model.UserResponseDto;

public class UpdateAction implements Action {

    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream in = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String data = "";
		while (br.ready()) {
			data += br.readLine() + "\n";
		}	

        System.out.println("data : " + data);
        JSONObject object = new JSONObject(data);

        String field = object.getString("field");
        String value = object.getString("value");
        System.out.println(field);
        System.out.println(value);
        UserDao userDao = UserDao.getInstance();
        UserResponseDto userDto = null;

        String userId = object.getString("id");
        System.out.println(userId);
       

        if ("password".equals(field)) {
            String currentPassword = object.getString("currentPassword");
            String newPassword = object.getString("newPassword");
            System.out.println("currentPassword : " + currentPassword);
            System.out.println("newPassword : " + newPassword);
            userDto = userDao.findUserByIdAndPassword(userId,currentPassword);

            if (userDto != null && userDto.getPassword().equals(currentPassword)) {
                userDto = userDao.updateUserPassword(userId, newPassword);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"message\": \"현재 비밀번호가 일치하지 않습니다.\"}");
                return;
            }
        } else {
            userDto = userDao.updateUserField(userId, field, value);
        }

        JSONObject resObj = new JSONObject();
        int status = userDto != null ? 200 : 400;
        String message = userDto != null ? "User update is success." : "User update is failed.";

        resObj.put("status", status);
        resObj.put("message", message);
        
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(resObj.toString());
    }
}
