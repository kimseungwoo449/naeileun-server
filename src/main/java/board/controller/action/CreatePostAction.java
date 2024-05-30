package board.controller.action;

import board.controller.Action;
import board.model.BoardDao;
import board.model.BoardResponseDto;
import org.json.JSONObject;
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
public class CreatePostAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("게시글 생성하기?");

        JSONObject resObj = new JSONObject();
        BoardDao boardDao = BoardDao.getInstance();

        boolean status = true;

        // AdminKey 검증
        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            System.out.println("AdminKey 오류!!");
            status = false;
        } else {
            List<Part> parts = (List<Part>) request.getParts();

            String title = "";
            String content = "";
            String userId = "";
            String boardCate = "";
            String imageUrl = "";

            for(Part part : parts) {
                String name = part.getName();

                InputStream in = part.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                String data = "";
                while(br.ready() && !name.equals("file")) {
                    data += br.readLine();
                    data += "\n";
                }
                data = data.substring(0, data.length() - 1);

                if(name.equals("title")) {
                    title = data;
                    System.out.println("title : " + title);
                }
                else if(name.equals("content")) {
                    content = data;
                }
                else if(name.equals("user_id")) {
                    userId = data;
                    System.out.println("userId : " + userId);
                }else if(name.equals("board_code")) {
                    boardCate = data;
                }
                else if(name.equals("file")) {
                    String type = part.getContentType().split("/")[0];
                    long size = part.getSize();
                    byte[] fileContents = in.readAllBytes();

                    imageUrl = ImageHandler.upload(type, size, fileContents);
                }

                br.close();
                in.close();
            }

            BoardResponseDto responseDto = boardDao.createPost(title, content, userId, boardCate, imageUrl);
            resObj = new JSONObject(responseDto);
        }

        resObj.put("status", status);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(resObj.toString());
    }
}
