package study.controller.action.studyGroup;

import org.json.JSONArray;
import org.json.JSONObject;
import study.controller.Action;
import study.model.studyGroup.StudyGroupDao;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DeleteStudyAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        JSONObject obj = new JSONObject();
        boolean status = false;
        String message = null;

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
                message ="admin key is not correct";
        }else{
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String data = "";

            while (br.ready()) {
                data = br.readLine();
            }

            String groupCode = new JSONObject(data).getString("group_code");

            StudyGroupDao sgDao = StudyGroupDao.getInstance();
            boolean isValid = sgDao.deleteStudyByGroupCode(groupCode);

            status = isValid;
            if(isValid){
                message = "Group Delete is successful.";
            }else{
                message = "Group Delete failed.";
            }

            in.close();
            br.close();
        }
        obj.put("status",status);
        obj.put("message", message);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(obj.toString());
    }
}
