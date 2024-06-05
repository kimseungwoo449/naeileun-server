package study.controller.action.studyGroup;

import org.json.JSONArray;
import org.json.JSONObject;
import study.controller.Action;
import study.model.groupMember.GroupMemberRequestDto;
import study.model.studyGroup.StudyGroupDao;
import study.model.studyGroup.StudyGroupResponseDto;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class GetPopularGroup implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        JSONObject obj = new JSONObject();
        JSONArray result = new JSONArray();
        boolean status = true;
        int meta = 0;

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            obj.put("result",result);
            obj.put("meta", meta);
            obj.put("status", false);
        }else{
            StudyGroupDao sgDao = StudyGroupDao.getInstance();
            List<StudyGroupResponseDto> popularStudy = sgDao.getPopularStudy();

            if(popularStudy.isEmpty()){
                status = false;
            }

            meta = popularStudy.size();
            obj.put("meta",meta);
            obj.put("status",status);
            obj.put("result", popularStudy);
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(obj.toString());
        }
    }

