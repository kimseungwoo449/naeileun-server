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

public class DeleteJobPostingAction implements Action {
    @Override
    public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();

        int status = 0;
        String message = "";

        if (!request.getHeader("Authorization").equals(KeyManager.getAdminKey())) {
            status = 400;
            message = "JobPosting delete is failed.";
        } else {
            InputStream in = request.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String data = "";
            while (br.ready()) {
                data += br.readLine() + "\n";
            }
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();

            JobPostingRequestDto jobPost = gson.fromJson(data, JobPostingRequestDto.class);
            JobPostingDao jobDao = JobPostingDao.getInstance();
             status = jobDao.deleteJobPosting(jobPost) ? 200 : 400;
             message = status == 200 ? "JobPosting delete is success." : "JobPosting delete is failed.";
        }
        resObj.put("status", status);
        resObj.put("message", message);


        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(resObj.toString());


    }

}
