package introduction.controller.action;

import com.google.gson.Gson;
import introduction.controller.Action;
import introduction.model.IntroductionDao;
import introduction.model.IntroductionRequestDto;
import introduction.model.IntroductionResponseDto;
import org.json.JSONObject;
import user.model.UserDao;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class GetIntroductionByDocumentCode implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<IntroductionResponseDto> resList = null;

        boolean success = true;

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            success = false;
        } else {
            int documentCode = Integer.parseInt(request.getParameter("document_code"));
            String userId = request.getParameter("user");
            UserDao userDao = UserDao.getInstance();
            IntroductionDao introductionDao = IntroductionDao.getInstance();

            if (userDao.findUserCodeById(userId) != introductionDao.findUserCodeByDocCode(documentCode)) {
                success = false;
            } else {
                IntroductionRequestDto dto = new IntroductionRequestDto();
                dto.setDocumentCode(documentCode);
                resList = introductionDao.getIntroductionByDocCode(dto);
            }
        }

        if (!success) {
            resList = null;
        }

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(resList);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(jsonResponse);
    }
}
