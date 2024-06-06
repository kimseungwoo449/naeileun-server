package study.controller.action.studyGroup.post;

import org.json.JSONObject;
import study.controller.Action;
import study.model.groupPost.GroupPostDao;
import study.model.groupPost.GroupPostRequestDto;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CreateGroupPostAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        JSONObject obj = new JSONObject();
        boolean status = false;
        String message = "";
        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            message = "admin key is not correct";
        }else {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String data = "";

            while (br.ready()) {
                data += br.readLine() + "\n";
            }

            data = data.substring(0, data.length() - 1);

            JSONObject object = new JSONObject(data);
            System.out.println("here");

            String title= object.getString("title");
            String content = object.getString("content");
            String groupCode = object.getString("groupCode");
            String userCode = object.getString("userCode");


            GroupPostRequestDto gpReqDto = new GroupPostRequestDto(groupCode,userCode,title,content);
            GroupPostDao groupPostDao = GroupPostDao.getInstance();
            status = groupPostDao.createGroupPost(gpReqDto);

            if(!status) {
                message = "Create Post failed";
            }else{
                message = "Create Post success";
            }
        }

        obj.put("status", status);
        obj.put("message", message);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(obj.toString());
    }
}

