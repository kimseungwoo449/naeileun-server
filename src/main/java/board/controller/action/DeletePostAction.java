package board.controller.action;

import board.controller.Action;
import board.controller.action.DeleteImageAction;
import board.model.BoardDao;
import org.json.JSONObject;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DeletePostAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("게시글 지우기");

        JSONObject resObj = new JSONObject();
        boolean status = false;

        // JSON 본문 읽기
        InputStream in = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String data = "";

        while (br.ready()) {
            data += br.readLine() + "\n";
        }
        System.out.println("resumeData : "+data);
        JSONObject reqObj = new JSONObject(data);
        String userId = reqObj.getString("user_id");
        int postCode = Integer.parseInt(reqObj.getString("post_code"));
        int boardCode = Integer.parseInt(reqObj.getString("board_code"));

        System.out.println("userId: " + userId);
        System.out.println("postCode: " + postCode);
        System.out.println("boardCode: " + boardCode);

        // AdminKey 검증
        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            System.out.println("AdminKey 오류!!");
            status = false;
        } else {

            // 게시글 삭제 로직 추가
            BoardDao boardDao = BoardDao.getInstance();

            // 응답 JSON 작성
            status = boardDao.deletePostByPostCode(boardCode, userId, postCode);
            System.out.println("deletePostByPostCode status: " + status);
            if(status) {
                // 이미지 삭제
                Action action = new DeleteImageAction();
            }
        }

        resObj.put("status", status);
        System.out.println("DeletePost resObj : " + resObj);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(resObj.toString());
    }
}
