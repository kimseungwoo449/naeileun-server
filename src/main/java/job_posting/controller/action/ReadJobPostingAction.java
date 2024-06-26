package job_posting.controller.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import job_posting.controller.Action;
import job_posting.model.JobPostingDao;
import job_posting.model.JobPostingResponseDto;
import org.json.JSONObject;
import utill.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReadJobPostingAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();

        int status = 0;
        String message = "";

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            status = 400;
            message = "admin key is not correct.";
            resObj.put("status", status);
            resObj.put("message", message);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().append(resObj.toString());
        } else {
            InputStream is = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int userCode = Integer.parseInt(br.readLine());

            JobPostingDao dao = JobPostingDao.getInstance();
            List<JobPostingResponseDto> jobPostings = dao.findMyJobPostingAll(userCode);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Gson gson = new GsonBuilder().setDateFormat(dateFormat.toPattern()).create();
            String jobPostingsJson = gson.toJson(jobPostings);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().append(jobPostingsJson);
        }
    }
}
