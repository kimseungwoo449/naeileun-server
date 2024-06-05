package comment.controller.action;

import comment.controller.Action;
import comment.model.CommentDao;
import comment.model.CommentRequestDto;
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

public class UpdateCommentAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();
        boolean status = true;
        String message = "Comment is updated..";

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            status = false;
            message = "Comment is blocked.";
        } else {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String data = "";

            while (br.ready()) {
                data += br.readLine() + "\n";
            }
            data = data.substring(0, data.length() - 1);

            JSONObject object = new JSONObject(data);
            String userId = object.getString("user_id");
            int postCode = object.getInt("post_code");
            int commentCode = object.getInt("comment_code");
            String content = object.getString("content");

            UserDao userDao = UserDao.getInstance();
            int userCode = userDao.findUserCodeById(userId);

            CommentRequestDto requestDto = new CommentRequestDto(commentCode, userCode, postCode, content);

            CommentDao commentDao = CommentDao.getInstance();
            boolean isSuccess = commentDao.updateComment(requestDto);

            if (!isSuccess) {
                status = false;
                message = "Comment is blocked.";
            }
        }

        resObj.put("status", status);
        resObj.put("message", message);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().append(resObj.toString());
    }
}
