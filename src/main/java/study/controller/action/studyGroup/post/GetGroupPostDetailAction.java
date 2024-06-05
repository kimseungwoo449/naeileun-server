package study.controller.action.studyGroup.post;

import org.json.JSONArray;
import org.json.JSONObject;
import study.controller.Action;
import study.model.groupPost.GroupPostDao;
import study.model.groupPost.GroupPostRequestDto;
import study.model.groupPost.GroupPostResponseDto;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetGroupPostDetailAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        JSONObject obj = new JSONObject();
        JSONObject result = null;
        boolean status = false;
        String message = "";
        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            message ="adminKey is not correct";
        } else {
           String postCode = request.getParameter("post_code");

           GroupPostRequestDto gpReqDto = new GroupPostRequestDto();
           gpReqDto.setPostCode(postCode);
           GroupPostDao gpDao = GroupPostDao.getInstance();
           GroupPostResponseDto gpResDto = gpDao.getGroupPostDetail(gpReqDto);
           result = new JSONObject(gpResDto);
           status = true;
           message = "success";
        }
        obj.put("result",result);
        obj.put("status",status);
        obj.put("message",message);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(obj.toString());
    }
}
