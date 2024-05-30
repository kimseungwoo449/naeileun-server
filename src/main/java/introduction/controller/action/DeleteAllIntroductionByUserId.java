package introduction.controller.action;

import introduction.controller.Action;
import introduction.model.IntroductionDao;
import introduction.model.IntroductionRequestDto;
import org.json.JSONObject;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DeleteAllIntroductionByUserId implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();
        String message = "Self-introductions are deleted";
        boolean status = true;

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            message = "Self-introductions deleted is failed";
            status = false;
        } else {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String data = "";

            while (br.ready()) {
                data += br.readLine() + "\n";
            }

            JSONObject reqObj = new JSONObject(data);
            IntroductionRequestDto dto = new IntroductionRequestDto();
            String userId = reqObj.getString("user_id");
            dto.setUserCode(userId);

            IntroductionDao introductionDao = IntroductionDao.getInstance();
            if(!introductionDao.deleteAllIntroductionByUserCode(dto)){
                message = "Self-introductions deleted is failed";
                status = false;
            }
        }

        resObj.put("message", message);
        resObj.put("status", status);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().append(resObj.toString());
    }
}
