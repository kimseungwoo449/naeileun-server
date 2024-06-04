package introduction.controller.action;

import com.google.gson.Gson;
import introduction.controller.Action;
import introduction.model.IntroductionDao;
import introduction.model.IntroductionRequestDto;
import introduction.model.IntroductionResponseDto;
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
import java.util.Map;

public class GetAllIntroductionByUserId implements Action {

    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, List<IntroductionResponseDto>> introductions = null;

        if (request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
//            InputStream in = request.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//
//            String data = "";
//
//            while (br.ready()) {
//                data += br.readLine() + "\n";
//            }
//
//            JSONObject reqObj = new JSONObject(data);
//            String userId = reqObj.getString("user_id");
            String userId = request.getParameter("user_id");
            IntroductionRequestDto dto = new IntroductionRequestDto();
            dto.setUserCode(userId);

            IntroductionDao introductionDao = IntroductionDao.getInstance();
            introductions = introductionDao.getAllIntroductionByUserCode(dto);
        }

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(introductions);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // JSON 응답을 출력에 작성
        response.getWriter().write(jsonResponse);
    }
}
