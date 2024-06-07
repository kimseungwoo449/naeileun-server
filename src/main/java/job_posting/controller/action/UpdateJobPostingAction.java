package job_posting.controller.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import job_posting.controller.Action;
import job_posting.model.JobPostingDao;
import job_posting.model.JobPostingRequestDto;
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

public class UpdateJobPostingAction implements Action {

    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();

        int status = 0;
        String message = "";

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            status = 400;
            message = "admin key is not correct.";
        } else {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                data.append(line);
            }
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();
            JobPostingRequestDto jobPost = gson.fromJson(data.toString(), JobPostingRequestDto.class);
            JobPostingDao jobDao = JobPostingDao.getInstance();
            JobPostingResponseDto jobDto = jobDao.updateJobPosting(jobPost);
            status = (jobDto != null) ? 200 : 400;
            message = (jobDto != null) ? "JobPosting update is success." : "JobPosting update is failed.";
        }
        resObj.put("status", status);
        resObj.put("message", message);


        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(resObj.toString());



    }
}
