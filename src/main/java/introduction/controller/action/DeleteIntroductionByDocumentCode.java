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

public class DeleteIntroductionByDocumentCode implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();
        boolean success = true;
        boolean status = true;
        String message = "Introduction is deleted.";

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            success = false;
        }else{
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String data = "";

            while (br.ready()) {
                data += br.readLine() + "\n";
            }

            JSONObject reqObj = new JSONObject(data);
            int documentCode = reqObj.getInt("document_code");
            String userId = reqObj.getString("user_id");
            UserDao userDao = UserDao.getInstance();
            IntroductionDao introductionDao = IntroductionDao.getInstance();

            if(userDao.findUserCodeById(userId)!=introductionDao.findUserCodeByDocCode(documentCode)) {
                success = false;
            }else{
                IntroductionRequestDto dto = new IntroductionRequestDto();
                dto.setDocumentCode(documentCode);
                if(!introductionDao.deleteIntroductionByDocCode(dto)){
                    success = false;
                }
            }
        }

        if(!success){
            status = false;
            message = "Introduction is not deleted";
        }

        resObj.put("status", status);
        resObj.put("message", message);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().append(resObj.toString());
    }
}
