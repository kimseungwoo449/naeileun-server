package introduction.controller.action;

import introduction.controller.Action;
import introduction.model.IntroductionDao;
import introduction.model.IntroductionRequestDto;
import org.json.JSONArray;
import org.json.JSONObject;
import user.model.UserDao;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UpdateIntroduction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();
        String message = "Self-introductions are updated";
        boolean status = true;

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            message = "Self-introductions updated is failed";
            status = false;
        } else {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String data = "";

            while (br.ready()) {
                data += br.readLine() + "\n";
            }

            JSONArray reqObj = new JSONArray(data);

            List<IntroductionRequestDto> dtoList = new ArrayList<>();
            for (int i = 0; i < reqObj.length(); i++) {
                JSONObject obj = reqObj.getJSONObject(i);
                String userId = obj.getString("user_id");
                int docNumber = obj.getInt("document_no");
                String head = obj.getString("head");
                String body = obj.getString("body");
                IntroductionRequestDto dto = new IntroductionRequestDto();
                dto.setUserCode(userId);
                dto.setBody(body);
                dto.setDocumentNumber(docNumber);
                dto.setHead(head);
                dtoList.add(dto);
            }
            IntroductionDao introductionDao =  IntroductionDao.getInstance();
            if(!introductionDao.updateIntroductions(dtoList)){
                message = "Self-introductions updated is failed";
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
