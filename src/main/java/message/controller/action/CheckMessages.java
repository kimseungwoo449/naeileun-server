package message.controller.action;

import message.controller.Action;
import message.model.MessageDao;
import message.model.MessageRequestDto;
import org.json.JSONObject;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CheckMessages implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();

        boolean success = true;

        boolean status = true;
        String message = "message checked";

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            success = false;
        } else {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String data = "";

            while (br.ready()) {
                data += br.readLine() + "\n";
            }

            JSONObject reqObj = new JSONObject(data);
            MessageRequestDto dto = new MessageRequestDto();
            dto.setSendUserCode(reqObj.getString("send_user_id"));
            dto.setReceiveUserCode(reqObj.getString("receive_user_id"));
            MessageDao dao = MessageDao.getInstance();
            if(!dao.refineIsChecked(dto))
                success = false;
        }

        if (!success) {
            status = false;
            message = "message not checked";
        }

        resObj.put("status", status);
        resObj.put("message", message);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().append(resObj.toString());
    }
}
