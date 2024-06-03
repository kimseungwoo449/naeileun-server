package study.controller.action.studyGroup;

import org.json.JSONObject;
import study.controller.Action;
import study.model.groupAwaiter.GroupAwaiterDao;
import study.model.groupAwaiter.GroupAwaiterRequestDto;
import study.model.groupMember.GroupMemberDao;
import study.model.groupMember.GroupMemberRequestDto;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CheckAwaiterAction implements Action {

    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        JSONObject obj = new JSONObject();
        boolean status = false;
        String message = null;
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

            String groupcode = reqObj.getString("group_code");
            String userCode = reqObj.getString("user_code");
            GroupAwaiterRequestDto gaReqDto = new GroupAwaiterRequestDto();
            gaReqDto.setGroupCode(groupcode);
            gaReqDto.setUserCode(userCode);

            GroupAwaiterDao gaDao = GroupAwaiterDao.getInstance();

            status = gaDao.checkAwaiter(gaReqDto);

            System.out.println(status);
            if(!status) {
                message = "Awaiter not exist";
            }else{
                message = "Awaiter exist";
            }
        }

        obj.put("status", status);
        obj.put("message", message);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(obj.toString());
    }
}
