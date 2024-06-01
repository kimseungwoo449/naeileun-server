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

public class UpdatePostAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("게시글 수정하기");

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
            String postCode = "";
            String imageUrl = "";
            String currentImagePath = "";

            for(Part part : parts) {
                String name = part.getName();
                System.out.println("name : " + name);
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
                        System.out.println("title : " + title);
                    }
                    else if(name.equals("content")) {
                        content = data;
                    }
                    else if(name.equals("user_id")) {
                        userId = data;
                        System.out.println("userId : " + userId);
                    }
                    else if(name.equals("post_code")) {
                        postCode = data;
                    }
                    else if(name.equals("image_path")){
                        System.out.println("data : " + data);
                        currentImagePath = data.equals("null") ? "" : data;
                        System.out.println("currentImagePath : " + currentImagePath);
                    }

                    br.close();
                    in.close();

                } else {
                    boolean isDeleted = boardDao.deleteImage(currentImagePath);
                    System.out.println("isDeleted : " + isDeleted);

                    imageUrl = ImageHandler.upload(part);
                    System.out.println("imageUrl : " + imageUrl);
                    System.out.println("ImageHandler upload 빠져나오기");
                }
            }

            System.out.println("for문 나오기");
            System.out.println("title : " + title);
            System.out.println("content : " + content);
            System.out.println("userId : " + userId);
            System.out.println("postCode : " + postCode);
            System.out.println("imageUrl : " + imageUrl);

            BoardResponseDto responseDto = boardDao.UpdatePost(title, content, userId, Integer.parseInt(postCode), imageUrl);
            System.out.println("CreatePost responseDto : " + responseDto);
            resObj = new JSONObject(responseDto);
        }

        resObj.put("status", status);
        System.out.println("UpdatePost resObj : " + resObj);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(resObj.toString());
    }
}
