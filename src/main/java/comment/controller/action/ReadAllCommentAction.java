package comment.controller.action;

import comment.controller.Action;
import comment.model.CommentDao;
import comment.model.CommentResponseDto;
import org.json.JSONArray;
import org.json.JSONObject;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ReadAllCommentAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            boolean status = false;
            String message = "Board is blocked.";

            resObj.put("status", status);
            resObj.put("message", message);
        } else {
            System.out.println("전체 댓글 가져오기");

            int postCode = Integer.parseInt(request.getParameter("post_code"));

            CommentDao commentDao = CommentDao.getInstance();

            List<CommentResponseDto> commentList = commentDao.readAllCommentByPostCode(postCode);

            JSONObject meta = new JSONObject();
            meta.put("total_count", commentList.size());

            JSONArray result = new JSONArray(commentList);

            resObj.put("meta", meta);
            resObj.put("result", result);
            System.out.println(resObj);

        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(resObj.toString());
    }
}
