package message.controller.action;

import com.google.gson.Gson;
import message.controller.Action;
import message.model.MessageDao;
import message.model.MessageRequestDto;
import message.model.MessageResponseDto;
import user.model.UserDao;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllMessageByUserId implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, List<MessageResponseDto>> resMap = new HashMap<>();
        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            resMap = null;
        } else {
            List<MessageResponseDto> resDtoArr = null;
            String userId = request.getParameter("user");
            MessageRequestDto dto = new MessageRequestDto();
            dto.setReceiveUserCode(userId); // 받은 유저가 로그인 유저인경우
            dto.setSendUserCode(userId); // 보낸 유저가 로그인 유저인 경우
            int loginUserCode = dto.getSendUserCode();
            MessageDao dao = MessageDao.getInstance();

            resDtoArr = dao.getMessages(dto);
            resMap = createMap(resDtoArr, loginUserCode);
        }


        Gson gson = new Gson();
        String jsonResponse = gson.toJson(resMap);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // JSON 응답을 출력에 작성
        response.getWriter().write(jsonResponse);
    }

    private Map<String, List<MessageResponseDto>> createMap(List<MessageResponseDto> resDtoArr, int loginUserCode) {
        Map<String, List<MessageResponseDto>> map = new HashMap<>();
        UserDao userDao = UserDao.getInstance();

        List<MessageResponseDto> messages = new ArrayList<>(resDtoArr);

        for (MessageResponseDto dto : messages) {
            int keyUserCode;
            if (dto.getReceiveUserCode() == loginUserCode) {
                keyUserCode = dto.getSendUserCode();
            } else if (dto.getSendUserCode() == loginUserCode) {
                keyUserCode = dto.getReceiveUserCode();
            } else {
                continue;
            }

            // 유저 코드를 이용해 유저 아이디를 가져옴
            String keyUserId = userDao.findUserIdByCode("" + keyUserCode);

            List<MessageResponseDto> userMessages = map.getOrDefault(keyUserId, new ArrayList<>());
            userMessages.add(dto);
            map.put(keyUserId, userMessages);
        }

        return map;
    }

}
