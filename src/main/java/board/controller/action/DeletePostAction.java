package board.controller.action;

import board.controller.Action;
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
        JSONObject resObj = new JSONObject();
        boolean status = false;
        boolean isDelete = false;

        // JSON 본문 읽기
        InputStream in = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String data = "";

        while (br.ready()) {
            data += br.readLine() + "\n";
        }
        System.out.println("postData : "+data);
        JSONObject reqObj = new JSONObject(data);
        String userId = reqObj.getString("user_id");
        int postCode = reqObj.getInt("post_code");
        int boardCode = reqObj.getInt("board_code");
        String imagePath = reqObj.getString("image_path");

        // AdminKey 검증
        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            status = false;
        } else {

            // 게시글 삭제 로직 추가
            BoardDao boardDao = BoardDao.getInstance();

            // 응답 JSON 작성
            status = boardDao.deletePostByPostCode(boardCode, userId, postCode);
            if(status) {
                // 이미지 삭제
                isDelete = boardDao.deleteImage(imagePath);

                if(imagePath.equals("") && !isDelete){
                    status = true;
                }
                else if(!imagePath.equals("") && isDelete){
                    status = true;
                }
            }
        }

        resObj.put("status", status);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(resObj.toString());
    }
}
