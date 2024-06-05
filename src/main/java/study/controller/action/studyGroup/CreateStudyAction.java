package study.controller.action.studyGroup;

import org.json.JSONObject;
import study.controller.Action;
import study.model.groupMember.GroupMember;
import study.model.groupMember.GroupMemberDao;
import study.model.groupMember.GroupMemberRequestDto;
import study.model.studyGroup.StudyGroupDao;
import study.model.studyGroup.StudyGroupRequestDto;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CreateStudyAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        JSONObject obj = new JSONObject();
        boolean status = false;
        String message = null;
        String groupCode = null;
        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            message = "admin key is not correct";
        } else {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String data = "";

            while (br.ready()) {
                data += br.readLine();
            }

            JSONObject reqObj = new JSONObject(data);

            String groupName = reqObj.getString("group_name");
            String userCode = reqObj.getString("user_code");
            String decription = reqObj.getString("decription").equals("") ? null : reqObj.getString("decription");

            Boolean isPublic = reqObj.getBoolean("is_public");
            Boolean autoMemberAccess = reqObj.getBoolean("auto_member_access");
            StudyGroupRequestDto sgReqDto = new StudyGroupRequestDto();
            sgReqDto.setName(groupName);
            sgReqDto.setAdminCode(userCode);
            sgReqDto.setDecription(decription);
            sgReqDto.setIsPublic(String.valueOf(isPublic));
            sgReqDto.setAutoMemberAccess(String.valueOf(autoMemberAccess));

            StudyGroupDao sgDao = StudyGroupDao.getInstance();
            groupCode = sgDao.createStudyAndGetGroupCode(sgReqDto);
            GroupMemberRequestDto grReqDto = new GroupMemberRequestDto(groupCode, userCode);
            GroupMemberDao gmDao = GroupMemberDao.getInstance();
            boolean addMember = gmDao.joinGroupMember(grReqDto);

            boolean isValid = groupCode == null ? false : true;
            status = isValid;
            if(!isValid) {
                message = "Group Create failed.";
            }else if (!addMember) {
                message = "Add Member is failed.";
            }else{
                message = "Group Create success.";
            }
            in.close();
            br.close();
        }

        obj.put("status", status);
        obj.put("message", message);
        obj.put("groupCode",groupCode);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(obj.toString());
    }
}
