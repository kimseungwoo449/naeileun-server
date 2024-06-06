package study.controller.action.studyGroup.post;

import org.json.JSONArray;
import org.json.JSONObject;
import study.controller.Action;
import study.model.groupPost.GroupPostDao;
import study.model.groupPost.GroupPostResponseDto;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetGroupPostsAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        JSONObject obj = new JSONObject();
        JSONArray result = new JSONArray();
        boolean status = false;
        String message = "";
        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            message ="adminKey is not correct";
        } else {
            String groupCode = request.getParameter("group_code");
            String offset = request.getParameter("offset");

            GroupPostDao gpDao = GroupPostDao.getInstance();
            List<GroupPostResponseDto> list = gpDao.getGroupPosts(groupCode,offset);
            result.put(list);
            status = list.size() > 0;
        }

        obj.put("result",result);
        obj.put("status",status);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(obj.toString());
    }
}
