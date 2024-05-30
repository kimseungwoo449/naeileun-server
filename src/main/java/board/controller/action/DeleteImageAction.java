package board.controller.action;

import board.controller.Action;
import org.json.JSONObject;
import utill.ImageHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteImageAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("이미지 삭제하기");

        JSONObject resObj = new JSONObject();

        String imageUrl = "https://ucarecdn.com/69dfed0b-8ea9-4f8d-b424-85fb65bf0594/";
        boolean isDelete = ImageHandler.deleteImage(imageUrl);
        System.out.println("isDelete : " + isDelete);
    }
}
