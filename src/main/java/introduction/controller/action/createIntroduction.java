package introduction.controller.action;

import introduction.controller.Action;
import introduction.model.IntroductionDao;
import introduction.model.IntroductionRequestDto;
import org.json.JSONArray;
import org.json.JSONObject;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class createIntroduction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();

        boolean status = true;
        String message = "Introduction is created..";

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            status = false;
            message = "Introduction is blocked.";
        } else {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String data = "";

            while (br.ready()) {
                data += br.readLine() + "\n";
            }
            JSONObject reqObj = new JSONObject(data);
            JSONArray dataArr = reqObj.getJSONArray("introductions");
            List<IntroductionRequestDto> dtoArr = new ArrayList<>();
            for(int i =0;i<dataArr.length();i++){
                IntroductionRequestDto dto = new IntroductionRequestDto(dataArr.getJSONObject(i));
                dtoArr.add(dto);
            }

            IntroductionDao introductionDao = IntroductionDao.getInstance();
            if(!introductionDao.addIntroduction(dtoArr)){
                status = false;
                message = "Introduction is blocked.";
            }
        }

        resObj.put("status", status);
        resObj.put("message", message);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().append(resObj.toString());
    }
}
