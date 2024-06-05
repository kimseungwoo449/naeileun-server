package study.controller.action.studyGroup;

import org.json.JSONArray;
import org.json.JSONObject;
import study.controller.Action;
import study.model.groupMember.GroupMemberDao;
import study.model.groupMember.GroupMemberRequestDto;
import study.model.groupMember.GroupMemberResponseDto;
import study.model.studyGroup.StudyGroupDao;
import user.model.UserDao;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class GetStudyMemberAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        JSONObject obj = new JSONObject();
        JSONArray result = null;
        JSONObject meta = null;
        String message = null;
        String groupCode = null;

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            message = "admin key is not correct";
        } else {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String data = "";

            while (br.ready()) {
                data = br.readLine();
            }

            JSONObject reqObj = new JSONObject(data);

            groupCode = reqObj.getString("group_code");

            GroupMemberRequestDto gmReqDto = new GroupMemberRequestDto();
            gmReqDto.setGroupCode(groupCode);

            GroupMemberDao gmDao = GroupMemberDao.getInstance();
            List<GroupMemberResponseDto> list = gmDao.getStudyMembers(gmReqDto);

            boolean isValid = list.isEmpty() ? false : true;
            if(!isValid) {
                message = "Find Group Member failed.";
            }else{
                message = "Find Group Member success.";
                result = new JSONArray(list);
                meta = new JSONObject(list.size());
            }
        }

        obj.put("result", result);
        obj.put("meta", meta);
        obj.put("message", message);
        obj.put("groupCode",groupCode);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(obj.toString());
    }
}
