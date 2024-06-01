package message.controller.action;

import com.google.gson.Gson;
import message.controller.Action;
import message.model.MessageDao;
import message.model.MessageRequestDto;
import message.model.MessageResponseDto;
import org.json.JSONArray;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllMessageByUserId implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MessageResponseDto> resDtoArr = null;

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            resDtoArr = null;
        }else{
            String userId = request.getParameter("user_id");
            MessageRequestDto dto = new MessageRequestDto();
            dto.setReceiveUserCode(userId); // 받은 유저가 로그인 유저인경우
            dto.setSendUserCode(userId); // 보낸 유저가 로그인 유저인 경우
            MessageDao dao = MessageDao.getInstance();

            resDtoArr = dao.getMessages(dto);
        }

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(resDtoArr);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // JSON 응답을 출력에 작성
        response.getWriter().write(jsonResponse);
    }
}
