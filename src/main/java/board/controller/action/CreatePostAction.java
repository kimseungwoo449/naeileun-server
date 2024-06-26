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

public class CreatePostAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();
        BoardDao boardDao = BoardDao.getInstance();

        boolean status = true;

        // AdminKey 검증
        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
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

                if(!name.equals("file")) {
                    InputStream in = part.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));


                    String data = "";
                    while(br.ready()) {
                        data += br.readLine();
                        data += "\n";
                    }
                    data = data.substring(0, data.length() - 1);

                    if(name.equals("title")) {
                        title = data;
                    }
                    else if(name.equals("content")) {
                        content = data;
                    }
                    else if(name.equals("user_id")) {
                        userId = data;
                    }else if(name.equals("board_code")) {
                        boardCate = data;
                    }
                    br.close();
                    in.close();


                } else {
                    imageUrl = ImageHandler.upload(part);
                }
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
