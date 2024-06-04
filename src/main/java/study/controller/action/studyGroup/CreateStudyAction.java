package study.controller.action.studyGroup;

import org.json.JSONObject;
import study.controller.Action;
import study.model.groupMember.GroupMember;
import study.model.groupMember.GroupMemberDao;
import study.model.studyGroup.StudyGroupDao;
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
            System.out.println(reqObj);

            String groupName = reqObj.getString("group_name");
            System.out.println("groupName: " + groupName);

            //String userCode = reqObj.getString("user_code"); //userId일 시 수정
            //user Id일 시 userDao로 userCode 찾기
            String userCode = "2";  //수정 시 해당 열 삭제

            String decription = reqObj.getString("decription").equals("") ? null : reqObj.getString("decription");
            System.out.println("decription: " + decription);

            boolean isPublic = reqObj.getBoolean("is_public");
            boolean autoMemberAccess = reqObj.getBoolean("auto_member_access");

            System.out.println("isPublic: " + isPublic);
            System.out.println("autoMemberAccess: " + autoMemberAccess);

            StudyGroupDao sgDao = StudyGroupDao.getInstance();
            groupCode = sgDao.createStudyAndGetGroupCode(groupName,userCode,decription,isPublic,autoMemberAccess);

            GroupMemberDao gmDao = GroupMemberDao.getInstance();
            boolean addMember = gmDao.addMemberByGroupCode(groupCode,userCode);

            boolean isValid = groupCode == null ? false : true;
            status = isValid;
            if(!isValid) {
                message = "Group Create failed.";
            }else if (!addMember) {
                message = "Add Member is failed.";
            }else{
                message = "Group Create success.";
            }
        }

        obj.put("status", status);
        obj.put("message", message);
        obj.put("groupCode",groupCode);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(obj.toString());
    }
}
