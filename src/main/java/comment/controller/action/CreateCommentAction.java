package comment.controller.action;

import board.model.BoardDao;
import board.model.BoardResponseDto;
import comment.controller.Action;
import comment.model.CommentDao;
import comment.model.CommentRequestDto;
import comment.model.CommentResponseDto;
import org.json.JSONObject;
import resume.model.ResumeDao;
import resume.model.ResumeRequestDto;
import user.model.UserDao;
import utill.ImageHandler;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

 // 파일 업로드를 처리하기 위한 어노테이션
public class CreateCommentAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("댓글 생성하기");

        JSONObject resObj = new JSONObject();
        boolean status = true;
        String message = "Comment is created..";

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
            System.out.println("CommentData : "+data);

            JSONObject object = new JSONObject(data);
            String userId = object.getString("user_id");
            int postCode = Integer.parseInt(object.getString("post_code"));
            String content = object.getString("content");

            System.out.println("userId : "+userId);
            System.out.println("postCode : "+postCode);
            System.out.println("content : "+content);

            UserDao userDao = UserDao.getInstance();
            int userCode = userDao.findUserCodeById(userId);

            CommentRequestDto requestDto = new CommentRequestDto(userCode, postCode, content);

            CommentDao commentDao = CommentDao.getInstance();
            Boolean isSuccess = commentDao.createComment(requestDto);

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
