package introduction.controller.action;

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

public class getAllIntroductionByUserId implements Action {
//    @Override
//    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        JSONObject resObj = new JSONObject();
//        List<IntroductionResponseDto> introductions = null;
//        if (!request.getHeader("Authorization").equals(IPAdressManager.ADMIN_KEY)) {
//            resObj = null;
//        }else{
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
//            IntroductionRequestDto dto = new IntroductionRequestDto();
//            String userId = reqObj.getString("user_id");
//            System.out.println(userId);
//            dto.setUserCode(userId);
//
//            IntroductionDao introductionDao = IntroductionDao.getInstance();
//            introductions = introductionDao.getAllIntroductionByUserCode(dto);
//        }
//
//        if(introductions!=null){
//            Object[] tempArr  = new Object[introductions.size()];
//
//            for(int i =0;i<introductions.size();i++){
//                IntroductionResponseDto targetDoc = introductions.get(i);
//
//                JSONObject temp = new JSONObject();
//                temp.put("document_no",targetDoc.getDocumentNumber());
//                temp.put("title",targetDoc.getTitle());
//                temp.put("head",targetDoc.getHead());
//                temp.put("body",targetDoc.getBody());
//                temp.put("document_code",targetDoc.getDocumentCode());
//                temp.put("user_code",targetDoc.getUserCode());
//                temp.put("write_date",targetDoc.getWriteDate());
//                temp.put("update_date",targetDoc.getUpdateDate());
//                tempArr[i] = temp;
//            }
//            resObj.put("result",tempArr);
//        }else{
//            resObj=null;
//        }
//
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json;charset=UTF-8");
//
//        response.getWriter().append(resObj.toString());
//    }

    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();
        Map<Integer, List<IntroductionResponseDto>> introductions = null;
        if (!request.getHeader("Authorization").equals(KeyManager.ADMIN_KEY)) {
            resObj = null;
        } else {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String data = "";

            while (br.ready()) {
                data += br.readLine() + "\n";
            }

            JSONObject reqObj = new JSONObject(data);
            IntroductionRequestDto dto = new IntroductionRequestDto();
            String userId = reqObj.getString("user_id");
            dto.setUserCode(userId);

            IntroductionDao introductionDao = IntroductionDao.getInstance();
            introductions = introductionDao.getAllIntroductionByUserCode(dto);
        }

        if (introductions != null) {
//            Object[] keys = introductions.keySet().toArray();
//
//            JSONObject keyObj = new JSONObject();
//            for (int i = 0; i < keys.length; i++) {
//                List<IntroductionResponseDto> listOfKey = introductions.get(keys[i]);
//                Object[] tempArr = new Object[listOfKey.size()];
//                for (int j = 0; j < listOfKey.size(); j++) {
//                    IntroductionResponseDto targetDoc = listOfKey.get(i);
//
//                    JSONObject temp = new JSONObject();
//                    temp.put("document_no", targetDoc.getDocumentNumber());
//                    temp.put("title", targetDoc.getTitle());
//                    temp.put("head", targetDoc.getHead());
//                    temp.put("body", targetDoc.getBody());
//                    temp.put("document_code", targetDoc.getDocumentCode());
//                    temp.put("user_code", targetDoc.getUserCode());
//                    temp.put("write_date", targetDoc.getWriteDate());
//                    temp.put("update_date", targetDoc.getUpdateDate());
//                    tempArr[i] = temp;
//                }
//                keyObj.put(keys[i].toString(), tempArr);
//            }
            resObj.put("result",introductions);
        } else {
            resObj = null;
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().append(resObj.toString());
    }
}
