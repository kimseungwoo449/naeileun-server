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
            System.out.println("admin fail");
        }else {
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
//            int documentCode = reqObj.getInt("document_code");
//            String userId = reqObj.getString("user_id");
            int documentCode = Integer.parseInt(request.getParameter("document_code"));
            String userId = request.getParameter("user");
            UserDao userDao = UserDao.getInstance();
            IntroductionDao introductionDao = IntroductionDao.getInstance();

            if (userDao.findUserCodeById(userId) != introductionDao.findUserCodeByDocCode(documentCode)) {
                System.out.println("docCode : "+documentCode);
                System.out.println("userId : "+userId);
                success = false;
                System.out.println("id not matched");
            }else{
                IntroductionRequestDto dto = new IntroductionRequestDto();
                dto.setDocumentCode(documentCode);
                resList = introductionDao.getIntroductionByDocCode(dto);
            }
        }

        if (!success) {
            resList = null;
        }

        for(int i =0;i<resList.size();i++){
            System.out.println(resList.get(i).getHead());
        }

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(resList);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // JSON 응답을 출력에 작성
        response.getWriter().write(jsonResponse);
    }
}
