package board.controller.action;

import board.controller.Action;
import board.model.BoardDao;
import org.json.JSONObject;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateRecommendationAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();

        System.out.println("추천수 올리기");

        boolean status = true;
        String message = "Recommendation is Success.";
        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            status = false;
            message = "Recommendation is blocked.";

        } else {
            int postCode = Integer.parseInt(request.getParameter("post_code"));

            BoardDao boardDao = BoardDao.getInstance();

            boolean isUpdate = boardDao.updateRecommendationByPostCode(postCode);

            if(!isUpdate) {
                status = false;
                message = "Recommendation is blocked.";
            }

            System.out.println("isUpdate : " + isUpdate);

        }

        resObj.put("status", status);
        resObj.put("message", message);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(resObj.toString());
    }
}
